package org.framework.rodolfo.freire.git.scrumgamification.controller;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;
import org.framework.rodolfo.freire.git.scrumgamification.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public List<Developer> getAll() {
        return developerService.getAll();
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id) {
        return developerService.getById(id);
    }

    @PostMapping
    public Developer save(@RequestBody Developer developer) {
        return developerService.save(developer);
    }

    @PutMapping("/{id}")
    public Developer update(@PathVariable Long id, @RequestBody Developer developer) {
        // Ensure the developer ID in the request body matches the path variable ID
        if (!id.equals(developer.getId())) {
            throw new IllegalArgumentException("Developer ID in path must match the ID in the request body");
        }
        return developerService.save(developer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        developerService.delete(id);
    }
}
