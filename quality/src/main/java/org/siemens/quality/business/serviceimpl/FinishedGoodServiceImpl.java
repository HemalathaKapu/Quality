package org.siemens.quality.business.serviceimpl;

import org.hibernate.mapping.Collection;
import org.siemens.quality.business.service.FinishedGoodService;
import org.siemens.quality.common.Constants;
import org.siemens.quality.dao.entites.FgTypeMapper;
import org.siemens.quality.dao.entites.FgTypeMaster;
import org.siemens.quality.dao.entites.FinishedGood;
import org.siemens.quality.dao.entites.ProcessParameters;
import org.siemens.quality.dao.repo.FgTypeMapperRepo;
import org.siemens.quality.dao.repo.FgTypeMasterRepo;
import org.siemens.quality.dao.repo.FinishedGoodsRepo;
import org.siemens.quality.dao.repo.ProcessParametersRepo;
import org.siemens.quality.sdo.FinishedGoodSdo;
import org.siemens.quality.sdo.ProcessParameterSdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinishedGoodServiceImpl implements FinishedGoodService {
    @Autowired
    FgTypeMapperRepo fgTypeMapperRepo;
    @Autowired
    FgTypeMasterRepo fgTypeMasterRepo;
    @Autowired
    FinishedGoodsRepo finishedGoodsRepo;
    @Autowired
    ProcessParametersRepo processParametersRepo;
    @Override
    public String createFinishedGood(FinishedGoodSdo finishedGoodSdo) {

        FinishedGood finishedGood = new FinishedGood();
        finishedGood.setMatCode(finishedGoodSdo.getMatCode());
        finishedGood.setMatDescription(finishedGoodSdo.getMatDescription());
        finishedGood.setSubOperation(finishedGoodSdo.getSubOperation());
        finishedGood.setFgType(getFgType(finishedGood.getMatCode()));
        finishedGood.setFgId(finishedGoodSdo.getFgId());
        FinishedGood newlyCreatedFg = finishedGoodsRepo.save(finishedGood);

        List<ProcessParameters> processParameters = new ArrayList<>();
        List<ProcessParameterSdo> processParameterSdoList = finishedGoodSdo.getProcessParameterSdoList();

        processParameterSdoList.forEach(sdo ->{
            ProcessParameters parameter = new ProcessParameters();
            parameter.setFinishedGood(newlyCreatedFg);
            parameter.setParameter(sdo.getParameter());
            parameter.setValue(sdo.getValue());
            parameter.setUnits(sdo.getUnits());
            processParameters.add(parameter);
        });

        processParametersRepo.saveAll(processParameters);

        return "Fg created";
    }

    @Override
    public List<FinishedGoodSdo> listFinishedGoods(Optional<Integer> id, Optional<String> subOperation)
    {
        if(id.isEmpty() && subOperation.isEmpty()) {
            List<FinishedGood> finishedGoods = finishedGoodsRepo.findAll();
            return convertToFinishedGoodSdo(finishedGoods);
        } else if(id.isPresent()) {
            Optional<FinishedGood> finishedGood = finishedGoodsRepo.findById(id.get());
            if(finishedGood.isPresent()) {
                return convertToFinishedGoodSdo(Collections.singletonList(finishedGood.get()));
            }
            else {
                return Collections.emptyList();
            }
        }  else {
            List<FinishedGood> finishedGoods = finishedGoodsRepo.findBySubOperation(subOperation.get());
            return convertToFinishedGoodSdo(finishedGoods);
        }

    }

    @Override
    public List<ProcessParameterSdo> listProcessParameters(Integer id) {
        Optional<FinishedGood> finishedGood = finishedGoodsRepo.findById(id);
        if(finishedGood.isPresent()){
            Set<ProcessParameters> processParameters = finishedGood.get().getProcessParameters();
            List<ProcessParameterSdo> processParameterSdos = convertToProcessParameterSdo(processParameters);
            return processParameterSdos;
        }
        else{
            throw new RuntimeException("Invalid Id");
        }
    }


    private FgTypeMaster getFgType(String matCode) {
        String itemCode = matCode.substring(0,3);
        Optional<FgTypeMapper> optionalFgTypeMapper = fgTypeMapperRepo.fgTypeByItemCode(itemCode);
        if(optionalFgTypeMapper.isPresent()) {
            return optionalFgTypeMapper.get().getFgType();
        } else {
            Optional<FgTypeMaster> optionalFgTypeMaster = fgTypeMasterRepo.fgTypeByName(Constants.OTHERS);
            return optionalFgTypeMaster.orElseThrow(() -> new RuntimeException("Others fg type not fount"));
        }

    }

    private FinishedGoodSdo convertToFinishedGoodSdo(FinishedGood fg) {
        FinishedGoodSdo sdo = new FinishedGoodSdo();
        sdo.setId(fg.getId());
        sdo.setFgId(fg.getFgId());
        sdo.setSubOperation(fg.getSubOperation());
        sdo.setMatCode(fg.getMatCode());
        sdo.setMatDescription(fg.getMatDescription());
        sdo.setProcessParameterSdoList(convertToProcessParameterSdo(fg.getProcessParameters()));
        return sdo;
    }

    private List<FinishedGoodSdo> convertToFinishedGoodSdo(List<FinishedGood> fgList) {
        return fgList.stream().map(i-> convertToFinishedGoodSdo(i)).collect(Collectors.toList());
    }
    private ProcessParameterSdo convertToProcessParameterSdo(ProcessParameters pp) {
        ProcessParameterSdo sdo = new ProcessParameterSdo();
        sdo.setId(pp.getId());
        sdo.setParameter(pp.getParameter());
        sdo.setValue(pp.getValue());
        sdo.setUnits(pp.getUnits());
        return sdo;
    }
    private List<ProcessParameterSdo> convertToProcessParameterSdo(Set<ProcessParameters> ppSet) {
        List<ProcessParameterSdo> parametersList = new ArrayList<>();
        ppSet.forEach(pp ->{
            parametersList.add(convertToProcessParameterSdo(pp));
        });
        return parametersList;
    }
}
