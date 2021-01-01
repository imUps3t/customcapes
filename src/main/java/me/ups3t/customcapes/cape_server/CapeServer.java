package me.ups3t.customcapes.cape_server;

import java.io.IOException;
import java.net.ServerSocket;

public class CapeServer {

    public static void hostServer(int port) {

        try {
            ServerSocket socket = new ServerSocket(port);

            while(true) {
                Thread thread = new Thread(new RequestHandler(socket.accept()));
                thread.start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
