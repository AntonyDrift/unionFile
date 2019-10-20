package com.union.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UnionFile {

    /**
     * Change separator if u have another OS
     */
    private static String path = System.getProperty("user.dir");
    private final File FIRST_FILE = new File(path + "/src/main/resources/files/first.txt");
    private final File LAST_FILE = new File(path + "/src/main/resources/files/last.txt");
    private final File RESULT = new File(path + "/src/main/resources/files/result.txt");

    private List<String> resultList = new ArrayList<>();

    public void runApp() {

        try {
            List<BufferedReader> listReader = new ArrayList<>();
            listReader.add(new BufferedReader(new FileReader(FIRST_FILE)));
            listReader.add(new BufferedReader(new FileReader(LAST_FILE)));

            //Reading lines from files "first.txt", "last.txt" in order
            for (BufferedReader reader : listReader) {

                String tmp = "";
                while ((tmp = reader.readLine()) != null) {
                    resultList.add(tmp);
                }
                reader.close();
            }

            //Sorting readed lines ascending
            Collections.sort(resultList, (first, second) -> first.compareTo(second));

            //Writing sorted result from file "result.txt"
            BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT));

            for (String line : resultList) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            System.out.println("Check your result by path:" + RESULT.getAbsolutePath());

            //Checking exceptions
        } catch (
                FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (
                IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

