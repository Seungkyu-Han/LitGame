package com.litGame.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
    @Value("\${springdoc.swagger-ui.title}")
    private val swaggerTitle: String
) {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info().apply{
                    title = swaggerTitle
                    description = "$swaggerTitle swagger 문서입니다."
                }
            )
    }
}