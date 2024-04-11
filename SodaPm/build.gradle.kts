

// For `KotlinCompile` task below
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22" // Kotlin version to use
    application // Application plugin. Also see 1️⃣ below the code
}

group = "_4Tipsy.SodaPm" // A company name, for example, `org.jetbrains`
version = "1.0-SNAPSHOT" // Version to assign to the built artifact

repositories { // Sources of dependencies. See 2️⃣
    mavenCentral() // Maven Central Repository. See 3️⃣
}

dependencies { // All the libraries you want to use. See 4️⃣
    // Copy dependencies' names after you find them in a repository
    implementation("com.moandjiezana.toml:toml4j:0.7.2")

    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0") // dunno why BUT THEY REFUSE TO IMPLEMENT!!!

    implementation("org.jprocesses:jProcesses:1.6.4")

    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation(kotlin("test")) // The Kotlin test library
}

tasks.test { // See 5️⃣
    useJUnitPlatform() // JUnitPlatform for tests. See 6️⃣
}

kotlin { // Extension for easy setup
    jvmToolchain(21) // Target version of generated JVM bytecode. See 7️⃣
}

application {
    mainClass.set("_4Tipsy.SodaPm.MainKt") // The main class of the application
}
