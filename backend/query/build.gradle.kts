plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("com.clickhouse:clickhouse-jdbc:0.6.5")
    implementation("io.github.pelenthium:clickhouse-dialect-spring-boot-starter:1.2.0")
    implementation("org.apache.httpcomponents.client5:httpclient5")
}

application {
    mainClass.set("query.Runner")
}

docker {
    javaApplication {
        baseImage.set("bellsoft/liberica-openjdk-debian:21.0.7-9")
        maintainer.set("rationan@fastmail.com")
        images.set(setOf("query:latest"))
        mainClassName.set("query.Runner")
        jvmArgs.set(listOf("-Xms256m", "-Xmx2048m"))
    }
}