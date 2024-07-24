package org.framework.rodolfo.freire.git.scrumgamification.integration;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ProductBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ProductBacklogRepository;
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
public class ProductBacklogIntegrationTest {

    @Autowired
    private final ProductBacklogRepository productBacklogRepository;
    @Autowired
    private MockMvc mockMvc;

    public ProductBacklogIntegrationTest(MockMvc mockMvc, ProductBacklogRepository productBacklogRepository) {
        this.mockMvc = mockMvc;
        this.productBacklogRepository = productBacklogRepository;
    }

    @Test
    public void testCreateProductBacklog() throws Exception {
        mockMvc.perform(post("/api/product-backlogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Project A\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Project A"));
    }

    @Test
    public void testUpdateProductBacklog() throws Exception {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setName("Project B");
        productBacklogRepository.save(productBacklog);

        productBacklog.setName("Updated Project B");

        mockMvc.perform(put("/api/product-backlogs/{id}", productBacklog.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Project B\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Project B"));
    }

    @Test
    public void testFindProductBacklogById() throws Exception {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setName("Project C");
        productBacklogRepository.save(productBacklog);

        mockMvc.perform(get("/api/product-backlogs/{id}", productBacklog.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Project C"));
    }

    @Test
    public void testDeleteProductBacklog() throws Exception {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setName("Project D");
        productBacklogRepository.save(productBacklog);

        mockMvc.perform(delete("/api/product-backlogs/{id}", productBacklog.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/product-backlogs/{id}", productBacklog.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllProductBacklogs() throws Exception {
        ProductBacklog productBacklog1 = new ProductBacklog();
        productBacklog1.setName("Project E");
        ProductBacklog productBacklog2 = new ProductBacklog();
        productBacklog2.setName("Project F");
        productBacklogRepository.save(productBacklog1);
        productBacklogRepository.save(productBacklog2);

        mockMvc.perform(get("/api/product-backlogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Project E"))
                .andExpect(jsonPath("$[1].name").value("Project F"));
    }
}

