package com.mua.backupbymaifee.util;

import java.io.File;

public class FileUtil {

    public static Boolean isAvailable(String absolutePath){
        return !new File(absolutePath).exists();
    }

    public static String getAvailablePath(String absolutePath){
        while (!isAvailable(absolutePath)){
            absolutePath+="|";
        }
        return absolutePath;
    }

}
