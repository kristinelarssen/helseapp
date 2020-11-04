package helseapp.json;

import helseapp.core.Dag;
import helseapp.core.Dager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileData {

    private DagPersistance dagPersistance;

    public FileData(DagPersistance dagPercistance){
        this.dagPersistance = dagPercistance;
    }

    /**
     * Save Dager objects to a json-file
     * @param savePath - String path to json-file
     * @param dager - Dager oject
     */
    void save(String savePath, Dager dager){
        if(savePath != null){
            Path path = Paths.get(savePath);
            try(Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)){
                dagPersistance.writeDager(dager, writer);
            } catch (IOException e){
                System.err.println("Fikk ikke skrevet til dager.json på hjemme området");
            }
        }
    }

    /**
     * Read json-file and create Dager objects
     * @param loadPath - String path to json-file
     * @return dager - Dager object
     */
    public Dager read(String loadPath){
        DagPersistance dagPersistence = dagPersistance;
        Reader reader = null;
        Dager dager = null;
        if(loadPath != null) {
            try {
                reader = new FileReader(Paths.get(loadPath).toFile(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.err.println("Feil!");
            }
        }
        try {
            dager = dagPersistence.readDager(reader);
        } catch (IOException e) {
            System.err.println("Feil_2");
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Feil_3");
                }
            }
        }
        return dager;
    }

    /**
     * Handles saving of a single Dag object, makes sure that the same day is not saved twice, and that the last
     * registration is the one that is saved
     * @param filePath - String path to json-file
     * @param dag - Dag object
     */
    public void saveDag(Dag dag, String filePath){
        Dager dager = read(filePath);
        for(int i = 0; i < dager.getDagCount(); i++) {
            if(dager.getDag(i).getDate().toString().equals(dag.getDate().toString())) {
                dager.removeDag(i);
            }
        }
        dager.addDag(dag);
        save(filePath, dager);
    }

}
