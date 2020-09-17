package helseapp.json.midlertidig;

import java.io.IOException;

public class FileData {

    public static void main(String[] args) throws IOException {
        String file_name = "helseapp/src/main/java/helseapp/json/midlertidig/vekt.txt";

        // Writes to file:
        try {
            WriteFile data = new WriteFile(file_name, true);
            data.writeToFile("Test");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
