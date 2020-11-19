package helseapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Iterator;
import java.util.ArrayList;
import java.time.LocalDate; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import helseapp.core.Dager;
import helseapp.core.Dag;
import helseapp.json.DagerModule;
import java.util.Arrays;
import java.util.List;


@AutoConfigureMockMvc
@ContextConfiguration(classes = { Controller.class, Helseapplication.class })
@WebMvcTest
public class ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setup() throws Exception {
    objectMapper = new ObjectMapper().registerModule(new DagerModule());;
  }

  @Test
  public void testGet_dag() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dager/")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
    try {
        Dager[] dager = objectMapper.readValue(result.getResponse().getContentAsString(), Dager[].class);
        List<Dag> dager2 = new ArrayList(Arrays.asList(dager));
        Iterator<Dag> it = dager2.iterator();
        assertTrue(it.hasNext());
        Dag dag1 = it.next();
        assertTrue(it.hasNext());
        Dag dag2 = it.next();
        assertTrue(it.hasNext());
        Dag dag3 = it.next();
        assertTrue(it.hasNext());
        Dag dag4 = it.next();
        assertFalse(it.hasNext());
        System.out.println(LocalDate.parse("2020-11-11"));
        System.out.println(dag1);
        //assertEquals(LocalDate.parse("2020-11-11"), dag1.getDate());
    } catch (JsonProcessingException e) {
        fail(e.getMessage());
    }

  }


  @Test
  public void testGet_todo2() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

  }



  
}
