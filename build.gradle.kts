plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.serialization") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//
//    implementation("com.azure.spring:spring-cloud-azure-starter-active-directory:5.14.0")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:3.3.1")


//    implementation("com.azure.spring:spring-cloud-azure-starter-active-directory:5.14.0")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

//    implementation("org.springframework.security:spring-security-oauth2-client")
//    implementation("org.springframework.security:spring-security-oauth2-jose")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
//    implementation("com.nimbusds:oauth2-oidc-sdk:9.9")
//    implementation("com.nimbusds:nimbus-jose-jwt:9.9")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation("org.flywaydb:flyway-core:10.15.2")
    implementation("org.flywaydb:flyway-database-postgresql:10.15.2")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
