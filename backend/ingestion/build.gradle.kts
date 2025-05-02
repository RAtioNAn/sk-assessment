plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("org.springframework.kafka:spring-kafka:3.3.5")
}

application {
    mainClass.set("ingestion.Runner")
}

docker {
    javaApplication {
        baseImage.set("bellsoft/liberica-openjdk-debian:21.0.7-9")
        maintainer.set("rationan@fastmail.com")
        images.set(setOf("ingestion:latest"))
        mainClassName.set("ingestion.Runner")
        jvmArgs.set(listOf("-Xms256m", "-Xmx2048m"))
    }
}
