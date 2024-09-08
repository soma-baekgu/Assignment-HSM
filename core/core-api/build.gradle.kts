tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":storage:db-core"))
    implementation(project(":core:core-domain"))
    implementation(project(":support:logging"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.security:spring-security-crypto:5.6.4")
}
