package org.framework.rodolfo.freire.git.scrumgamification.performance;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ScrumMaster;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ScrumMasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ScrumMasterPerformanceTest {

    @Autowired
    private ScrumMasterRepository scrumMasterRepository;

    private ScrumMaster scrumMaster;

    @BeforeEach
    public void setup() {
        scrumMaster = new ScrumMaster();
        scrumMaster.setName("John Doe");
        scrumMasterRepository.save(scrumMaster);
    }

    @Test
    public void testCreateScrumMasterPerformance() {
        long startTime = System.nanoTime();

        ScrumMaster newScrumMaster = new ScrumMaster();
        scrumMaster.setName("Jane Doe");
        scrumMasterRepository.save(newScrumMaster);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para criar Scrum Master: " + durationInMillis + " ms");
    }

    @Test
    public void testFindScrumMasterByIdPerformance() {
        long startTime = System.nanoTime();

        ScrumMaster foundScrumMaster = scrumMasterRepository.findById(scrumMaster.getId()).orElse(null);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para encontrar Scrum Master por ID: " + durationInMillis + " ms");
    }

    @Test
    public void testUpdateScrumMasterPerformance() {
        long startTime = System.nanoTime();

        scrumMaster.setName("Updated John Doe");
        scrumMasterRepository.save(scrumMaster);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para atualizar Scrum Master: " + durationInMillis + " ms");
    }

    @Test
    public void testDeleteScrumMasterPerformance() {
        long startTime = System.nanoTime();

        scrumMasterRepository.deleteById(scrumMaster.getId());

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para deletar Scrum Master: " + durationInMillis + " ms");
    }
}

