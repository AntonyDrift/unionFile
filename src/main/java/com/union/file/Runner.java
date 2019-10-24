package com.union.file;

import java.io.File;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        BigTextGenerator textGenerator = new BigTextGenerator();
        SorterClass run = new SorterClass();

        File inputFile = new File(System.getProperty("user.dir") + "/src/main/resources/files/tom2.txt");
        File fileFromDelete = new File(System.getProperty("user.dir") + "/src/main/resources/garbage");
        File tmpFolder = new File(System.getProperty("user.dir") + "/src/main/resources/files/tmp/");
        File splitFolder = new File(System.getProperty("user.dir") + "/src/main/resources/files/tmp/split/");
        File partFolder = new File(System.getProperty("user.dir") + "/src/main/resources/files/tmp/part/");
        File booksFolder = new File(System.getProperty("user.dir") + "/src/main/resources/files/books/");

//        Time: 342 millis
//        textGenerator.generate(inputFile, 100);

        run.deleteFolderOrFile(fileFromDelete);

//        Time: 678 millis
//        run.mergeToFile(booksFolder);

//        Time: 46051 millis
//        run.splitFile(inputFile, 10000);

//        Time: 77833 millis
//        run.sortSplitterFile(tmpFolder);

//        Time: 915089 millis
//        run.mergeSortedFiles(tmpFolder, 100);

//        Time: 52332 millis
//        run.mergeToFile(partFolder);

        System.out.println("Time: " + (System.currentTimeMillis() - start) + " millis");

    }
}
