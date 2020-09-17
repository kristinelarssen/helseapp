package helseapp.json.midlertidig;

import java.io.IOException;

public class FileData {

    public static void main(String[] args) {
        String file_name = "helseapp/src/main/java/helseapp/json/midlertidig/test.txt";

        // Write to file:
        try {
            WriteFile.writeToFile("Test", "helseapp/src/main/java/helseapp/json/midlertidig/test.txt", false);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        // Read from file:
        try {
            String[] arryLines = ReadFile.OpenFile(file_name);
            int i;
            for (i = 0; i < arryLines.length; i++){
                System.out.println(arryLines[i]);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
