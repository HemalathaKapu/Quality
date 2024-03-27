package org.siemens.quality.business.serviceimpl;

import org.siemens.quality.business.service.InspectionService;
import org.siemens.quality.dao.entites.FinishedGood;
import org.siemens.quality.dao.entites.Inspection;
import org.siemens.quality.dao.entites.InspectionDetails;
import org.siemens.quality.dao.entites.Measurement;
import org.siemens.quality.dao.repo.FinishedGoodsRepo;
import org.siemens.quality.dao.repo.InspectionDetailsRepository;
import org.siemens.quality.dao.repo.InspectionRepository;
import org.siemens.quality.dao.repo.MeasurementsRepository;
import org.siemens.quality.sdo.InspectionDetailsSdo;
import org.siemens.quality.sdo.InspectionSdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InspectionServiceImpl implements InspectionService {
    @Autowired
    private FinishedGoodsRepo finishedGoodsRepo;
    @Autowired
    private MeasurementsRepository measurementsRepository;
    @Autowired
    private InspectionDetailsRepository inspectionDetailsRepository;
    @Autowired
    private InspectionRepository inspectionRepository;
    @Override
    public boolean persistInspections(InspectionSdo inspectionSdo) {

        Optional<FinishedGood> optionalFinishedGood = finishedGoodsRepo.findById(inspectionSdo.getFg());
        FinishedGood fg = optionalFinishedGood.orElseThrow(() -> new RuntimeException("Invalid finished good"));

        if(!isValidBusinessProcessFlow(fg)) {
            return false;
        }
        if(!isValidMeasurements(inspectionSdo,fg)) {
            return  false;
        }

        Inspection inspection = new Inspection();
        inspection.setFg(fg);
        inspection.setStatus(inspectionSdo.getStatus());
        inspection.setRemarks(inspectionSdo.getRemarks());
        inspection.setInspectorName(inspectionSdo.getInspectorName());
        Inspection newInspection = inspectionRepository.save(inspection);

        List<InspectionDetails> inspectionDetailList = new ArrayList<>();
        inspectionSdo.getInspections().forEach(measurement -> {
            Optional<Measurement> optionalMeasurement = measurementsRepository.findById(measurement.getMeasurementId());
            Measurement measurement1 = optionalMeasurement.orElseThrow(()-> new RuntimeException("Invalid measurement"));
            InspectionDetails inspectionDetails = new InspectionDetails();
            inspectionDetails.setMeasurement(measurement1);
            inspectionDetails.setInspection(newInspection);
            inspectionDetails.setValue(measurement.getValue());
            inspectionDetailList.add(inspectionDetails);

        });

        inspectionDetailsRepository.saveAll(inspectionDetailList);

        return true;
    }

    private boolean isValidBusinessProcessFlow(FinishedGood fg) {
        Set<Inspection> inspections = fg.getInspections();
        List<Inspection> approvedOrRejectedInspections = inspections.stream()
                .filter(i -> i.getStatus().equalsIgnoreCase("approved") ||
                        i.getStatus().equalsIgnoreCase("rejected"))
                .toList();
        return approvedOrRejectedInspections.isEmpty();
    }
    private boolean isValidMeasurements(InspectionSdo inspectionSdo,FinishedGood fg) {
        List<Integer> measurementIdsPassedByUser = inspectionSdo.getInspections().stream()
                .map(InspectionDetailsSdo::getMeasurementId).toList();

        List<Integer> measurementsToBeRecorded = fg.getFgType().getMeasurements()
                .stream().map(Measurement::getId).toList();

        return measurementIdsPassedByUser.containsAll(measurementsToBeRecorded);
    }

    @Override
    public List<InspectionSdo> listInspections(Integer fgId) {

        return null;
    }
}
