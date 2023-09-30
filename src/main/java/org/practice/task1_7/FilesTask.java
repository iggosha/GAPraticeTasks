package org.practice.task1_7;

import java.io.*;
import java.util.Scanner;

public class FilesTask {

    public static void main(String[] args) {
        FilesTask task = new FilesTask();
        task.callConsoleReader();
    }

    private void callConsoleReader() {
        Scanner read = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command (create|read|write|delete):");
            String[] commandParts = read.nextLine().split(" ");
            String filePath = commandParts[0];
            String action = commandParts[1];
            switch (action) {
                case "-create" -> {
                    createFile(filePath);
                }
                case "-write" -> {
                    boolean toAppend = !commandParts[2].equals("-rw");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 3; i < commandParts.length; i++) {
                        stringBuilder.append(commandParts[i]).append(' ');
                    }
                    String content = stringBuilder.toString();
                    writeFile(filePath, toAppend, content);
                }
                case "-read" -> {
                    readFile(filePath);
                }
                case "-delete" -> {
                    deleteFile(filePath);
                }
                case "q" -> {
                    return;
                }
                default -> {
                    System.err.println("Wrong console command");
                }
            }
        }
    }

    private void createFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    private void readFile(String filePath) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
                fileContent.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        System.out.println(fileContent);
    }

    private void writeFile(String filePath, boolean toAppend, String content) {
        try {
            if (new File(filePath).exists()) {
                try (FileWriter fw = new FileWriter(filePath, toAppend)) {
                    fw.write(content);
                    fw.flush();
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
            } else {
                throw new FileNotFoundException("File doesn't exist");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//TODO help, exc