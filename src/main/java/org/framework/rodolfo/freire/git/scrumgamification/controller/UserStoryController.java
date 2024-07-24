package org.framework.rodolfo.freire.git.scrumgamification.controller;

import org.framework.rodolfo.freire.git.scrumgamification.entities.UserStory;
import org.framework.rodolfo.freire.git.scrumgamification.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {

    private final UserStoryService userStoryService;

    @Autowired
    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping
    public List<UserStory> getAll() {
        return userStoryService.getAll();
    }

    @GetMapping("/{id}")
    public UserStory getById(@PathVariable Long id) {
        return userStoryService.getById(id);
    }

    @PostMapping
    public UserStory save(@RequestBody UserStory userStory) {
        return userStoryService.save(userStory);
    }

    @PutMapping("/{id}")
    public UserStory update(@PathVariable Long id, @RequestBody UserStory userStory) {
        // Ensure the user story ID in the request body matches the path variable ID
        if (!id.equals(userStory.getId())) {
            throw new IllegalArgumentException("User Story ID in path must match the ID in the request body");
        }
        return userStoryService.save(userStory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userStoryService.delete(id);
    }
}
