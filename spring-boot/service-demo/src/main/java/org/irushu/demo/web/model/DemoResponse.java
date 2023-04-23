package org.irushu.demo.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DemoResponse {

    @Schema( title="Output")
    private String output;

}
