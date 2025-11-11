import io.github.bhuyanp.gradle.theme.ThemePreset

plugins {
    java
    `java-library`
    `maven-publish`
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.github.bhuyanp.spring-banner-gradle-plugin").version("1.1")
    id("org.openapi.generator") version "7.16.0"
}

group = "io.github.bhuyanp.shipping"
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
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
    implementation("org.apache.commons:commons-lang3:3.18.0")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.github.bhuyanp.order:order-event-interface:+")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


// Client sdk generation
val clientSourceFolder = "$projectDir/build/clientSdk"
val clientTargetFolder = "$projectDir/src/clientSdk"
sourceSets {
    create("clientSdk") {
        java {
            srcDir("$clientTargetFolder/java")
            compileClasspath += sourceSets["main"].compileClasspath
            runtimeClasspath += sourceSets["main"].runtimeClasspath
        }
    }
}

openApiGenerate {
    //https://openapi-generator.tech/docs/generators/spring
    generatorName.set("spring")
    library.set("spring-http-interface")
    inputSpec.set("$projectDir/shipping-service-spec-v1.json")
    outputDir.set(clientSourceFolder)
    invokerPackage.set("${group}.client")
    apiPackage.set("${group}.client.api")
    modelPackage.set("${group}.client.model")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8-localdatetime",
            "enumPropertyNaming" to "MACRO_CASE",
            "useSpringBoot3" to "true",
            "useSpringBuiltInValidation" to "true",
            "documentationProvider" to "none",
            "annotationLibrary" to "none",
            "interfaceOnly" to "true",
            "openApiNullable" to "false",
        )
    )
}

tasks.openApiGenerate {
    doFirst {
        delete(clientSourceFolder)
        //delete(clientTargetFolder)
    }
}
val copyClientSdk = tasks.register<Copy>("copyClientSdk") {
    from("$clientSourceFolder/src/main")
    into(clientTargetFolder)
}
val compileClientSdkJavaTask = tasks.named<JavaCompile>("compileClientSdkJava") {
    dependsOn(copyClientSdk)
}
val clientSdkArtifact = tasks.register<Jar>("clientSdkJar") {
    from(compileClientSdkJavaTask)
    from(sourceSets["clientSdk"].resources)
    archiveBaseName = clientSDKArtifactId
}
val clientSDKArtifactId = project.name + "-clientsdk"
val clientSdkSourceArtifact = tasks.register<Jar>("clientSdkSourceJar") {
    dependsOn(copyClientSdk)
    from("$clientTargetFolder/java")
    archiveBaseName = clientSDKArtifactId
    archiveClassifier = "sources"
}

tasks.assemble {
    dependsOn(clientSdkArtifact, clientSdkSourceArtifact)
}
// Client sdk generation

publishing {
    publications {
        create<MavenPublication>("clientSDKPublication") {
            artifact(clientSdkArtifact)
            artifact(clientSdkSourceArtifact)
            artifactId = clientSDKArtifactId
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
    repositories {
        mavenLocal()
    }
}




tasks.withType<Test> {
    useJUnitPlatform()
}

springBanner {
    themePreset = ThemePreset.SURPRISE_ME
}
