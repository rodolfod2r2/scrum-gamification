package org.framework.rodolfo.freire.git.scrumgamification.integration;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;
import org.framework.rodolfo.freire.git.scrumgamification.repository.DeveloperRepository;
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
public class DeveloperIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeveloperRepository developerRepository;

    @Test
    public void testCreateDeveloper() throws Exception {
        mockMvc.perform(post("/api/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"role\": \"Backend Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.role").value("Backend Developer"));
    }

    @Test
    public void testUpdateDeveloper() throws Exception {
        Developer developer = new Developer();
        developer.setName("Jane Smith");
        developer.setRole("Frontend Developer");
        developerRepository.save(developer);

        developer.setRole("Fullstack Developer");

        mockMvc.perform(put("/api/developers/{id}", developer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Jane Smith\", \"role\": \"Fullstack Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("Fullstack Developer"));
    }

    @Test
    public void testFindDeveloperById() throws Exception {
        Developer developer = new Developer();
        developer.setName("John Doe");
        developer.setRole("Backend Developer");
        developerRepository.save(developer);

        mockMvc.perform(get("/api/developers/{id}", developer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.role").value("Backend Developer"));
    }

    @Test
    public void testDeleteDeveloper() throws Exception {
        Developer developer = new Developer();
        developer.setName("John Doe");
        developer.setRole("Backend Developer");
        developerRepository.save(developer);

        mockMvc.perform(delete("/api/developers/{id}", developer.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/developers/{id}", developer.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllDevelopers() throws Exception {
        Developer developer1 = new Developer();
        developer1.setName("John Doe");
        developer1.setRole("Backend Developer");
        Developer developer2 = new Developer();
        developer2.setName("Jane Smith");
        developer2.setRole("Frontend Developer");
        developerRepository.save(developer1);
        developerRepository.save(developer2);

        mockMvc.perform(get("/api/developers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].role").value("Backend Developer"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].role").value("Frontend Developer"));
    }
}


