pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.architectury.dev/")
        maven("https://maven.fabricmc.net/")
        maven("https://repo.essential.gg/repository/maven-public/")
        maven("https://maven.minecraftforge.net/")
        maven("https://jitpack.io/")
    }
}

val projectName: String by settings
rootProject.name = projectName