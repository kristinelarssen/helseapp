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

  private ObjectMapper objectMapper;


  @BeforeEach
  public void setup() throws Exception {
    objectMapper = new ObjectMapper().registerModule(new DagerModule());;
  }


  @Test
  public void testGet_home() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
    String innhold = result.getResponse().getContentAsString();
    assertEquals("hello", innhold);
  }


  @Test
  public void testGet_dager() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dager/")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
    try {
        Dager[] dager = objectMapper.readValue(result.getResponse().getContentAsString(), Dager[].class);
        System.out.println(dager[0]);
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
    } catch (JsonProcessingException e) {
        fail(e.getMessage());
    }
  }


    @Test
    public void testGet_ByDato() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dager/2020-11-10")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        String innhold = result.getResponse().getContentAsString();
        String forventet_innhold = "{\"vekt\":10.0,\"skritt\":20.0,\"treningstid\":30.0,\"protein\":40.0,\"karbo\":50.0,\"fett\":60.0,\"date\":\"2020-11-10\",\"kalorier\":900.0}";
        assertEquals(forventet_innhold, innhold);
    }

    




  
}
