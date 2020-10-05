package helseapp.json.midlertidig;

import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
    public static void writeToFile(String textLine, String path, boolean appendToFile) throws IOException{
        java.io.FileWriter write = new java.io.FileWriter(path, appendToFile);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s" + "%n", textLine);
        print_line.close();
    }
}
