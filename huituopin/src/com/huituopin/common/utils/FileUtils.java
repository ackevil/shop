package com.huituopin.common.utils;

import java.io.File;

public class FileUtils {
    public static void main(String[] args) {
        // This is the path where the file's name you want to take.
        String path = "C:/Users/yutu/Desktop/jar";
        getFile(path);
    }

    public static File[] getFile(String path) {
        File file = new File(path);
        File[] array = file.listFiles();
        return array;
        // for (int i = 0; i < array.length; i++) {
        // if (array[i].isFile()) {
        // // only take file name
        // System.out.println("^^^^^" + array[i].getName());
        // // take file path and name
        // System.out.println("#####" + array[i]);
        // // take file path and name
        // System.out.println("*****" + array[i].getPath());
        // }
        // }
    }
}
