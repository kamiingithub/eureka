package com.netflix.eureka.util;

/**
 * @author linyilong
 * @createTime 2021/3/1 3:26 下午
 * @description
 */

import java.io.BufferedReader;
import java.io.File;import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class cal {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        //获取所要查询文件夹路径
        String path = "/Users/linyilong/Ideaprojects/architect/raw/eureka";
        myCodeCount(new File(path));
        System.out.println("总代码行数为"+count);
    }
    /**
     * 递归统计代码总行数
     * @throws IOException
     */
    public static void myCodeCount(File file) throws IOException {
        //文件是否为普通文件并以.java为后缀结尾
        if (file.isFile() && (file.getName().endsWith(".java")) && !file.getName().contains("Test")) {
            int i = readData(file);
            count += i;
        }
        if (file.isDirectory()) {//测试此抽象路径名表示的文件是否为目录
            File[] files = file.listFiles();
            for (File f : files) {
                File[] files2 = f.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.getName().endsWith(".java")) {
                            return true;
                        }
                        return false;
                    }
                });
                myCodeCount(f);
            }
        }
    }
    /**
     * 单个文件中的行数
     */
    public static int readData(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String str = null;
        int i = 0;
        while ((str = br.readLine())!=null) {
            i++;
        }
        br.close();
        System.out.println(file.getName()+"的行数为"+i);
        return i;
    }
}
