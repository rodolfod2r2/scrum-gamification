package org.framework.rodolfo.freire.git.scrumgamification.repository;

import org.framework.rodolfo.freire.git.scrumgamification.entities.ScrumMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {
}
