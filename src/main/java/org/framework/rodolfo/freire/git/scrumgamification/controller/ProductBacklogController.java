package org.framework.rodolfo.freire.git.scrumgamification.controller;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ProductBacklog;
import org.framework.rodolfo.freire.git.scrumgamification.service.ProductBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-backlogs")
public class ProductBacklogController {

    private final ProductBacklogService productBacklogService;

    @Autowired
    public ProductBacklogController(ProductBacklogService productBacklogService) {
        this.productBacklogService = productBacklogService;
    }

    @GetMapping
    public List<ProductBacklog> getAll() {
        return productBacklogService.getAll();
    }

    @GetMapping("/{id}")
    public ProductBacklog getById(@PathVariable Long id) {
        return productBacklogService.getById(id);
    }

    @PostMapping
    public ProductBacklog save(@RequestBody ProductBacklog productBacklog) {
        return productBacklogService.save(productBacklog);
    }

    @PutMapping("/{id}")
    public ProductBacklog update(@PathVariable Long id, @RequestBody ProductBacklog productBacklog) {
        // Ensure the product backlog ID in the request body matches the path variable ID
        if (!id.equals(productBacklog.getId())) {
            throw new IllegalArgumentException("Product Backlog ID in path must match the ID in the request body");
        }
        return productBacklogService.save(productBacklog);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productBacklogService.delete(id);
    }
}
