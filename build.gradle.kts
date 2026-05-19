plugins {
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
}

val apiVersion = "26.1"

java {
    // Note: Project requires Java 25 for Paper 26.1.2 API compatibility.
    // Ensure the Gradle JVM (Settings) is set to Java 24 to support the build runner.
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("26.1.2")
        
        // Suppress terminal deprecation warnings for sun.misc.Unsafe (caused by JOML)
        jvmArgs(
            "-Xms2G", 
            "-Xmx2G", 
            "--add-opens=jdk.unsupported/sun.misc=ALL-UNNAMED"
        )
    }

    processResources {
        val props = mapOf(
            "version" to version,
            "apiVersion" to apiVersion,
            "name" to project.name,
            "main" to "${project.group}.${project.name}"
        )
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
