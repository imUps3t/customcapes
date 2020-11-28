package me.ups3t.customcapes.cape_server;

import me.ups3t.customcapes.CapeUtils;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler implements Runnable {

    private Socket socket;

    private BufferedReader socketIn;
    private DataOutputStream socketOut;

    public RequestHandler(Socket socket) {
        this.socket = socket;

        try {
            this.socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.socketOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {

        List<String> headers = new ArrayList<>();
        headers.add("HTTP/1.1 200 OK");
        headers.add("Content-Type: image/png");
        headers.add("Content-Length: %l");
        headers.add("Accept-Ranges: bytes");

        try {

            String requestMeta = socketIn.readLine();

            if(requestMeta.startsWith("GET") && requestMeta.split(" ")[1].startsWith("/capes/")) {
                String requestedCape = requestMeta.split(" ")[1];
                String requestedUser = requestedCape.split("/")[2];
                requestedUser = requestedUser.substring(0, requestedUser.length() - 4);

                if (CapeUtils.getExemptUsers().contains(requestedUser)) {
                    if (CapeUtils.getUserCapeMap().containsKey(requestedUser)) {

                        File requestedFile = new File(CapeUtils.customCapesPath + "capes" + File.separator + CapeUtils.getUserCapeMap().get(requestedUser));

                        if (requestedFile.exists()) {

                            int length = (int) requestedFile.length();
                            byte[] array = new byte[length];
                            InputStream in = new FileInputStream(requestedFile);
                            int offset = 0;
                            while (offset < length) {
                                int count = in.read(array, offset, (length - offset));
                                offset += count;
                            }
                            in.close();

                            for (String header : headers) {
                                socketOut.writeBytes(header.replaceAll("%l", String.valueOf(length)) + "\r\n");
                            }

                            socketOut.writeBytes("\r\n");
                            socketOut.write(array);
                            socketOut.flush();

                        }
                    }
                } else {

                    URL url = new URL("http://107.182.233.85/capes/" + requestedUser + ".png");

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    InputStream is = null;

                    int length = 0;
                    try {
                        is = url.openStream();
                        length = is.available();
                        byte[] byteChunk = new byte[4096];
                        int n;

                        while ((n = is.read(byteChunk)) > 0) {
                            baos.write(byteChunk, 0, n);
                        }
                    } catch (IOException e) {

                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }

                    for (String header : headers) {
                        socketOut.writeBytes(header.replaceAll("%l", String.valueOf(length)) + "\r\n");
                    }

                    socketOut.writeBytes("\r\n");
                    socketOut.write(baos.toByteArray());
                    socketOut.flush();


                }
            }


            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
