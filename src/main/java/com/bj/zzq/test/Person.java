package com.bj.zzq.test;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/5/25
 * @Description:
 */
public class Person implements IPerson {
    public final void init(final String name) throws IllegalArgumentException {

    }

    public static void main(String[] args){
        int[] key = getNext("abaabcac");
        String s="";
        String s2="";
        for (int i = 0; i <key.length ; i++) {
            s=s+key[i];
            s2=s2+(key[i]+1);
        }
        System.out.println(s);
        System.out.println(s2);
    }
    public static int[] getNext(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                next[++j] = ++k;

            } else {

                k = next[k];

            }

        }

        return next;

    }
}