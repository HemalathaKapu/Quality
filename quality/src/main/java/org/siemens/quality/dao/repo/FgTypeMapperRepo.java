package org.siemens.quality.dao.repo;

import org.siemens.quality.dao.entites.FgTypeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FgTypeMapperRepo extends JpaRepository<FgTypeMapper,Integer> {
    @Query("select f from FgTypeMapper f where f.itemCode = ?1")
    Optional<FgTypeMapper> fgTypeByItemCode(String itemCode);
}
