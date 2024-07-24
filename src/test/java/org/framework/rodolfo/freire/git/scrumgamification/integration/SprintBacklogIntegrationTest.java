package org.framework.rodolfo.freire.git.scrumgamification.integration;

import org.framework.rodolfo.freire.git.scrumgamification.entities.SprintBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.SprintBacklogRepository;
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
public class SprintBacklogIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SprintBacklogRepository sprintBacklogRepository;

    @Test
    public void testCreateSprintBacklog() throws Exception {
        mockMvc.perform(post("/api/sprint-backlogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"task\": \"Sprint 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.task").value("Sprint 1"));
    }

    @Test
    public void testUpdateSprintBacklog() throws Exception {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint 2");
        sprintBacklogRepository.save(sprintBacklog);

        sprintBacklog.setTask("Updated Sprint 2");

        mockMvc.perform(put("/api/sprint-backlogs/{id}", sprintBacklog.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"task\": \"Updated Sprint 2\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task").value("Updated Sprint 2"));
    }

    @Test
    public void testFindSprintBacklogById() throws Exception {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint 3");
        sprintBacklogRepository.save(sprintBacklog);

        mockMvc.perform(get("/api/sprint-backlogs/{id}", sprintBacklog.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task").value("Sprint 3"));
    }

    @Test
    public void testDeleteSprintBacklog() throws Exception {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint 4");
        sprintBacklogRepository.save(sprintBacklog);

        mockMvc.perform(delete("/api/sprint-backlogs/{id}", sprintBacklog.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/sprint-backlogs/{id}", sprintBacklog.getId()))
                .andExpect(status().isNotFound());
    }
}

