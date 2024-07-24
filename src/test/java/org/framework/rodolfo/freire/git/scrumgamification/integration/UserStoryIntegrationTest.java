package org.framework.rodolfo.freire.git.scrumgamification.integration;

import org.framework.rodolfo.freire.git.scrumgamification.entities.UserStory;
import org.framework.rodolfo.freire.git.scrumgamification.repository.UserStoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserStoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserStoryRepository userStoryRepository;

    @Test
    public void testCreateUserStory() throws Exception {
        mockMvc.perform(post("/api/user-stories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"As a user, I want to login\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("As a user, I want to login"));
    }

    @Test
    public void testUpdateUserStory() throws Exception {
        UserStory userStory = new UserStory();
        userStory.setTitle("Logout");
        userStory.setDescription("As a user, I want to logout");
        userStoryRepository.save(userStory);

        userStory.setDescription("As a user, I want to logout gracefully");

        mockMvc.perform(put("/api/user-stories/{id}", userStory.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"As a user, I want to logout gracefully\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("As a user, I want to logout gracefully"));
    }

    @Test
    public void testFindUserStoryById() throws Exception {
        UserStory userStory = new UserStory();
        userStory.setTitle("Profile");
        userStory.setDescription("As a user, I want to view my profile");
        userStoryRepository.save(userStory);

        mockMvc.perform(get("/api/user-stories/{id}", userStory.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("As a user, I want to view my profile"));
    }

    @Test
    public void testDeleteUserStory() throws Exception {
        UserStory userStory = new UserStory();
        userStory.setTitle("Preferences");
        userStory.setDescription("As a user, I want to update my preferences");
        userStoryRepository.save(userStory);

        mockMvc.perform(delete("/api/user-stories/{id}", userStory.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user-stories/{id}", userStory.getId()))
                .andExpect(status().isNotFound());
    }
}

