package org.acme;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ContainersConfig.class)
public class AvengerControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AvengerRepository avengerRepository;

    @Test
    public void getAll() throws Exception {
        Mockito.when(avengerRepository.findAll())
            .thenReturn(List.of(new Avenger("Falcon", "Sam Wilson", true)));

        mockMvc.perform(get("/avengers"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("Falcon"))
            .andExpect(jsonPath("$[0].civilname").value("Sam Wilson"))
            .andExpect(jsonPath("$[0].snapped").value(true));

        Mockito.verify(avengerRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(avengerRepository);
    }

    @Test
    public void avengerFindByName() throws Exception {
        Mockito.when(avengerRepository.findByName("Falcon"))
            .thenReturn(Optional.of(new Avenger("Falcon", "Sam Wilson", true)));

        mockMvc.perform(get("/avengers/Falcon"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Falcon"))
            .andExpect(jsonPath("$.civilname").value("Sam Wilson"))
            .andExpect(jsonPath("$.snapped").value(true));

        Mockito.verify(avengerRepository, Mockito.times(1)).findByName("Falcon");
        Mockito.verifyNoMoreInteractions(avengerRepository);
    }

    @Test
    public void createAvenger() throws Exception {
        Mockito.when(avengerRepository.save(Mockito.any(Avenger.class)))
            .thenReturn(new Avenger("Falcon", "Sam Wilson", true));

        mockMvc.perform(post("/avengers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Falcon\", \"civilname\":\"Sam Wilson\", \"snapped\":true}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("name").value("Falcon"))
            .andExpect(jsonPath("civilname").value("Sam Wilson"))
            .andExpect(jsonPath("snapped").value(true));

        Mockito.verify(avengerRepository, Mockito.times(1)).save(Mockito.any(Avenger.class));
        Mockito.verifyNoMoreInteractions(avengerRepository);
    }
}
