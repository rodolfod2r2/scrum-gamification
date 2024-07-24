package org.framework.rodolfo.freire.git.scrumgamification.unit;

import org.framework.rodolfo.freire.git.scrumgamification.entities.SprintBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.SprintBacklogRepository;
import org.framework.rodolfo.freire.git.scrumgamification.service.SprintBacklogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SprintBacklogServiceTest {

    @Mock
    private SprintBacklogRepository sprintBacklogRepository;

    @InjectMocks
    private SprintBacklogService sprintBacklogService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateSprintBacklog() {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint 1");

        when(sprintBacklogRepository.save(sprintBacklog)).thenReturn(sprintBacklog);

        SprintBacklog savedSprintBacklog = sprintBacklogService.save(sprintBacklog);

        assertEquals("Sprint 1", savedSprintBacklog.getTask());

        verify(sprintBacklogRepository, times(1)).save(sprintBacklog);
    }

    @Test
    public void testUpdateSprintBacklog() {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint 2");
        sprintBacklog.setId(1L);

        when(sprintBacklogRepository.findById(1L)).thenReturn(Optional.of(sprintBacklog));

        String newName = "Updated Sprint 2";
        sprintBacklog.setTask(newName);

        when(sprintBacklogRepository.save(sprintBacklog)).thenReturn(sprintBacklog);

        SprintBacklog updatedSprintBacklog = sprintBacklogService.save(sprintBacklog);

        assertEquals(newName, updatedSprintBacklog.getTask());

        verify(sprintBacklogRepository, times(1)).findById(1L);
        verify(sprintBacklogRepository, times(1)).save(sprintBacklog);
    }

    @Test
    public void testFindSprintBacklogById() {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint 3");
        sprintBacklog.setId(1L);

        when(sprintBacklogRepository.findById(1L)).thenReturn(Optional.of(sprintBacklog));

        SprintBacklog foundSprintBacklog = sprintBacklogService.getById(1L);

        assertEquals("Sprint 3", foundSprintBacklog.getTask());

        verify(sprintBacklogRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteSprintBacklog() {
        SprintBacklog sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint 4");
        sprintBacklog.setId(1L);

        when(sprintBacklogRepository.findById(1L)).thenReturn(Optional.of(sprintBacklog));

        sprintBacklogService.delete(1L);

        verify(sprintBacklogRepository, times(1)).deleteById(1L);
    }
}

