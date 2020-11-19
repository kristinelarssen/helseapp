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

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    // File objects
    String filePath = "src/main/java/IT1901/gr2059/demo/dager.json";
    private DagPersistance dagPersistance = new DagPersistance();

    @GetMapping("/dager")
    public ResponseEntity<List<Dag>> responseDager() {
        Dager dager = dagPersistance.read(filePath);
        List<Dag> dagList = dager.getDager();
        return new ResponseEntity<List<Dag>>(dagList, HttpStatus.OK);
    }

    @RequestMapping(value = "/dager/{dato}", method = RequestMethod.GET)
    public ResponseEntity<Dag> responseDag(@PathVariable("dato") String dato) {
        LocalDate d = LocalDate.parse(dato);
        Dager dager = dagPersistance.read(filePath);
        List<Dag> dagList = dager.getDager();
        for (Dag dag : dagList){
            if (dag.getDate().equals(d)){
                return new ResponseEntity<Dag>(dag, HttpStatus.OK);
            }
        }
        return null;
    }

    @PostMapping(value = "/dager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dag> addDag(@RequestBody Dag dag){
        // Save object to file. New element for new date and update for existing date.
        dagPersistance.saveDag(dag, filePath);
        return new ResponseEntity<Dag>(dag, HttpStatus.OK);
    }


    public static void main(String[] args) {

    }


























    // Implements both post and put in post.
    /*
     @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/dager/{dato}")
    public String updateLaptop(@RequestBody Dag dag) {
        for (Dag d : dagList) {
            if (d.getDate() == dag.getDate()) {
                dagList.remove(d);
                dagList.add(dag);
                return "updated";
            }
        }
        return "Object does not exist";
    }
     */

    /*

    // laptop
    @DeleteMapping(value = "/laptops/{laptopId}")
    public String deleteLaptop(@PathVariable int laptopId){
        for (Laptop lap : laptopList) {
            if (lap.getLaptopID() == laptopId) {
                laptopList.remove(lap);
                return "Laptop " + laptopId + " is deleted.";
            }
        }
        return "Object does not exist";
    }
    // Dag
    @DeleteMapping(value = "/dager/{dato}")
    public String deleteDag(@PathVariable String dato, LocalDate date){
        for (Dag d : dagList) {
            if (d.getDate() == date) {
                dagList.remove(d);
                return "Laptop " + dato + " is deleted.";
            }
        }
        return "Object does not exist";
    }

    // laptop
    List<Laptop> laptopList = new ArrayList<>();

    // laptop
    @GetMapping("/laptops")
    public ResponseEntity<List<Laptop>> Responselaptops() {
        return new ResponseEntity<List<Laptop>>(laptopList, HttpStatus.OK);
    }
    // laptop
    @RequestMapping(value = "/laptops/{laptopId}", method = RequestMethod.GET)
    public ResponseEntity<Laptop> Responselaptop(@PathVariable("laptopId") int laptopId) {

        Laptop lap1 = new Laptop("Could not find", 404, 404, 404);

        for (Laptop laptop : laptopList){
            if (laptop.getLaptopID() == laptopId){
                return new ResponseEntity<Laptop>(laptop, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Laptop>(lap1, HttpStatus.OK);
    }

    // laptop
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/laptops")
    public String addLaptop(@RequestBody Laptop laptop){
        laptopList.add(laptop);
        return laptop.toString();
    }

    // laptop
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/laptops/{laptopId}")
    public String updateLaptop(@RequestBody Laptop laptop) {
        for (Laptop lap : laptopList) {
            if (lap.getLaptopID() == laptop.laptopID) {
                laptopList.remove(lap);
                laptopList.add(laptop);
                return "updated";
            }
        }
        return "Object does not exist";
    }


     */
}
























    /*


    @PostMapping("/laptops/new")
    public String addLaptops(@RequestBody List<Laptop> laptops) {
        laptopList.addAll(laptops);
        return "All laptops added";
    }

    @GetMapping("/laptops/search")
    public List<Laptop> priceRange(@RequestParam int greaterThan){
        return laptopList.stream().filter(laptop -> laptop.price > greaterThan).collect(Collectors.toList());
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ResponseLaptops")
    public List<Object> getLaptopsFromAPI(){
        // Random endpoint on the internet
        String url = "http://localhost:8080/ResponseLaptop";
        Object[] objects = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(objects);
    }

    @Override
    public Laptop deserialize1(final JsonParser jsonParser, final DeserializationContext deserContext)
            throws IOException, JsonProcessingException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(jsonNode);
    }

    public Laptop deserialize(final JsonNode jsonNode) throws JsonProcessingException {
        // If the json-node is a dag object
        if (jsonNode instanceof ObjectNode) {
            // Creates object from the json-node
            final ObjectNode objectNode = (ObjectNode) jsonNode;
            // Get all attributes of the object
            final String name = objectNode.get("name").asText();
            final int price = objectNode.get("price").asInt();
            final int ramSize = objectNode.get("ramSize").asInt();
            // Create the object
            return new Laptop(name, price, ramSize);
        }
        return null;
    }
    @Override
    public List<Laptop> deserializeList(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        List<Laptop> lapList = new ArrayList<>();
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        if (jsonNode instanceof ArrayNode) {
            final ArrayNode dagerArray = (ArrayNode) jsonNode;
            final Collection<Laptop> laptopCollection = new ArrayList<Laptop>(dagerArray.size());
            for (final JsonNode laptopNode : dagerArray) {
                final Laptop lap = deserialize(laptopNode);
                lapList.add(lap);
            }
            return lapList;
        }
        return null;
    }



    public static void main(String[] args) {
        System.out.println("hello");

        List<Object> laptopList2 = new ArrayList<>();
        Controller contr = new Controller();
        laptopList2 = contr.getLaptopsFromAPI();


        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/ResponseLaptop";
        Object[] objects = restTemplate.getForObject(url, Object[].class);
        List<Object> obj = Arrays.asList(objects);
        System.out.println(obj);


    }
*/


