import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) throws FileNotFoundException {

        Menu();
    }

    public static void ReadFile(String directory) {
        Path path = Paths.get(directory);
        int i = 0;
        if (Files.exists(path)) {
            try {
                if (NumberOfLines() == 0) {
                    System.out.println("No records in file, add records.");
                } else {
                    for (String s : Files.readAllLines(path)) {
                        System.out.println(i + " : " + s);
                        i++;
                    }
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    public static void Menu() throws FileNotFoundException {
        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");
        System.out.println(pl.coderslab.ConsoleColors.RESET + "a - add");
        System.out.println("r - remove");
        System.out.println("l - list");
        System.out.println("e - exit");
        Input();
    }

    public static void Otheraction() throws FileNotFoundException {
        System.out.println("\n" + "Would you like to perform other action? (y/n)");
        Scanner scanList = new Scanner(System.in);
        String input = scanList.nextLine();
        switch (input) {
            case "y":
                Menu();
                break;
            case "n":
                break;
        }
    }


    public static void Input() throws FileNotFoundException {
        Scanner scanList = new Scanner(System.in);
        String input = scanList.nextLine();
        switch (input) {
            case "l":
                ReadFile("src/main/Files/tasks.csv");
                Otheraction();
                break;
            case "a":
                Add();
                Otheraction();
                break;
            case "r":

                RemoveFunction();
                Otheraction();
                break;
            case "e":
                break;
        }

    }

    public static void Add() {
        System.out.println("Add task description, please");
        Scanner scan = new Scanner(System.in);
        String excerciseInput = scan.nextLine();

        System.out.println("Add task due date, please");
        Scanner scan1 = new Scanner(System.in);
        String dueDate = scan1.nextLine();

        System.out.println("Is your task important? (true/false)");
        Scanner scan2 = new Scanner(System.in);
        String importance = scan2.nextLine();

        Path path = Paths.get("src/main/Files/tasks.csv");
        List<String> outList = new ArrayList<>();
        String record = excerciseInput + ", " + dueDate + ", " + importance;
        try {
            Files.writeString(path, "\n", StandardOpenOption.APPEND);
            Files.writeString(path, record, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Cannot save this line to the file.");
        }
    }

    public static int NumberOfLines() {
        Path path = Paths.get("src/main/Files/tasks.csv");
        int j = 0;
        try {
            for (String s : Files.readAllLines(path)) {
                j++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return j;
    }

    public static void RemoveFunction() throws FileNotFoundException {
        Path path = Paths.get("src/main/Files/tasks.csv");
        if (NumberOfLines() == 0) {
            System.out.println("No records in current file.");
        } else {


            System.out.println("Type line number to remove.");
            Scanner scan = new Scanner(System.in);
            int numberOfLine = 0;
            String numberOfLineString = scan.nextLine();
            if (!NumberUtils.isParsable(numberOfLineString)) {
                System.out.println("Type value by number."+ "\n");
                RemoveFunction();

            } else {
                numberOfLine = Integer.parseInt(numberOfLineString);
                if (numberOfLine > -1 && numberOfLine < NumberOfLines() && NumberOfLines() != 0) {

                    String[] table = new String[NumberOfLines()];

                    int j = 0;
                    try {
                        for (String s : Files.readAllLines(path)) {
                            table[j] = s;
                            j++;
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                    String[] result = ArrayUtils.remove(table, numberOfLine);
                    List<String> outList = new ArrayList<>();


                    if (NumberOfLines() == 0) {
                        System.out.println("There are no records left in the file.");
                    } else if (NumberOfLines() == 1) {
                        PrintWriter pw = new PrintWriter("src/main/Files/tasks.csv");
                        pw.close();


                    } else {
                        try {
                            for (int k = 0; k < result.length; k++) {
                                outList.add(result[k]);
                                Files.write(path, outList);

                            }
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }

                } else {
                    System.out.println("Provided number out of database range - number of lines in current file: " + NumberOfLines());
                }
            }
        }


    }

}
