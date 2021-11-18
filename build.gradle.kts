buildscript {
    extra["buildConfig"] = mapOf(
        "compileSdk" to 31,
        "minSdk" to 25,
        "targetSdk" to 30,
        "buildTools" to "31.0.0"
    )
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(buildcfg.GradlePlugins.androidGradlePlugin)
        classpath(buildcfg.GradlePlugins.kotlinGradlePlugin)
        classpath(buildcfg.GradlePlugins.gradleMavenPublishPlugin)
    }
}

subprojects {
    version = project.property("VERSION_NAME")!!
    group = project.property("GROUP")!!

    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = buildcfg.Versions.JAVA_VERSION_STR
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors = true
        }
    }
}
