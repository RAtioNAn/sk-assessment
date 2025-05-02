plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("com.clickhouse:clickhouse-jdbc:0.8.5")
}