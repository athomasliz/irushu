package org.irushu.demo.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DemoRequest {

    @Schema(example= "A", title="Input Value")
    private String input;

}
