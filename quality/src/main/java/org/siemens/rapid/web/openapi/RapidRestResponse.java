package org.siemens.rapid.web.openapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RapidRestResponse<T> {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T response;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<String> errors;
}
