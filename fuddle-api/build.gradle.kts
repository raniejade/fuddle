import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("scripting-jvm"))
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

publishing {
    publications {
        register("maven", MavenPublication::class) {
            groupId = "dev.fuddle"
            artifactId = "fuddle-api"
            from(components["java"])
        }
    }
}
