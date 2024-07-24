package org.framework.rodolfo.freire.git.scrumgamification.controller;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ScrumMaster;
import org.framework.rodolfo.freire.git.scrumgamification.service.ScrumMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scrum-masters")
public class ScrumMasterController {

    private final ScrumMasterService scrumMasterService;

    @Autowired
    public ScrumMasterController(ScrumMasterService scrumMasterService) {
        this.scrumMasterService = scrumMasterService;
    }

    @GetMapping
    public List<ScrumMaster> getAll() {
        return scrumMasterService.getAll();
    }

    @GetMapping("/{id}")
    public ScrumMaster getById(@PathVariable Long id) {
        return scrumMasterService.getById(id);
    }

    @PostMapping
    public ScrumMaster save(@RequestBody ScrumMaster scrumMaster) {
        return scrumMasterService.save(scrumMaster);
    }

    @PutMapping("/{id}")
    public ScrumMaster update(@PathVariable Long id, @RequestBody ScrumMaster scrumMaster) {
        // Ensure the scrum master ID in the request body matches the path variable ID
        if (!id.equals(scrumMaster.getId())) {
            throw new IllegalArgumentException("Scrum Master ID in path must match the ID in the request body");
        }
        return scrumMasterService.save(scrumMaster);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scrumMasterService.delete(id);
    }
}
