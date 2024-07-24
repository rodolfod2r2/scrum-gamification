package org.framework.rodolfo.freire.git.scrumgamification.unit;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ScrumMaster;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ScrumMasterRepository;
import org.framework.rodolfo.freire.git.scrumgamification.service.ScrumMasterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScrumMasterServiceTest {

    @Mock
    private ScrumMasterRepository scrumMasterRepository;

    @InjectMocks
    private ScrumMasterService scrumMasterService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateScrumMaster() {
        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setName("John Doe");

        when(scrumMasterRepository.save(scrumMaster)).thenReturn(scrumMaster);

        ScrumMaster savedScrumMaster = scrumMasterService.save(scrumMaster);

        assertEquals("John Doe", savedScrumMaster.getName());

        verify(scrumMasterRepository, times(1)).save(scrumMaster);
    }

    @Test
    public void testUpdateScrumMaster() {
        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setName("Jane Doe");
        scrumMaster.setId(1L);

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.of(scrumMaster));

        String newName = "Updated Jane Doe";
        scrumMaster.setName(newName);

        when(scrumMasterRepository.save(scrumMaster)).thenReturn(scrumMaster);

        ScrumMaster updatedScrumMaster = scrumMasterService.save(scrumMaster);

        assertEquals(newName, updatedScrumMaster.getName());

        verify(scrumMasterRepository, times(1)).findById(1L);
        verify(scrumMasterRepository, times(1)).save(scrumMaster);
    }

    @Test
    public void testFindScrumMasterById() {
        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setName("Jack Smith");
        scrumMaster.setId(1L);

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.of(scrumMaster));

        ScrumMaster foundScrumMaster = scrumMasterService.getById(1L);

        assertEquals("Jack Smith", foundScrumMaster.getName());

        verify(scrumMasterRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteScrumMaster() {
        ScrumMaster scrumMaster = new ScrumMaster();
        scrumMaster.setName("Jessica Brown");
        scrumMaster.setId(1L);

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.of(scrumMaster));

        scrumMasterService.delete(1L);

        verify(scrumMasterRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindAllScrumMasters() {
        scrumMasterService.getAll();
        verify(scrumMasterRepository, times(1)).findAll();
    }
}

