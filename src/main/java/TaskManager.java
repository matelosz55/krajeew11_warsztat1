import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Menu();
    }

    public static String[][] ReadFile(String directory) {
        Path path = Paths.get(directory);
        String[][] fileTable = new String[3][3];
        if (Files.exists(path)) {
            try {
                for (String s : Files.readAllLines(path)) {
                    int i = 0;
                    i++;
                    for (int j = 0; j < fileTable[i].length; j++) {
                        fileTable[i] = s.split(", ");
                    }
                    System.out.println(Arrays.toString(fileTable[i]));
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

    public static void Otheraction(){
        System.out.println("\n" +"Would you like to perform other action? (y/n)");
        Scanner scanList = new Scanner(System.in);
        String input = scanList.nextLine();
        switch (input){
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
        switch(input){
            case "l":
                ReadFile("src/main/Files/tasks.csv");
                Otheraction();
                break;
            case "a":
                System.out.println("add");
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

}
