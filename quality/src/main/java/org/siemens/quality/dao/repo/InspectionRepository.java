package org.siemens.quality.dao.repo;

import org.siemens.quality.dao.entites.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
}