package org.framework.rodolfo.freire.git.scrumgamification.unit;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ProductBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ProductBacklogRepository;
import org.framework.rodolfo.freire.git.scrumgamification.service.ProductBacklogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductBacklogServiceTest {

    @Mock
    private ProductBacklogRepository productBacklogRepository;

    @InjectMocks
    private ProductBacklogService productBacklogService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProductBacklog() {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setName("Project A");

        when(productBacklogRepository.save(productBacklog)).thenReturn(productBacklog);

        ProductBacklog savedProductBacklog = productBacklogService.save(productBacklog);

        assertEquals("Project A", savedProductBacklog.getName());

        verify(productBacklogRepository, times(1)).save(productBacklog);
    }

    @Test
    public void testUpdateProductBacklog() {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setName("Project B");
        productBacklog.setId(1L);

        when(productBacklogRepository.findById(1L)).thenReturn(Optional.of(productBacklog));

        String newProjectName = "Updated Project B";
        productBacklog.setName(newProjectName);

        when(productBacklogRepository.save(productBacklog)).thenReturn(productBacklog);

        ProductBacklog updatedProductBacklog = productBacklogService.save(productBacklog);

        assertEquals(newProjectName, updatedProductBacklog.getName());

        verify(productBacklogRepository, times(1)).findById(1L);
        verify(productBacklogRepository, times(1)).save(productBacklog);
    }

    @Test
    public void testFindProductBacklogById() {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setName("Project C");
        productBacklog.setId(1L);

        when(productBacklogRepository.findById(1L)).thenReturn(Optional.of(productBacklog));

        ProductBacklog foundProductBacklog = productBacklogService.getById(1L);

        assertEquals("Project C", foundProductBacklog.getName());

        verify(productBacklogRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteProductBacklog() {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setName("Project D");
        productBacklog.setId(1L);

        when(productBacklogRepository.findById(1L)).thenReturn(Optional.of(productBacklog));

        productBacklogService.delete(1L);

        verify(productBacklogRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindAllProductBacklogs() {
        productBacklogService.getAll();

        verify(productBacklogRepository, times(1)).findAll();
    }
}

