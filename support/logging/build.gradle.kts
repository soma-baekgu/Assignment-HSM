dependencies {
    api("io.github.oshai:kotlin-logging-jvm:7.0.0")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.sentry:sentry-logback:${property("sentryVersion")}")
}
