package org.siemens.quality.dao.repo;

import org.siemens.quality.dao.entites.ProcessParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessParametersRepo extends JpaRepository<ProcessParameters,Integer> {
}
