package org.irushu.demo.web.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class DemoRequest {
    @Schema(example= "A", required = false, title="Input")
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "DemoRequest{" +
                "input='" + input + '\'' +
                '}';
    }
}
