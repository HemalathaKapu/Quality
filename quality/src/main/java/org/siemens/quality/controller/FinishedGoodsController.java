package org.siemens.quality.controller;

import org.siemens.quality.business.service.FinishedGoodService;
import org.siemens.quality.sdo.FinishedGoodSdo;
import org.siemens.quality.sdo.ProcessParameterSdo;
import org.siemens.rapid.web.openapi.RapidRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FinishedGoodsController {
    @Autowired
    FinishedGoodService finishedGoodService;
    @PostMapping(path = "/finished-good")
    public ResponseEntity<String> createFinishedGood(@RequestBody FinishedGoodSdo finishedGoodSdo) {
        String message = finishedGoodService.createFinishedGood(finishedGoodSdo);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
    @GetMapping(path = "/finished-good")
    public ResponseEntity<RapidRestResponse<List<FinishedGoodSdo>>> listFinishedGoods(@RequestParam Optional<Integer> id,
                                                               @RequestParam Optional<String> subOperation) {
        List<FinishedGoodSdo> finishedGoodSdoList = finishedGoodService.listFinishedGoods(id, subOperation);
        return new ResponseEntity<>(new RapidRestResponse<>(finishedGoodSdoList,null),HttpStatus.OK);

    }
    @GetMapping(path = "/finished-good/parameter")
    public ResponseEntity<RapidRestResponse<List<ProcessParameterSdo>>> listProcessParameters(@RequestParam Integer id){
        return  new ResponseEntity<>(new RapidRestResponse<>(finishedGoodService.listProcessParameters(id),null),HttpStatus.OK);
    }
}
