package org.framework.rodolfo.freire.git.scrumgamification.performance;

import org.framework.rodolfo.freire.git.scrumgamification.entities.SprintBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.SprintBacklogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class SprintBacklogPerformanceTest {

    @Autowired
    private SprintBacklogRepository sprintBacklogRepository;

    private SprintBacklog sprintBacklog;

    @BeforeEach
    public void setup() {
        sprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("Sprint Backlog Item");
        sprintBacklogRepository.save(sprintBacklog);
    }

    @Test
    public void testCreateSprintBacklogPerformance() {
        long startTime = System.nanoTime();

        SprintBacklog newSprintBacklog = new SprintBacklog();
        sprintBacklog.setTask("New Sprint Backlog Item");
        sprintBacklogRepository.save(newSprintBacklog);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para criar Sprint Backlog: " + durationInMillis + " ms");
    }

    @Test
    public void testFindSprintBacklogByIdPerformance() {
        long startTime = System.nanoTime();

        SprintBacklog foundSprintBacklog = sprintBacklogRepository.findById(sprintBacklog.getId()).orElse(null);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para encontrar Sprint Backlog por ID: " + durationInMillis + " ms");
    }

    @Test
    public void testUpdateSprintBacklogPerformance() {
        long startTime = System.nanoTime();

        sprintBacklog.setTask("Updated Sprint Backlog Item");
        sprintBacklogRepository.save(sprintBacklog);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para atualizar Sprint Backlog: " + durationInMillis + " ms");
    }

    @Test
    public void testDeleteSprintBacklogPerformance() {
        long startTime = System.nanoTime();

        sprintBacklogRepository.deleteById(sprintBacklog.getId());

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para deletar Sprint Backlog: " + durationInMillis + " ms");
    }
}

