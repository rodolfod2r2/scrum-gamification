package org.framework.rodolfo.freire.git.scrumgamification.performance;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;
import org.framework.rodolfo.freire.git.scrumgamification.repository.DeveloperRepository;
import org.framework.rodolfo.freire.git.scrumgamification.service.DeveloperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DirtiesContext
public class DeveloperPerformanceTest {

    private static final int NUM_DEVELOPERS = 1000;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private DeveloperRepository developerRepository;

    @BeforeEach
    public void setup() {
        developerRepository.deleteAll();
    }

    @Test
    public void testCreateManyDevelopers() {
        long startTime = System.currentTimeMillis();

        List<Developer> developers = new ArrayList<>();
        for (int i = 0; i < NUM_DEVELOPERS; i++) {
            Developer developer = new Developer();
            developer.setName("Developer " + i);
            developer.setRole("Role " + i);
            developers.add(developer);
        }
        for (Developer d : developers) {
            developerService.save(d);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tempo total para criar " + NUM_DEVELOPERS + " desenvolvedores: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testUpdateManyDevelopers() {
        List<Developer> developers = new ArrayList<>();
        for (int i = 0; i < NUM_DEVELOPERS; i++) {
            Developer developer = new Developer();
            developer.setName("Developer " + i);
            developer.setRole("Role " + i);
            developerRepository.save(developer);
            developers.add(developer);
        }

        long startTime = System.currentTimeMillis();

        for (Developer developer : developers) {
            developer.setRole("Updated Role");
            developerService.save(developer);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tempo total para atualizar " + NUM_DEVELOPERS + " desenvolvedores: " + (endTime - startTime) + " ms");
    }
}
