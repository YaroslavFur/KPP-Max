package Files;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public String readFromFile(String fileName) {
        fileName += ".txt";
        String stringFromFile = "";
        try (FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            for (int i = 0; scanner.hasNextLine(); i++) {
                stringFromFile += (scanner.nextLine());
                stringFromFile += "\n";
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringFromFile;
    }
}
