package com.half.moustache.pai.lab.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by piotr on 10.10.15.
 */
public class FileHandler {
    private String fileName;

    private BufferedReader bufferedReader;

    private FileReader fileReader;

    public FileHandler(String fileName) {
        this.fileName = fileName;
        try {
            fileReader = new FileReader(this.fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bufferedReader = new BufferedReader(this.fileReader);
    }

    public String readLine(){
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
