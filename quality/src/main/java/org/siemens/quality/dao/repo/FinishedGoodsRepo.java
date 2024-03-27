package org.siemens.quality.dao.repo;

import org.siemens.quality.dao.entites.FinishedGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinishedGoodsRepo extends JpaRepository<FinishedGood,Integer> {
    @Query("select f from FinishedGood f where upper(f.subOperation) = upper(?1)")
    List<FinishedGood> findBySubOperation(String subOperation);
}
