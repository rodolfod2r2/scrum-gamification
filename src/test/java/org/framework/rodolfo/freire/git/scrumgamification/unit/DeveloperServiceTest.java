package org.framework.rodolfo.freire.git.scrumgamification.unit;


import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;
import org.framework.rodolfo.freire.git.scrumgamification.repository.DeveloperRepository;
import org.framework.rodolfo.freire.git.scrumgamification.service.DeveloperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DeveloperService developerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDeveloper() {
        Developer developer = new Developer();
        developer.setName("John Doe");
        developer.setRole("Backend Developer");

        when(developerRepository.save(developer)).thenReturn(developer);

        Developer savedDeveloper = developerService.save(developer);

        assertEquals("John Doe", savedDeveloper.getName());
        assertEquals("Backend Developer", savedDeveloper.getRole());

        verify(developerRepository, times(1)).save(developer);
    }

    @Test
    public void testUpdateDeveloper() {
        Developer developer = new Developer();
        developer.setName("Jane Smith");
        developer.setRole("Frontend Developer");
        developer.setId(1L);

        when(developerRepository.findById(1L)).thenReturn(Optional.of(developer));

        String newRole = "Fullstack Developer";
        developer.setRole(newRole);

        when(developerRepository.save(developer)).thenReturn(developer);

        Developer updatedDeveloper = developerService.save(developer);

        assertEquals(newRole, updatedDeveloper.getRole());

        verify(developerRepository, times(1)).findById(1L);
        verify(developerRepository, times(1)).save(developer);
    }

    @Test
    public void testFindDeveloperById() {
        Developer developer = new Developer();
        developer.setName("John Doe");
        developer.setRole("Backend Developer");
        developer.setId(1L);

        when(developerRepository.findById(1L)).thenReturn(Optional.of(developer));

        Developer foundDeveloper = developerService.getById(1L);

        assertEquals("John Doe", foundDeveloper.getName());
        assertEquals("Backend Developer", foundDeveloper.getRole());

        verify(developerRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteDeveloper() {
        Developer developer = new Developer();
        developer.setName("John Doe");
        developer.setRole("Backend Developer");
        developer.setId(1L);

        when(developerRepository.findById(1L)).thenReturn(Optional.of(developer));

        developerService.delete(1L);

        verify(developerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindAllDevelopers() {
        developerService.getAll();

        verify(developerRepository, times(1)).findAll();
    }

    @Test
    public void testCreateDeveloperInvalid() {
        Developer developer = new Developer();

        assertThrows(IllegalArgumentException.class, () -> developerService.save(developer));

        verify(developerRepository, never()).save(any());
    }
}

