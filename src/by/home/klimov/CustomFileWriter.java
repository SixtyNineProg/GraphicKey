package by.home.klimov;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomFileWriter {

    public static void writeToFile(String fileName, String str) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append('\n');
            writer.append(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
