package org.siemens.quality.sdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinishedGoodSdo {
    private Integer id;
    private String fgId;
    private String subOperation;
    private String matCode;
    private String matDescription;
    private List<ProcessParameterSdo> processParameterSdoList;
}
