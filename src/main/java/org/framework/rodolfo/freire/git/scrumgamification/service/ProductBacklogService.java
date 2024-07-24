package org.framework.rodolfo.freire.git.scrumgamification.service;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ProductBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.repository.ProductBacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBacklogService {

    private final ProductBacklogRepository productBacklogRepository;

    @Autowired
    public ProductBacklogService(ProductBacklogRepository productBacklogRepository) {
        this.productBacklogRepository = productBacklogRepository;
    }

    public List<ProductBacklog> getAll() {
        return productBacklogRepository.findAll();
    }

    public ProductBacklog getById(Long id) {
        return productBacklogRepository.findById(id).orElse(null);
    }

    public ProductBacklog save(ProductBacklog productBacklog) {
        return productBacklogRepository.save(productBacklog);
    }

    public void delete(Long id) {
        productBacklogRepository.deleteById(id);
    }
}
