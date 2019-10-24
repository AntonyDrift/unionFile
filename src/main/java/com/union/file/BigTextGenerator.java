package com.union.file;

import java.io.*;

public class BigTextGenerator {

    public void generate(File file, int multiplier) {

        File output = new File(file.getPath() + "_x.factor");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));

            String line = reader.readLine();

            for (int i = 0; i < multiplier; i++) {
                while (line != null) {
                    if (line != "") {
                        writer.write(line);
                        line = reader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
