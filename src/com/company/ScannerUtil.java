package com.company;

import java.util.Scanner;

public class ScannerUtil {
    public static String getString(String title){
        System.out.println(title);
        return new Scanner(System.in).nextLine();
    }
    public static Integer getInteger(String title){
        System.out.println(title);
        return new Scanner(System.in).nextInt();
    }
}
