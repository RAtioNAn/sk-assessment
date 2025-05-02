plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.bmuschko.docker-java-application:com.bmuschko.docker-java-application.gradle.plugin:9.4.0")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.4.5")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.7")
}
