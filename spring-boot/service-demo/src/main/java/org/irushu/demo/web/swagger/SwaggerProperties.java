package org.irushu.demo.web.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private String applicationName;

    private String applicationVersion;

    private String applicationDescription;

}
