package helseapp.json.midlertidig;

import java.io.IOException;

public class FileData {

    public static void main(String[] args) throws IOException {
        String file_name = "helseapp/src/main/java/helseapp/json/midlertidig/vekt.txt";

        // Read from file:
        try {
            ReadFile file = new ReadFile(file_name);
            String[] arryLines = file.OpenFile();
            int i;
            for (i = 0; i < arryLines.length; i++){
                System.out.println(arryLines[i]);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Write to file:
        try {
            WriteFile data = new WriteFile(file_name, true);
            data.writeToFile("Test");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
