package com.ilearning.generate.file;

import java.io.File;
import java.io.IOException;

public class GenFileUtil {
    /**
     * 判断文件是否存在，不存在就创建
     *
     * @param file
     */
    public static boolean createFile(File file) {
        if (file.exists()) {
            System.out.println(file.getName() + " exists");
            return false;
        } else {
            System.out.println(file.getName() +  " not exists, create it ...");
            //getParentFile() 获取上级目录(包含文件名时无法直接创建目录的)
            if (!file.getParentFile().exists()) {
                System.out.println(file.getParentFile().getName() +" not exists");
                //创建上级目录
                file.getParentFile().mkdirs();
            }
            try {
                //在上级目录里创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
