import dev.architectury.pack200.java.Pack200Adapter

plugins {
    kotlin("jvm") version("1.6.21")
    id("dev.architectury.architectury-pack200") version("0.1.3")
    id("gg.essential.loom") version("0.10.0.4")
}

val projectName: String by project
val projectId: String by project
val projectVersion: String by project
val projectGroup: String by project
val mcVersion: String = property("minecraft.version")?.toString() ?: throw IllegalStateException("minecraft.version is not set...")

version = projectVersion
group = projectGroup

loom {
    runConfigs {
        getByName("client") {
            isIdeConfigGenerated = true
        }
    }

    forge {
        pack200Provider.set(Pack200Adapter())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
}

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        inputs.property("version", projectVersion)
        inputs.property("mcversion", mcVersion)
        inputs.property("name", projectName)
        inputs.property("id", projectId)

        filesMatching("mcmod.info") {
            expand(
                    "id" to projectId,
                    "name" to projectName,
                    "version" to projectVersion,
                    "mcversion" to mcVersion
            )
        }
    }

    named<Jar>("jar") {
        archiveBaseName.set(projectName)
        manifest.attributes(
            "ModSide" to "CLIENT",
            "ForceloadAsMod" to true,
            "TweakOrder" to "0"
        )
    }
}

kotlin {
    jvmToolchain {
        check(this is JavaToolchainSpec)
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}
