package xml;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileUtil {
    
    public static void stringToFile(String source, String fileName) throws IOException {
        FileWriter fw = null;
        File file = new File(fileName);
        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file.getAbsoluteFile());
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(source);
            //System.out.println("Done writing to " + fileName); //For testing
        }
        fw.close();
    }    
    
}
