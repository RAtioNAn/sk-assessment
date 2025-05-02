dependencyResolutionManagement {

    versionCatalogs {
        create("libs", { from(files("../gradle/libs.versions.toml")) })
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://download.red-gate.com/maven/release")
        }
    }
}

rootProject.name = "buildSrc"
