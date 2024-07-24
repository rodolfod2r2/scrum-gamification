package org.framework.rodolfo.freire.git.scrumgamification.performance;

import org.framework.rodolfo.freire.git.scrumgamification.entities.UserStory;
import org.framework.rodolfo.freire.git.scrumgamification.repository.UserStoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class UserStoryPerformanceTest {

    @Autowired
    private UserStoryRepository userStoryRepository;

    private UserStory userStory;

    @BeforeEach
    public void setup() {
        // Inicialização de dados de teste, se necessário
        userStory = new UserStory();
        userStory.setTitle("Login");
        userStory.setDescription("As a user, I want to...");
        userStoryRepository.save(userStory);
    }

    @Test
    public void testCreateUserStoryPerformance() {
        long startTime = System.nanoTime();

        UserStory newUserStory = new UserStory();
        userStory.setTitle("Login");
        userStory.setDescription("As a developer, I want to...");
        userStoryRepository.save(newUserStory);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para criar User Story: " + durationInMillis + " ms");
    }

    @Test
    public void testFindUserStoryByIdPerformance() {
        long startTime = System.nanoTime();

        UserStory foundUserStory = userStoryRepository.findById(userStory.getId()).orElse(null);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para encontrar User Story por ID: " + durationInMillis + " ms");
    }

    @Test
    public void testUpdateUserStoryPerformance() {
        long startTime = System.nanoTime();

        userStory.setDescription("As a tester, I want to...");
        userStoryRepository.save(userStory);

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para atualizar User Story: " + durationInMillis + " ms");
    }

    @Test
    public void testDeleteUserStoryPerformance() {
        long startTime = System.nanoTime();

        userStoryRepository.deleteById(userStory.getId());

        long endTime = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Tempo de execução para deletar User Story: " + durationInMillis + " ms");
    }
}

