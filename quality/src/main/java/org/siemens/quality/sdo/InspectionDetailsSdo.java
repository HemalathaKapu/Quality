package org.siemens.quality.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InspectionDetailsSdo {
    private Integer id;
    private String value;
    private Integer measurementId;
    private String measurementName;
}
