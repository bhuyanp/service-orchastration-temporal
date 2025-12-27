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
    implementation("io.github.bhuyanp.order:service-common:0.0.1-SNAPSHOT")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.temporal:temporal-spring-boot-starter:1.31.0")

    implementation("io.github.bhuyanp.order:order-event-interface:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.notification:notification-service-clientsdk:+")
    implementation("io.github.bhuyanp.order:order-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.inventory:inventory-service-clientsdk:0.0.1-SNAPSHOT")
    implementation("io.github.bhuyanp.payment:payment-service-clientsdk:0.0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("io.temporal:temporal-testing:1.24.1")
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
