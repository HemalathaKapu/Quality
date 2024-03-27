package org.siemens.quality.controller;

import org.siemens.quality.business.service.InspectionService;
import org.siemens.quality.sdo.FinishedGoodSdo;
import org.siemens.quality.sdo.InspectionSdo;
import org.siemens.rapid.web.openapi.RapidRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InspectionController {
    @Autowired
    InspectionService inspectionService;

    @PostMapping(path = "/inspection")
    public ResponseEntity<RapidRestResponse<Boolean>> createInspection(@RequestBody InspectionSdo inspectionSdo) {
        boolean b = inspectionService.persistInspections(inspectionSdo);
        if(b) {
            return new ResponseEntity<>(new RapidRestResponse<>(b,null), HttpStatus.OK);
        } else {
            List<String> errors = new ArrayList<>();
            errors.add("Invalid business flow or invalid valid/ insufficient measurements received");
            return new ResponseEntity<>(new RapidRestResponse<>(null,errors), HttpStatus.OK);
        }

    }
}
