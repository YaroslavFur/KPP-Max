package MainPackage;

import Files.FileManager;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter from file or console? (f/c)");
        String answer = scanner.nextLine();
        if (Objects.equals(answer, "f") || Objects.equals(answer, "F"))
        {
            String stringInput = new FileManager().readFromFile("Text");
            System.out.println(stringInput);
            String output = RegexManager.getSecondLastWordFromEachWord(stringInput);
            System.out.println(output);
        }
        System.out.println("Enter \"E\" to exit");
        String input;
        while (true) {
            System.out.println("Enter text:");
            input = scanner.nextLine();
            if (Objects.equals(input, "E") || Objects.equals(input, "e"))
            {
                System.out.println("Exit");
                break;
            }
            String output = RegexManager.getSecondLastWordFromEachWord(input);
            System.out.println(output);
            System.out.println();
        }

    }
}
