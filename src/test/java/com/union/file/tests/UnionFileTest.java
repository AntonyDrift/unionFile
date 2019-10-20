package com.union.file.tests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class UnionFileTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    private List<String> testList = Arrays.asList("0", "1", "2", "4", "4", "7", "8");
    private static String path = System.getProperty("user.dir");

    @Test
    public void compareResultFiles() {

        try {
            final File tmpFile = tmpFolder.newFile("tmp-result");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));

            for (String line : testList) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

            File actualFile = new File(path + "/src/main/resources/files/result.txt");

            BufferedReader expectedReader = new BufferedReader(new FileReader(tmpFile));
            BufferedReader actualReader = new BufferedReader(new FileReader(actualFile));

            String expectedLine = "";
            while ((expectedLine = expectedReader.readLine()) != null) {
                String actualLine = actualReader.readLine();
                Assert.assertEquals(expectedLine, actualLine);
            }

            actualReader.close();
            expectedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hasResultFile() {

        File file = new File(path + "/src/main/resources/files/result.txt");
        Assert.assertTrue( file.isFile() && file.canRead()) ;
    }

    @Test
    public void hasCorrectNameFiles() {

        File folder = new File(path + "/src/main/resources/files/");
        List<File> actualFiles = Arrays.asList(folder.listFiles());
        List<String> expectedNameFiles = Arrays.asList("first.txt", "last.txt", "result.txt");

        for (File file : actualFiles) {
            Assert.assertTrue(expectedNameFiles.contains(file.getName()));
        }
    }
}
