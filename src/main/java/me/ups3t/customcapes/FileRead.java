package me.ups3t.customcapes;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {

    public static String readAllLines(File file) {
        String text = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while((line = reader.readLine()) != null) {
                text += line;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return text;
    }

    public static List<String> readLinesToList(File file) {
        List<String> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return list;
    }

}
