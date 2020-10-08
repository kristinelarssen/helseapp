package helseapp.json;

import helseapp.core.Dag;
import helseapp.core.Dager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import helseapp.json.DagPersistance;
import javafx.stage.FileChooser;

public class Testing {

    Dager dager = new Dager();

    public void setDager(final Dager dager){
        this.dager = dager;
    }

    public static void main(String[] args) {
        String savePath = "helseapp/src/main/java/helseapp/json/dager.json";
        DagPersistance dagPersistance = new DagPersistance();
        FileData fileData = new FileData(dagPersistance);
        Dag dag1 = new Dag(999, 1, 191919, 1, 1, 1, LocalDate.now());
        Dag dag2 = new Dag(2, 2, 2, 2, 2, 2, LocalDate.now());
        Dag dag3 = new Dag(3, 3, 3, 3, 3, 1000, LocalDate.now());
        Dager dager_tilFil = new Dager(dag1, dag2, dag3);

        fileData.save(savePath, dager_tilFil);


        /* Dette brukes ikke:
         Reader reader = null;
        if(savePath != null){
            Path path = Paths.get("helseapp/src/main/java/helseapp/json/dager.json");
            try(Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)){
                dagPersistance.writeDager(dager_tilFil, writer);
            } catch (IOException e){
                System.err.println("Fikk ikke skrevet til dager.json på hjemme området");
            }
        }

        Det over brukes ikke */




        Dag dag4 = new Dag(1000000, 22222222, 333333, 4444, 5555, 6666, LocalDate.now());
        fileData.saveDag(dag4, savePath);


        Dager dager_fraFil = fileData.read(savePath);

        /* Dette brukes ikke:
        if (savePath != null) {
            try {
                reader = new FileReader(Paths.get(savePath).toFile(), StandardCharsets.UTF_8);
                System.out.println("det fungerte");
            }
            catch (final IOException e) {
                System.err.println("feil");
            }
        }
        try{
            dager_fraFil = dagPersistance.readDager(reader);
        } catch (IOException e) {
            System.err.println("feil2");
        }
        Det over brukes ikke */

        // Lagring:
        /*
        1. Få på plass lesing fra fil (ish done)
        2. koble opp lagring til GUI
        3. Logikk for å unngå at samme dag lagres flere ganger
        4. Ikke overskrive fil, men oppdatere den(legge til nederst)
        5. Finne måte å skille dag fra dag(dato?)
         */

        if(savePath != null){
            Path path = Paths.get("helseapp/src/main/java/helseapp/json/Test.json");
            try(Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)){
                dagPersistance.writeDager(dager_fraFil, writer);
            } catch (IOException e){
                System.err.println("Fikk ikke skrevet til dager.json på hjemme området");
            }
        }

    }



}
