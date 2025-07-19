package com.litGame.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    @Value("\${spring.server_url}")
    private val serverUrl: String
): WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns(serverUrl)
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}
