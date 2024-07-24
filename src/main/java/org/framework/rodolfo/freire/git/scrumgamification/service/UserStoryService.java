package org.framework.rodolfo.freire.git.scrumgamification.service;

import org.framework.rodolfo.freire.git.scrumgamification.entities.UserStory;
import org.framework.rodolfo.freire.git.scrumgamification.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;

    @Autowired
    public UserStoryService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    public List<UserStory> getAll() {
        return userStoryRepository.findAll();
    }

    public UserStory getById(Long id) {
        return userStoryRepository.findById(id).orElse(null);
    }

    public UserStory save(UserStory userStory) {
        return userStoryRepository.save(userStory);
    }

    public void delete(Long id) {
        userStoryRepository.deleteById(id);
    }
}
