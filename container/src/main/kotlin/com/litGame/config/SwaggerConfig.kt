package com.litGame.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = io.swagger.v3.oas.annotations.info.Info(
        title = "LitGame API",
        version = "v1",
        description = "100억 프로젝트"
    )
)
class SwaggerConfig