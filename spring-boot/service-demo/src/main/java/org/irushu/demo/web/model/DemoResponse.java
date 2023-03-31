package org.irushu.demo.web.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class DemoResponse {

    @Schema(required = false, title="Output")
    private String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "DemoResponse{" +
                "output='" + output + '\'' +
                '}';
    }
}
