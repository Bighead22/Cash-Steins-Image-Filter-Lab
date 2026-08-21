import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileReader {
    public static void appendToFile(String input, String filePath)throws IOException {
        
            Files.write(
                Paths.get(filePath), 
                (input + System.lineSeparator()).getBytes(), 
                StandardOpenOption.CREATE, 
                StandardOpenOption.APPEND
            );
            System.out.println("Saved\n");
        
    }

    public static void keepFirstLines(String filePath, int numLines) throws IOException {
        Path path = Paths.get(filePath);

        List<String> allLines = Files.readAllLines(path);

        if (allLines.size() <= numLines) {
            return;
        }
        
        List<String> remainingLines = allLines.subList(0, numLines);

        Files.write(path, remainingLines);
    }
}

