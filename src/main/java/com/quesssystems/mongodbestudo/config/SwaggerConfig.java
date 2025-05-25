package com.quesssystems.mongodbestudo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "API", version = "v1.0.0",
                description = "API desenvolvida durante um curso de modelagem de dados para MongoDB")
)
public class SwaggerConfig {

}
