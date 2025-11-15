import io.github.bhuyanp.gradle.theme.ThemePreset

plugins {
    java
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.github.bhuyanp.spring-banner-gradle-plugin").version("1.1")
}

group = "io.github.bhuyanp.order"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("io.temporal:temporal-spring-boot-starter:1.31.0")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
    implementation("org.apache.commons:commons-lang3:3.18.0")
    implementation("io.github.bhuyanp.notification:notification-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.order:order-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.inventory:inventory-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.payment:payment-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.shipping:shipping-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.order:order-event-interface:+")
    implementation("org.springframework.kafka:spring-kafka")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("io.temporal:temporal-testing:1.24.1")
}
dependencies {
    implementation("io.github.bhuyanp.order:service-common:0.0.1-SNAPSHOT")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.temporal:temporal-spring-boot-starter:1.31.0")
    implementation("org.springframework.kafka:spring-kafka")

    implementation("io.github.bhuyanp.notification:notification-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.order:order-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.inventory:inventory-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.payment:payment-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.shipping:shipping-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.order:order-event-interface:+")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.processResources {
    filesMatching("application.yml") {
        expand(properties)
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}

springBanner {
    themePreset = ThemePreset.SURPRISE_ME
}
