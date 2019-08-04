import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(project(":fuddle-engine"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(Dependencies.clikt)
    implementation(Dependencies.logbackClassic)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

application {
    applicationName = "fuddle"
    mainClassName = "fuddle.cli.EntryKt"
}
