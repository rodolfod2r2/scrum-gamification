package org.framework.rodolfo.freire.git.scrumgamification.performance;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ProductBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ProductBacklogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ProductBacklogPerformanceTest {

    @Autowired
    private ProductBacklogRepository productBacklogRepository;

    private ProductBacklog productBacklog;

    @BeforeEach
    public void setup() {
        // Inicialização de dados de teste, se necessário
        productBacklog = new ProductBacklog();
        productBacklog.setName("Product Backlog Item");
        productBacklogRepository.save(productBacklog);
    }

    @Test
    public void testCreateProductBacklogPerformance() {
        long startTime = System.nanoTime();

        ProductBacklog newProductBacklog = new ProductBacklog();
        newProductBacklog.setName("New Product Backlog Item");
        productBacklogRepository.save(newProductBacklog);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para criar Product Backlog: " + durationInMillis + " ms");
    }

    @Test
    public void testFindProductBacklogByIdPerformance() {
        long startTime = System.nanoTime();

        ProductBacklog foundProductBacklog = productBacklogRepository.findById(productBacklog.getId()).orElse(null);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para encontrar Product Backlog por ID: " + durationInMillis + " ms");
    }

    @Test
    public void testUpdateProductBacklogPerformance() {
        long startTime = System.nanoTime();

        productBacklog.setName("Updated Product Backlog Item");
        productBacklogRepository.save(productBacklog);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para atualizar Product Backlog: " + durationInMillis + " ms");
    }

    @Test
    public void testDeleteProductBacklogPerformance() {
        long startTime = System.nanoTime();

        productBacklogRepository.deleteById(productBacklog.getId());

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para deletar Product Backlog: " + durationInMillis + " ms");
    }
}
