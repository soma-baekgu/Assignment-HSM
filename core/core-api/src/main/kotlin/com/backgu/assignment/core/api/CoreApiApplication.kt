package com.backgu.assignment.core.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(
    scanBasePackages = [
        "com.backgu.assignment.core.api",
        "com.backgu.assignment.core.domain",
        "com.backgu.assignment.storage.db.core",
    ],
)
class CoreApiApplication

fun main(args: Array<String>) {
    runApplication<CoreApiApplication>(*args)
}
