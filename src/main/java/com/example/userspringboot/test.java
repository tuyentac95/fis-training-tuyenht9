package com.example.userspringboot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\tuyen\\AppData\\Local\\Temp\\MicrosoftEdgeDownloads\\0d7e4f43-bb0c-4cff-ab9f-cc5cf61f27c1\\access-code.csv"));
        //parsing a CSV file into the constructor of Scanner class
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dell\\Desktop\\csvDemo.csv"));
        //setting comma as delimiter pattern
        while (sc.hasNext()) {
            System.out.print(sc.next());
            System.out.println();
        }
        sc.close();
        //closes the scanner
    }
}
