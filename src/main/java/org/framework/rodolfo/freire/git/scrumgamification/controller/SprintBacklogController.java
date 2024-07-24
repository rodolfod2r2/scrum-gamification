package org.framework.rodolfo.freire.git.scrumgamification.controller;

import org.framework.rodolfo.freire.git.scrumgamification.entities.SprintBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.service.SprintBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprint-backlogs")
public class SprintBacklogController {

    private final SprintBacklogService sprintBacklogService;

    @Autowired
    public SprintBacklogController(SprintBacklogService sprintBacklogService) {
        this.sprintBacklogService = sprintBacklogService;
    }

    @GetMapping
    public List<SprintBacklog> getAll() {
        return sprintBacklogService.getAll();
    }

    @GetMapping("/{id}")
    public SprintBacklog getById(@PathVariable Long id) {
        return sprintBacklogService.getById(id);
    }

    @PostMapping
    public SprintBacklog save(@RequestBody SprintBacklog sprintBacklog) {
        return sprintBacklogService.save(sprintBacklog);
    }

    @PutMapping("/{id}")
    public SprintBacklog update(@PathVariable Long id, @RequestBody SprintBacklog sprintBacklog) {
        // Ensure the sprint backlog ID in the request body matches the path variable ID
        if (!id.equals(sprintBacklog.getId())) {
            throw new IllegalArgumentException("Sprint Backlog ID in path must match the ID in the request body");
        }
        return sprintBacklogService.save(sprintBacklog);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sprintBacklogService.delete(id);
    }
}
