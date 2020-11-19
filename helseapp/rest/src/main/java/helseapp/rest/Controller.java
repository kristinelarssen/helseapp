package helseapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import helseapp.core.Dag;
import helseapp.core.Dager;
import helseapp.json.DagPersistance;

@RestController
@RequestMapping
public class Controller {

    /**
     * Method for checking that the localhost:8080 is running. Prints hello.
     * 
     * @return "hello" - String 
     */
    @GetMapping("/")
    public String home() {
        return "hello";
    }

    // File objects
    String filePath = "../core/src/main/java/helseapp/json/dager.json";
    private DagPersistance dagPersistance = new DagPersistance();

    /**
     * Http GET request for Dager.
     * 
     * @return new ResponseEntity<List<Dag>>(dagList, HttpStatus.OK) - String with json-elements
     */
    @GetMapping("/dager")
    public ResponseEntity<List<Dag>> responseDager() {
        Dager dager = dagPersistance.read(filePath);
        List<Dag> dagList = dager.getDager();
        return new ResponseEntity<List<Dag>>(dagList, HttpStatus.OK);
    }

    /**
     * Http GET request for a date, using /dager/{dato}.
     * 
     * @param dato - @PathVariable("dato") String
     * @return new ResponseEntity<Dag>(dag, HttpStatus.OK) - String with a single json-element
     */
    @RequestMapping(value = "/dager/{dato}", method = RequestMethod.GET)
    public ResponseEntity<Dag> responseDag(@PathVariable("dato") String dato) {
        LocalDate d = LocalDate.parse(dato);
        Dager dager = dagPersistance.read(filePath);
        List<Dag> dagList = dager.getDager();
        for (Dag dag : dagList) {
            if (dag.getDate().equals(d)) {
                return new ResponseEntity<Dag>(dag, HttpStatus.OK);
            }
        }
        return null;
    }

    /**
     * Http POST request for Dager, using /dager.
     * 
     * @param dag - @RequestBody Dag
     * @return new ResponseEntity<Dag>(dag, HttpStatus.OK) - String with json-element
     */
    @PostMapping(value = "/dager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dag> addDag(@RequestBody Dag dag) {
        // Save object to file. New element for new date and update for existing date.
        dagPersistance.saveDag(dag, filePath);
        return new ResponseEntity<Dag>(dag, HttpStatus.OK);
    }
}