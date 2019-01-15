package com.bj.zzq.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class CommonUtils {
    public static String newUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\18511\\Desktop\\think_dept.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        File file1 = new File("C:\\Users\\18511\\Desktop\\update.sql");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1)));
        String s = "";
        ArrayList<Object> list = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            String[] split = s.split(",");
            String deptId = split[0];
            deptId = deptId.replace("\"", "");
            String name = split[1];
            name = name.replace("\"", "");
            String depttype = split[2];
            depttype = depttype.replace("\"", "");
            //1-中科院重点实验室 2-国家研究中心 3-国家重点实验室
            //1-中国科学院饮用水科学与技术重点实验室 2-北京分子科学国家研究中心 3-高分子物理与化学国家重点实验室

            String reg = "";
            if ("1".equals(depttype)) {
                reg = "^中国科学院.+重点实验室$";
            } else if ("2".equals(depttype)) {
                reg = "^.+国家研究中心$";
            } else if ("3".equals(depttype)) {
                reg = "^.+国家重点实验室$";
            } else {
                System.out.println("没有depttype得实验室：" + name + " " + deptId + "   " + depttype);
            }
            if (!name.matches(reg)) {
                System.out.println(deptId + " " + name + " " + depttype);
                if ("1".equals(depttype)) {
                    name = "中国科学院" + name + "重点实验室";
                } else if ("2".equals(depttype)) {
                    name = name + "国家研究中心";
                } else if ("3".equals(depttype)) {
                    name = name + "国家重点实验室";
                }
                bw.newLine();
                bw.write("update think_dept set name='" + name + "' where id=" + deptId + ";");
            }

        }
        bw.flush();
    }
}
