package me.ups3t.customcapes;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Settings {

    private static final Map<String, Object> settingMap;

    static {
        settingMap = new JSONObject(FileRead.readAllLines(new File(CapeUtils.APPLICATION_PATH + File.separator + "settings.json"))).toMap();
    }

    public static Map<String, Object> getSettings() {
        return settingMap;
    }
}
