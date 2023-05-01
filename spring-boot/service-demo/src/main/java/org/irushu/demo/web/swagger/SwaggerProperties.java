package org.irushu.demo.web.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private String applicationName;

    private String applicationVersion;

    private String applicationDescription;

}
