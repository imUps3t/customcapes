package me.ups3t.customcapes;

import java.io.File;
import java.io.IOException;

public class Utils {

    public static File getFile(String filePath, boolean directory) {
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

}
