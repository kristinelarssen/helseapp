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

    public void save(String savePath, Dager dager){
        if(savePath != null){
            Path path = Paths.get(savePath);
            try(Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)){
                dagPersistance.writeDager(dager, writer);
            } catch (IOException e){
                System.err.println("Fikk ikke skrevet til dager.json på hjemme området");
            }
        }
    }

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
        return dager;
    }

    // Metode for å håndtere at en dag ikke lagres flere ganger
    public void saveDag(Dag dag, String filePath){
        Dager dager = read(filePath);
        Dag siste_dag = dager.getDag(dager.getDagCount()-1);
        if(siste_dag.getDate().equals(dag.getDate())){
            dager.removeDag(dager.getDagCount()-1);
        }
        dager.addDag(dag);
        save(filePath, dager);
    }

}
