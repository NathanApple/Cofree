package com.aol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String[][] employees = {
                { "Man", "Sparkes", "msparkes0@springhow.com", "Engineering" },
                { "Dulcinea", "Terzi", "dterzi1@springhow.com", "Engineering" },
                { "Tamar", "Bedder", "tbedder2@springhow.com", "Legal" },
                { "Vance", "Scouller", "vscouller3@springhow.com", "Sales" },
                { "Gran", "Jagoe", "gjagoe4@springhow.com", "Business Development" }
        };
        File csvFile = new File("employees.csv");
        try (FileWriter fileWriter = new FileWriter(csvFile, true)) {
            for (String[] data : employees) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < data.length; i++) {
                    line.append(data[i]);
                    if (i != data.length - 1) {
                        line.append(',');
                    }
                }
                line.append("\n");
                fileWriter.write(line.toString());
            }
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
