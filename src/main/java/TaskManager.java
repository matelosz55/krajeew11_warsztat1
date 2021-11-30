import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Menu();
    }

    public static String[][] ReadFile(String directory) {
        Path path = Paths.get(directory);
        int i = 0;
        if (Files.exists(path)) {
            try {
                for (String s : Files.readAllLines(path)) {
                    System.out.println(i + " : " + s);
                    i++;
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return null;

    }

    public static void Menu() {
        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");
        System.out.println(pl.coderslab.ConsoleColors.RESET + "a - add");
        System.out.println("r - remove");
        System.out.println("l - list");
        System.out.println("e - exit");
        Input();
    }

    public static void Otheraction() {
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


    public static void Input() {
        Scanner scanList = new Scanner(System.in);
        String input = scanList.nextLine();
        switch (input) {
            case "l":
                ReadFile("src/main/Files/tasks.csv");
                Otheraction();
                break;
            case "a":
                System.out.println("add");
                Add();
                Otheraction();
                break;
            case "r":
                System.out.println("remove");
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
        //Funkcja zapisu

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

}
