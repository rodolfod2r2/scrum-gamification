package org.framework.rodolfo.freire.git.scrumgamification.service;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ScrumMaster;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ScrumMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrumMasterService {

    private final ScrumMasterRepository scrumMasterRepository;

    @Autowired
    public ScrumMasterService(ScrumMasterRepository scrumMasterRepository) {
        this.scrumMasterRepository = scrumMasterRepository;
    }

    public List<ScrumMaster> getAll() {
        return scrumMasterRepository.findAll();
    }

    public ScrumMaster getById(Long id) {
        return scrumMasterRepository.findById(id).orElse(null);
    }

    public ScrumMaster save(ScrumMaster scrumMaster) {
        return scrumMasterRepository.save(scrumMaster);
    }

    public void delete(Long id) {
        scrumMasterRepository.deleteById(id);
    }
}
