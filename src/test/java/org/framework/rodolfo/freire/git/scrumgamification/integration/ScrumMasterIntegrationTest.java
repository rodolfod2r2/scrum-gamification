package org.framework.rodolfo.freire.git.scrumgamification.integration;


import org.framework.rodolfo.freire.git.scrumgamification.entities.ScrumMaster;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ScrumMasterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ScrumMasterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScrumMasterRepository scrumMasterRepository;

    @Test
    public void testCreateScrumMaster() throws Exception {
        mockMvc.perform(post("/api/scrum-masters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdateScrumMaster() throws Exception {
        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setName("Jane Doe");
        scrumMasterRepository.save(scrumMaster);

        scrumMaster.setName("Updated Jane Doe");

        mockMvc.perform(put("/api/scrum-masters/{id}", scrumMaster.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Jane Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Jane Doe"));
    }

    @Test
    public void testFindScrumMasterById() throws Exception {
        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setName("Jack Smith");
        scrumMasterRepository.save(scrumMaster);

        mockMvc.perform(get("/api/scrum-masters/{id}", scrumMaster.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jack Smith"));
    }

    @Test
    public void testDeleteScrumMaster() throws Exception {
        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setName("Jessica Brown");
        scrumMasterRepository.save(scrumMaster);

        mockMvc.perform(delete("/api/scrum-masters/{id}", scrumMaster.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/scrum-masters/{id}", scrumMaster.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllScrumMasters() throws Exception {
        ScrumMaster scrumMaster1 = new ScrumMaster();
        scrumMaster1.setName("John Smith");
        ScrumMaster scrumMaster2 = new ScrumMaster();
        scrumMaster2.setName("Jane Brown");
        scrumMasterRepository.save(scrumMaster1);
        scrumMasterRepository.save(scrumMaster2);

        mockMvc.perform(get("/api/scrum-masters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Smith"))
                .andExpect(jsonPath("$[1].name").value("Jane Brown"));
    }
}

