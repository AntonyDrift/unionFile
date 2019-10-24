package com.union.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.*;

import java.util.*;
import java.util.List;

@Data
@NoArgsConstructor
public class SorterClass {

    BufferedReader reader;
    BufferedWriter writer;

    static String tmpPath = System.getProperty("user.dir") + "/src/main/resources/files/tmp/";
    static String partPath = tmpPath + "part/";
    static String splitPath = tmpPath + "split/";

    public void deleteFolderOrFile(File inputToDelete) {

        Scanner sc = new Scanner(System.in);
        String type = "";

        if (inputToDelete.isFile()) {
            type = "file:" + inputToDelete.getName();

        } else if (inputToDelete.isDirectory()) {
            type = "directory:" + inputToDelete.getName();
        } else {
            System.out.println("Attention, wrong type!!! Enter new path");
            System.exit(404);
        }

        System.out.println("You're sure want to delete this " + type + " ?");
        System.out.println("1. Yes" + "\n" + "Other input: No");

        String next;
        switch (next = sc.next()) {
            case "1": {
//                inputToDelete.delete();
                inputToDelete.delete();
                if(inputToDelete.exists()) {
                    System.out.println("Delete files on your directory\nPath:" + inputToDelete.getPath());
                }
            }
            default: {
                System.out.println("Bye-Bye");
                System.exit(404);
            }
        }
    }

    public void mergeSortedFiles(File folder, int amountFiles) {

        File[] myFiles = folder.listFiles();

        int counterSplitter = 0;
        int outerCycle = 0;
        long counterLines = 0;

        int filesLength = myFiles.length * amountFiles;
        long amountPartedFiles = 0;


        for (int i = 0; i < myFiles.length; i++) {

            try {
                reader = new BufferedReader(new FileReader(myFiles[i]));
                counterLines += reader.lines().count();

                if (counterLines % filesLength == 0) {
                    amountPartedFiles = counterLines / filesLength;
                } else amountPartedFiles = (counterLines / filesLength) + 1;

                System.out.println("amountPartedFiles: " + amountPartedFiles);


                while (outerCycle < amountPartedFiles) {

                    List<String> tmp = new ArrayList<>();
                    int indexTmp = 0;

                    for (int each = 0; each < myFiles.length; each++) {

                        reader = new BufferedReader(new FileReader(myFiles[each]));

                        String line = reader.readLine();

                        int check = outerCycle * amountFiles;
                        while (check > 0) {
                            line = reader.readLine();
                            check--;
                            continue;
                        }

                        while (line != null && counterSplitter < amountFiles) {
                            tmp.add(line);
                            line = reader.readLine();
                            counterSplitter++;
                        }
                        counterSplitter = 0;
                        reader.close();
                    }

                    Collections.sort(tmp);

                    File outFile = new File(partPath + myFiles[0].getName()
                            + "_" + outerCycle + ".part");

                    writer = new BufferedWriter(new FileWriter(outFile));

                    for (String s : tmp) {
                        writer.write(s);
                        writer.newLine();
                    }
                    writer.close();

                    outerCycle++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void splitFile(File original, Integer amountNeededLines) {


        long counterLines = 0;
        int counterFiles = 1;

        try {
            reader = new BufferedReader(new FileReader(original));
            String line = reader.readLine();

            String outfileName = splitPath + original.getName();

            while (line != null) {
                File outFile = new File(outfileName + "_" + counterFiles + ".split");
                BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

                while (line != null && counterLines < amountNeededLines) {
                    writer.write(line);
                    writer.newLine();
                    line = reader.readLine();
                    counterLines++;
                }
                writer.close();
                counterLines = 0;
                counterFiles++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortSplitterFile(File folder) {

        File[] files = folder.listFiles();

        List<File> fileList = new ArrayList<>();

        for (File file : files) {

            if (file.isFile()) {
                fileList.add(file);
            }
        }
        for (File file : fileList) {
            try {
                reader = new BufferedReader(new FileReader(file));

                String line = reader.readLine();
                List<String> sortedLines = new ArrayList<>();

                while (line != null) {
                    sortedLines.add(line);
                    line = reader.readLine();
                }
                sortedLines.sort(String::compareTo);

                writer = new BufferedWriter(new FileWriter(file));

                for (String sortedLine : sortedLines) {
                    writer.write(sortedLine);
                    writer.newLine();
                }
                writer.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void mergeToFile(File folder) {

        File[] files = folder.listFiles();
        File output = new File(tmpPath + "result/" + files[0].getName() + "_sorted.file");

        for (File file : files) {
            try {
                reader = new BufferedReader(new FileReader(file));
                FileWriter fr = new FileWriter(output, true);
                writer = new BufferedWriter(fr);

                String line = reader.readLine();
                while (line != null) {
                    writer.write(line);
                    line = reader.readLine();
                }
                reader.close();
                writer.close();
                fr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}