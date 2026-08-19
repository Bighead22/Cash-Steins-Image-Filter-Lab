import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
}
