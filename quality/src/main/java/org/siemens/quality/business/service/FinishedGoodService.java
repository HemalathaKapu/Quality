package org.siemens.quality.business.service;

import org.siemens.quality.sdo.FinishedGoodSdo;
import org.siemens.quality.sdo.ProcessParameterSdo;

import java.util.List;
import java.util.Optional;


public interface FinishedGoodService {
    String createFinishedGood(FinishedGoodSdo finishedGoodSdo);
    List<FinishedGoodSdo> listFinishedGoods(Optional<Integer> id,Optional<String> subOperation);

    List<ProcessParameterSdo> listProcessParameters(Integer id);


}
