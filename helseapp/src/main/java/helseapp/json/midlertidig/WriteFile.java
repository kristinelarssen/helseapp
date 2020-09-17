package helseapp.json.midlertidig;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

    private String path;
    private boolean appendToFile = false;

    public WriteFile(String file_path){
        this.path = file_path;
    }

    public WriteFile(String file_path, boolean appendValue){
        this.path = file_path;
        this.appendToFile = appendValue;
    }

    public void writeToFile(String textLine) throws IOException{
        java.io.FileWriter write = new java.io.FileWriter(path, appendToFile);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s" + "%n", textLine);
        print_line.close();
    }
}
