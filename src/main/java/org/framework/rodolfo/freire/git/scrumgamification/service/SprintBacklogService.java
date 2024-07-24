package org.framework.rodolfo.freire.git.scrumgamification.service;

import org.framework.rodolfo.freire.git.scrumgamification.entities.SprintBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.SprintBacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintBacklogService {

    private final SprintBacklogRepository sprintBacklogRepository;

    @Autowired
    public SprintBacklogService(SprintBacklogRepository sprintBacklogRepository) {
        this.sprintBacklogRepository = sprintBacklogRepository;
    }

    public List<SprintBacklog> getAll() {
        return sprintBacklogRepository.findAll();
    }

    public SprintBacklog getById(Long id) {
        return sprintBacklogRepository.findById(id).orElse(null);
    }

    public SprintBacklog save(SprintBacklog sprintBacklog) {
        return sprintBacklogRepository.save(sprintBacklog);
    }

    public void delete(Long id) {
        sprintBacklogRepository.deleteById(id);
    }
}
