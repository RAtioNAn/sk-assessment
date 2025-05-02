plugins {
    java
    application
    id("com.bmuschko.docker-java-application")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

application {
    mainClass.set("${project.name}.Runner")
}

docker {
    javaApplication {
        baseImage.set("bellsoft/liberica-openjdk-debian:21.0.7-9")
        maintainer.set("rationan@fastmail.com")
        images.set(setOf("ingestion:latest"))
        mainClassName.set(application.mainClass.get())
        jvmArgs.set(listOf("-Xms256m", "-Xmx2048m"))
    }
}