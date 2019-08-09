import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    mavenLocal()
}

val fuddle by configurations.creating {
    extendsFrom(configurations["implementation"])
}

sourceSets {
    val main by getting
    val fuddle by creating {
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDir("src/fuddle/kotlin")
        }
        compileClasspath += main.output + fuddle
    }
}

dependencies {
    implementation("dev.fuddle:fuddle-api:0.1.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
