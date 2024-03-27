package org.siemens.quality.dao.repo;

import org.siemens.quality.dao.entites.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}