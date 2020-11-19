package helseapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import helseapp.core.Dag;
import helseapp.json.DagerModule;



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
