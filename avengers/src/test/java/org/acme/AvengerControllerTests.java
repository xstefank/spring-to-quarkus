package org.acme;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class AvengerControllerTests {


    @InjectMock
    AvengerRepository avengerRepository;

    @Test
    public void getAll() throws Exception {
        Mockito.when(avengerRepository.findAll())
            .thenReturn(List.of(new Avenger("Falcon", "Sam Wilson", true)));

        given().get("/avengers")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("[0].name", equalTo("Falcon"))
            .body("[0].civilname", equalTo("Sam Wilson"))
            .body("[0].snapped", equalTo(true));

//        mockMvc.perform(get("/avengers"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$[0].name").value("Falcon"))
//            .andExpect(jsonPath("$[0].civilname").value("Sam Wilson"))
//            .andExpect(jsonPath("$[0].snapped").value(true));

        Mockito.verify(avengerRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(avengerRepository);
    }

    @Test
    public void avengerFindByName() throws Exception {
        Mockito.when(avengerRepository.findByName("Falcon"))
            .thenReturn(Optional.of(new Avenger("Falcon", "Sam Wilson", true)));

        given().get("/avengers/Falcon")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("name", equalTo("Falcon"))
            .body("civilname", equalTo("Sam Wilson"))
            .body("snapped", equalTo(true));

//        mockMvc.perform(get("/avengers/Falcon"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.name").value("Falcon"))
//            .andExpect(jsonPath("$.civilname").value("Sam Wilson"))
//            .andExpect(jsonPath("$.snapped").value(true));

        Mockito.verify(avengerRepository, Mockito.times(1)).findByName("Falcon");
        Mockito.verifyNoMoreInteractions(avengerRepository);
    }

    @Test
    public void createAvenger() throws Exception {
        Mockito.when(avengerRepository.save(Mockito.any(Avenger.class)))
            .thenReturn(new Avenger("Falcon", "Sam Wilson", true));

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"name\":\"Falcon\", \"civilname\":\"Sam Wilson\", \"snapped\":true}")
            .post("/avengers")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("name", equalTo("Falcon"))
            .body("civilname", equalTo("Sam Wilson"))
            .body("snapped", equalTo(true));

//        mockMvc.perform(post("/avengers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\":\"Falcon\", \"civilname\":\"Sam Wilson\", \"snapped\":true}"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("name").value("Falcon"))
//            .andExpect(jsonPath("civilname").value("Sam Wilson"))
//            .andExpect(jsonPath("snapped").value(true));

        Mockito.verify(avengerRepository, Mockito.times(1)).save(Mockito.any(Avenger.class));
        Mockito.verifyNoMoreInteractions(avengerRepository);
    }
}
