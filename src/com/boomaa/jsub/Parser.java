package com.boomaa.jsub;

import com.boomaa.jsub.parseobj.Block;
import com.boomaa.jsub.parseobj.Class;
import com.boomaa.jsub.parseobj.Method;
import com.boomaa.jsub.parseobj.Variable;

import java.io.FileInputStream;
import java.io.IOException;

public class Parser {
    private static ClassHierarchy classHierarchy = null;

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        String input = new String(fis.readAllBytes());
        String[] lineInput = input.replaceAll("\r", "").split("\n");
        for (int i = 0;i < lineInput.length;i++) {
            String[] spcLine = lineInput[i].split(" ");
            Block current = null;
            for (int j = 0;j < spcLine.length;j++) {
                String cline = spcLine[j].trim();
                if (cline.equals("{")) {
                    current = newBlock(spcLine[j - 2], spcLine[j - 1]);
                }
                if (cline.equals("}")) {
                    //TODO figure out how to end blocks
                    current.setClosed();
                }
            }
        }
        System.out.println(classHierarchy);
    }

    private static Block newBlock(String keyword, String name) {
        Block block = null;
        switch (keyword.trim()) {
            case "class":
                block = new Class(name);
                break;
            case "method":
                block = new Method.Builder(name);
                break;
            case "variable":
                block = new Variable<>(name);
        }
        assignClassHierarchy(block);
        return block;
    }

    private static void endBlock(Block toEnd) {

    }

    private static void assignClassHierarchy(Block query) {
        if (query instanceof Class) {
            Class qc = (Class) query;
            if (classHierarchy == null) {
                classHierarchy = new ClassHierarchy(qc);
            } else {
                classHierarchy.addRelativeClass(RelativeSelector.SUB, qc);
            }
        } else if (query instanceof Method.Builder) {
            if (classHierarchy == null || classHierarchy.master() == null) {
                throw new IllegalStateException("Master class has not been established as required before adding methods");
            }
            Method.Builder mb = (Method.Builder) query;
//            mb = mb.setAction().setEnclosingClass().setParameterTypes()
                    //TODO sort out the builder stuff with method/variable types
            classHierarchy.current().registerMethod(mb.build());
        } else if (query instanceof Variable) {

        }
    }

    public static ClassHierarchy getClassHierarchy() {
        return classHierarchy;
    }
}
