package Util;

import java.io.*;

public class WriterToFile {

    String nameOfFile;
    BufferedWriter fileWriter;

    public WriterToFile(String nameOfFile) throws IOException {
        this.nameOfFile = nameOfFile;
        fileWriter = new BufferedWriter(new FileWriter(nameOfFile));
    }

    public void writeToFile(String string) throws IOException {
        File file = new File("output.txt");
        fileWriter.append(string);
        fileWriter.close();
    }
}
