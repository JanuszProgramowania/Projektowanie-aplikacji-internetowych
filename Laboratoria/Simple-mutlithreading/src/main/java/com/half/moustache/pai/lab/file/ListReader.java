package com.half.moustache.pai.lab.file;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 10.10.15.
 */
public class ListReader {
    private String listPath;

    private FileHandler fileHandler;

    private List<Thread> allThreads = new ArrayList<Thread>();

    public ListReader(String listPath) {
        this.listPath = listPath;
        fileHandler = new FileHandler(listPath);
    }

    public void actSingleThread(){
        String filePath = fileHandler.readLine();
        while(filePath != null){
            LineCounter lineCounter = new LineCounter(filePath);
            lineCounter.run();
            filePath=fileHandler.readLine();
        }
        fileHandler.close();
    }

    public void actMultiThread(){
        String filePath = fileHandler.readLine();
        while(filePath != null){
            LineCounter lineCounter = new LineCounter(filePath);
            allThreads.add(lineCounter.start());
            filePath=fileHandler.readLine();
        }
        fileHandler.close();
        for (Thread t : allThreads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
