package org.irushu.demo.web.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class OpenApiConfig implements WebMvcConfigurer {

    private final SwaggerProperties swaggerProperties;

    public OpenApiConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public OpenAPI springDocOpenAPI(){
        return new OpenAPI()
                .info(
                new Info().title(swaggerProperties.getApplicationName())
                        .description(swaggerProperties.getApplicationDescription())
                        .version(swaggerProperties.getApplicationVersion())
        );
    }

}
