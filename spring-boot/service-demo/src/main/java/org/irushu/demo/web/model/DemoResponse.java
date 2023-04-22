package org.irushu.demo.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DemoResponse {

    @Schema( title="Output")
    private String output;

}
