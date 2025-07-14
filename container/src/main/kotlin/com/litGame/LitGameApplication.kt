package com.litGame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LitGameApplication

fun main(args: Array<String>) {
    runApplication<LitGameApplication>(*args)
}