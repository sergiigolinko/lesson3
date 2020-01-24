package les3;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileManager {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int option = -1;
        while (option != 0) {
            System.out.println("Please choose an option: 1 - contents of the directory; 2 - directory creation; " +
                    "3 - file creation; 4 - file renaming; 5 - writing to file; " +
                    "6 - file reading; 7 - copy file or directory; 8 - exit");

            option = SCANNER.nextInt();

            switch (option) {
                case 1: {
                    directoryList();
                    break;
                }
                case 2: {
                    directoryCreation();
                    break;
                }
                case 3: {
                    creationFile();
                    break;
                }
                case 4: {
                    renamingFile();
                }
                case 5: {
                    writeToFile();
                }
                case 6: {
                    readFile();
                }
                case 7: {
                    copyFilesOrDirectories();
                }
                case 8: {
                    System.exit(8);
                }
                default: {
                    System.out.println("Option not found!");
                }
            }
        }

    }

    static void directoryList() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter directory path");

        Path path = Paths.get(input.nextLine());
        if (Files.exists(path)) {

            System.out.printf("%n%s", path.getFileName());
            System.out.printf("Path:%n", path);

            System.out.printf("%s,a directory%n", Files.isDirectory(path));


            if (Files.isDirectory(path)) {
                try {
                    System.out.printf("%nDirectory contents:%n");
                    DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
                    for (Path p : directoryStream)
                        System.out.println(p);
                } catch (IOException e) {
                    e.getStackTrace();
                }

            }
        } else {
            System.out.printf("%s does not exist%n", path);
        }
    }

    static void directoryCreation() {
        Scanner input = new Scanner(System.in);
        boolean success;
        System.out.println("Enter path of directory to create");
        String dir = input.nextLine();
        File directory = new File(dir);
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("Directory already exists ...");
        } else {
            System.out.println("Directory not exists,creating now");
            success = directory.mkdir();
            if (success) {
                System.out.printf("Successfully created new directory:%s%n", dir);
            }
        }
    }

    static void renamingFile() {
        Scanner input = new Scanner(System.in);
        boolean success;
        System.out.println("Enter file name path to be renamed");
        String filename = input.nextLine();
        File f = new File(filename);
        if (f.exists() && f.isFile()) {
            System.out.println("Rename file name path");
            String rename = input.nextLine();
            File h = new File(rename);
            success = f.renameTo(h);

            if (h.exists() && success) {
                System.out.println("The file was renamed");
            } else {
                System.out.printf("Failed to created new name :%s%n", h);
            }

        }
    }

    static void creationFile() {
        System.out.println("Enter path for creation");
        Scanner input = new Scanner(System.in);

        Path path = Paths.get(input.nextLine());
        File newFile = new File(path + "newFile.txt");
        try {
            if (newFile.createNewFile()) {
                System.out.println("newFile.txt has created");
            } else {
                System.out.println("newFile.txt has already exists");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    static void writeToFile() {
        System.out.println("Enter file path for writing");
        Scanner input = new Scanner(System.in);
        Path path = Paths.get(input.nextLine());
        if (Files.exists(path)) {
            try {

                FileWriter fileWriter = new FileWriter(path + "newFile.txt");
                fileWriter.write("This file is readable.");
                System.out.println("The information has written!");
                fileWriter.close();

            } catch (IOException e) {
                e.getStackTrace();
            }
        } else {
            System.out.println("ERROR --> You must create file!");
        }

    }

    static void readFile() {
        System.out.println("Enter file path for reading");
        Scanner input = new Scanner(System.in);
        Path path = Paths.get(input.nextLine());
        if (Files.exists(path)) {
            try {
                FileReader fileReader = new FileReader(path + "newFile.txt");
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                System.out.println("The file has read!");
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.getStackTrace();
            }
        } else {
            System.out.println("ERROR -->  You must create ans write file!");
        }
    }

    static void copyFilesOrDirectories() {
        System.out.println("Enter source path and object for copying");
        Scanner input1 = new Scanner(System.in);
        Path source = Paths.get(input1.nextLine());
        System.out.println("Enter destination path and object for copying");
        Scanner input2 = new Scanner(System.in);
        Path destination = Paths.get(input2.nextLine());
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println("The copying has finished!");

    }

}






