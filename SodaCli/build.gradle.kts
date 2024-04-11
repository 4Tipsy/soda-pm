

// For `KotlinCompile` task below
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22" // Kotlin version to use
    application // Application plugin. Also see 1️⃣ below the code
}

group = "_4Tipsy.SodaCli" // A company name, for example, `org.jetbrains`
version = "1.0-SNAPSHOT" // Version to assign to the built artifact

repositories { // Sources of dependencies. See 2️⃣
    mavenCentral() // Maven Central Repository. See 3️⃣
}

dependencies { // All the libraries you want to use. See 4️⃣
    // Copy dependencies' names after you find them in a repository
    implementation("com.moandjiezana.toml:toml4j:0.7.2")

    implementation("com.github.freva:ascii-table:1.8.0")
    implementation("com.github.ajalt.clikt:clikt:4.2.2")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    testImplementation(kotlin("test")) // The Kotlin test library
}

tasks.test { // See 5️⃣
    useJUnitPlatform() // JUnitPlatform for tests. See 6️⃣
}

kotlin { // Extension for easy setup
    jvmToolchain(21) // Target version of generated JVM bytecode. See 7️⃣
}

application {
    mainClass.set("_4Tipsy.SodaCli.MainKt") // The main class of the application
}
