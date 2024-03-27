package org.siemens.quality.dao.repo;

import org.siemens.quality.dao.entites.FgTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FgTypeMasterRepo  extends JpaRepository<FgTypeMaster,Integer> {
    @Query("select f from FgTypeMaster f where f.fgType = ?1")
    Optional<FgTypeMaster> fgTypeByName(String fgType);
}
