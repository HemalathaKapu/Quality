package org.siemens.quality.sdo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessParameterSdo {
    private Integer id;
    private String parameter;
    private String value;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String units;
}
