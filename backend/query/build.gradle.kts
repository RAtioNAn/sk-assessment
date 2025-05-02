plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("com.clickhouse:clickhouse-jdbc:0.6.5")
    implementation("io.github.pelenthium:clickhouse-dialect-spring-boot-starter:1.2.0")
    implementation("org.apache.httpcomponents.client5:httpclient5")
}