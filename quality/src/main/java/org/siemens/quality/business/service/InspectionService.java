package org.siemens.quality.business.service;

import org.siemens.quality.sdo.InspectionSdo;

import java.util.List;

public interface InspectionService {
    boolean persistInspections(InspectionSdo inspectionSdo);
    List<InspectionSdo> listInspections(Integer fgId);
}
