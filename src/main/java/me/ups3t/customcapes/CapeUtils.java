package me.ups3t.customcapes;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class CapeUtils {

    private static ArrayList<String> exemptUsers;
    private static Map<String, Object> userCapeMap;

    public static final String APPLICATION_PATH = System.getProperty("user.dir");
    public static final String CAPES_PATH = APPLICATION_PATH + File.separator + "capes";

    static {
        Utils.getFile(APPLICATION_PATH, true);

        exemptUsers = new ArrayList<>();

        File exemptUsersFile = Utils.getFile(APPLICATION_PATH + File.separator + "exempt_users.txt", false);
        exemptUsers.addAll(FileRead.readLinesToList(exemptUsersFile));

        File userCapeMapFile = Utils.getFile(APPLICATION_PATH + File.separator + "cape_paths.json", false);
        String capeMapJson = FileRead.readAllLines(userCapeMapFile);

        userCapeMap = new JSONObject(capeMapJson).toMap();
    }

    public static ArrayList<String> getExemptUsers() {
        return exemptUsers;
    }

    public static Map<String, Object> getUserCapeMap() {
        return userCapeMap;
    }
}
