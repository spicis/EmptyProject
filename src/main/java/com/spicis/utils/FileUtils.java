package com.spicis.utils;

import com.spicis.logger.LogFactory;

import java.io.*;

public class FileUtils {

    public static String getJsonFile(String file) {
        Reader fileReader = null;
        Reader reader = null;
        String jsonStr = "";
        try {
            File jsonFile = new File(file);
            fileReader = new FileReader(jsonFile);

            reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            jsonStr = sb.toString();
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("文件解析异常", e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                LogFactory.getErrorLogger().logError("文件解析异常", e);
            }
        }

        return jsonStr;
    }

}
