package org.practice.task1_7;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class FilesTask {

    public static void main(String[] args) {
        FilesTask task = new FilesTask();
        while (true) {
            try {
                task.callConsoleReader();
            } catch (IllegalArgumentException e) {
                System.err.println("Wrong console command");
            } catch (Exception e) {
                if (e.getMessage().equals("q")) {
                    break;
                } else {
                    throw new RuntimeException();
                }
            }
        }
    }

    private void callConsoleReader() throws Exception {
        Scanner read = new Scanner(System.in);
        System.out.println("Write \": ?\" for help");
        while (true) {
            System.out.println("\nEnter command: ");
            String[] commandParts = read.nextLine().split(" ");
            String filePath = commandParts[0];
            String action;
            if (commandParts.length > 1) {
                action = commandParts[1];
            } else {
                throw new IllegalArgumentException("Wrong console command");
            }
            switch (action) {
                case "-create" -> createFile(filePath);
                case "-write" -> {
                    boolean toAppend = !commandParts[2].equals("-rw");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 3; i < commandParts.length; i++) {
                        stringBuilder.append(commandParts[i]).append(' ');
                    }
                    String content = stringBuilder.toString();
                    writeFile(filePath, toAppend, content);
                }
                case "-read" -> readFile(filePath);
                case "-delete" -> deleteFile(filePath);
                case "q" -> throw new Exception("q");
                case "?" -> System.out.println("""
                        'filepath' -create
                        'filepath' -write -rw/-a (rewrite/append) 'content'
                        'filepath' -read
                        'filepath' -delete
                        ': q' to quit
                        """);
                default -> System.err.println("Wrong console command");
            }
        }
    }

    private void createFile(String filePath) {
        File file;
        try {
            file = new File(filePath);
            boolean created = file.createNewFile();
            if (!created) {
                throw new FileAlreadyExistsException("File " + filePath + " already exists");
            } else {
                System.out.println("File successfully created");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    private void deleteFile(String filePath) {
        File file;
        try {
            file = new File(filePath);
            boolean deleted = file.delete();
            if (!deleted) {
                throw new FileNotFoundException("File " + filePath + " not found");
            } else {
                System.out.println("File successfully deleted");
            }
        } catch (
                IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
        }
    }


    private void readFile(String filePath) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent
                        .append(line)
                        .append('\n');
            }
            if (!fileContent.isEmpty()) {
                System.out.println(fileContent);
            } else {
                System.err.println("File " + filePath + " is empty");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private void writeFile(String filePath, boolean toAppend, String content) {
        try {
            if (!new File(filePath).exists()) {
                throw new FileNotFoundException("File " + filePath + " not found");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return;
        }
        try (FileWriter fw = new FileWriter(filePath, toAppend)) {
            fw.write(content);
            fw.flush();
            System.out.println("Content successfully written");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}