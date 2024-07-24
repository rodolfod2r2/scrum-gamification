package org.framework.rodolfo.freire.git.scrumgamification.repository;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
}
