package com.litGame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class LitGameApplication

fun main(args: Array<String>) {
    runApplication<LitGameApplication>(*args)
}