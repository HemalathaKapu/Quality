package org.siemens.quality.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InspectionSdo {
    private Integer id;
    private String inspectorName;
    private String status;
    private String remarks;
    private Integer fg;
    private List<InspectionDetailsSdo> inspections;
}
