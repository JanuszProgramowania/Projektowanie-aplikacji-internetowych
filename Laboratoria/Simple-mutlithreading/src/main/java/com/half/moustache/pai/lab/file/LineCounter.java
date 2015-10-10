package com.half.moustache.pai.lab.file;

/**
 * Created by piotr on 10.10.15.
 */
public class LineCounter implements Runnable{
    private Thread t;

    private String fileName;

    private FileHandler fileHandler;

    private int lines = 0;

    public LineCounter(String fileName) {
        this.fileName = fileName;
        fileHandler = new FileHandler(fileName);
    }

    public int getLines() {
        return lines;
    }

    public void run() {
        while (fileHandler.readLine() != null) lines++;
        fileHandler.close();
        System.out.println("FILE " + this.fileName + " LINES " + this.getLines());
    }

    public Thread start(){
        if (t == null)
        {
            t = new Thread(this);
            t.start ();
        }
        return t;
    }
}
