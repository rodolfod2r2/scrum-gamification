package org.framework.rodolfo.freire.git.scrumgamification.unit;

import org.framework.rodolfo.freire.git.scrumgamification.entities.UserStory;
import org.framework.rodolfo.freire.git.scrumgamification.repository.UserStoryRepository;
import org.framework.rodolfo.freire.git.scrumgamification.service.UserStoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserStoryServiceTest {

    @Mock
    private UserStoryRepository userStoryRepository;

    @InjectMocks
    private UserStoryService userStoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUserStory() {
        UserStory userStory = new UserStory();
        userStory.setTitle("Login");
        userStory.setDescription("As a user, I want to login");

        when(userStoryRepository.save(userStory)).thenReturn(userStory);

        UserStory savedUserStory = userStoryService.save(userStory);

        assertEquals("As a user, I want to login", savedUserStory.getDescription());

        verify(userStoryRepository, times(1)).save(userStory);
    }

    @Test
    public void testUpdateUserStory() {
        UserStory userStory = new UserStory();
        userStory.setTitle("Logout");
        userStory.setDescription("As a user, I want to logout");
        userStory.setId(1L);

        when(userStoryRepository.findById(1L)).thenReturn(Optional.of(userStory));

        String newDescription = "As a user, I want to logout gracefully";
        userStory.setDescription(newDescription);

        when(userStoryRepository.save(userStory)).thenReturn(userStory);

        UserStory updatedUserStory = userStoryService.save(userStory);

        assertEquals(newDescription, updatedUserStory.getDescription());

        verify(userStoryRepository, times(1)).findById(1L);
        verify(userStoryRepository, times(1)).save(userStory);
    }

    @Test
    public void testFindUserStoryById() {
        UserStory userStory = new UserStory();
        userStory.setTitle("Profile");
        userStory.setDescription("As a user, I want to view my profile");
        userStory.setId(1L);

        when(userStoryRepository.findById(1L)).thenReturn(Optional.of(userStory));

        UserStory foundUserStory = userStoryService.getById(1L);

        assertEquals("As a user, I want to view my profile", foundUserStory.getDescription());

        verify(userStoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteUserStory() {
        UserStory userStory = new UserStory();
        userStory.setTitle("Preferences");
        userStory.setDescription("As a user, I want to update my preferences");
        userStory.setId(1L);

        when(userStoryRepository.findById(1L)).thenReturn(Optional.of(userStory));

        userStoryService.delete(1L);

        verify(userStoryRepository, times(1)).deleteById(1L);
    }
}

