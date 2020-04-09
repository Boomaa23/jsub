package com.boomaa.jsub;

import java.io.FileInputStream;
import java.io.IOException;

public class Parser {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        String input = new String(fis.readAllBytes());
        String[] lineInput = input.split("\n");
    }
}
