package me.ups3t.customcapes;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CapeUtils {

    private static ArrayList<String> exemptUsers;
    private static Map<String, Object> userCapeMap;

    public static final String customCapesPath = System.getProperty("user.home") + File.separator + "CustomCapes" + File.separator;

    private static File getFile(String filePath, boolean directory) {
        File file = new File(filePath);
        if(!file.exists()) {
            if(directory) {
                file.mkdir();
            }  else {
                try {
                    file.createNewFile();
                }catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }

        return file;
    }

    static {

        getFile(customCapesPath, true);

        exemptUsers = new ArrayList<>();

        File exemptUsersFile = getFile(customCapesPath + "exempt_users.txt", false);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(exemptUsersFile));

            String line;
            while((line = reader.readLine()) != null) {
                exemptUsers.add(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String capeMapJson="";
        File userCapeMapFile = getFile(customCapesPath + "cape_paths.json", false);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(userCapeMapFile));

            String line;
            while((line = reader.readLine()) != null) {
                capeMapJson += line;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        JSONObject object = new JSONObject(capeMapJson);
        userCapeMap = object.toMap();
    }

    public static ArrayList<String> getExemptUsers() {
        return exemptUsers;
    }

    public static Map<String, Object> getUserCapeMap() {
        return userCapeMap;
    }
}
