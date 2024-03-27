package org.siemens.quality.dao.repo;

import org.siemens.quality.dao.entites.InspectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InspectionDetailsRepository extends JpaRepository<InspectionDetails, Integer> {
}