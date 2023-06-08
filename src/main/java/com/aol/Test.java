package com.aol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        String[][] employees = {
                { "Man", "Sparkes", "msparkes0@springhow.com", "Engineering" },
                { "Dulcinea", "Terzi", "dterzi1@springhow.com", "Engineering" },
                { "Tamar", "Bedder", "tbedder2@springhow.com", "Legal" },
                { "Vance", "Scouller", "vscouller3@springhow.com", "Sales" },
                { "Gran", "Jagoe", "gjagoe4@springhow.com", "Business Development" }
        };

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().toUpperCase());

        String[] code = { uuid.toString().toUpperCase(), "2" };
        File csvFile = new File("new.csv");
        try (FileWriter fileWriter = new FileWriter(csvFile, true)) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < code.length; i++) {
                line.append(code[i]);
                if (i != code.length - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
