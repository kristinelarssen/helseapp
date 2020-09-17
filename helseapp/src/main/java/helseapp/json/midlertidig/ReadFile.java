package helseapp.json.midlertidig;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile {

    public static String[] OpenFile(String path) throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);

        int numberOfLines = readLines(path);
        String[] textData = new String[numberOfLines];

        int i;

        for (i = 0; i < numberOfLines; i++){
            textData[i] = textReader.readLine();
        }
        textReader.close();
        return textData;
    }

    private static int readLines(String path) throws IOException{

        FileReader fileToRead = new FileReader(path);
        BufferedReader bf = new BufferedReader(fileToRead);

        int numberOfLines = 0;

        while ((bf.readLine())!= null){
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }

}
