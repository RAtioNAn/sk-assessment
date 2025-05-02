plugins {
    id("buildlogic.java-application-conventions")
    id("org.flywaydb.flyway") version "11.8.0"
}

flyway {
    url = "jdbc:h2:mem:mydb"
    user = ""
    password = ""
    locations = arrayOf("classpath:migrations")
}