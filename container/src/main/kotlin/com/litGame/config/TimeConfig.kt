package com.litGame.config

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import java.time.ZoneId
import java.util.*

@Configuration
class TimeConfig {

    @PostConstruct
    fun setupTimeZoneAndLocale() {
        Locale.setDefault(Locale.KOREA)
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")))
    }
}