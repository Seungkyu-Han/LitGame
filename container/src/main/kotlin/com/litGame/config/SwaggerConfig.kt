package com.litGame.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
    @Value("\${spring.server_url}")
    private val serverUrl: String

) {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("LitGame API")
                    .version("v1")
                    .description("100억 프로젝트")
            )
            .addServersItem(Server().url(serverUrl).description("개발 서버"))

    }
}