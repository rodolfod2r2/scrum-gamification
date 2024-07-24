package org.framework.rodolfo.freire.git.scrumgamification.repository;

import org.framework.rodolfo.freire.git.scrumgamification.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
