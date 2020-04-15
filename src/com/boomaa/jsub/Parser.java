package com.boomaa.jsub;

import com.boomaa.jsub.parseobj.*;
import com.boomaa.jsub.parseobj.Class;

import java.io.FileInputStream;
import java.io.IOException;

public class Parser {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        String input = new String(fis.readAllBytes());
        String[] lineInput = input.replaceAll("\r\n", "").replaceAll("[ ]{2,}", "").trim().split("\\{");

        Block current = null;
        Class last = null;
        for (int i = 0;i < lineInput.length;i++) {
            Block temp = null;
            if ((temp = newBlock(lineInput[i])) != null) {
                if (current != null && current instanceof Class) {
                    last = (Class) current;
                }
                current = temp;
            }
            if (current instanceof Class && last != null) {
                last.addRelation(RelationSelector.NESTED, (Class) current);
            }
        }
        System.out.println();
    }

    public static Block newBlock(String lineInput) {
        Block block = null;
        lineInput = lineInput.trim();
        if (lineInput.contains("class")) {
            block = new Class(lineInput.substring(lineInput.indexOf("class") + 6));
        } else if (lineInput.contains("method")) {
            block = new Method.Builder(lineInput.substring(lineInput.indexOf("method") + 7));
        } else if (lineInput.contains("if")) {
            block = new Conditional(lineInput.substring(lineInput.indexOf("if") + 3));
        }
        return block;
    }

    private static void assignHierarchy(Block query) {
        if (query instanceof Class) {
            Class qc = (Class) query;
        } else if (query instanceof Method.Builder) {
        } else if (query instanceof Variable) {
        } else if (query instanceof Conditional) {
        }
    }
}
