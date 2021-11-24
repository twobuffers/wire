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
        gradlePluginPortal()
    }
    dependencies {
        classpath(buildcfg.GradlePlugins.androidGradlePlugin)
        classpath(buildcfg.GradlePlugins.kotlinGradlePlugin)
        classpath(buildcfg.GradlePlugins.gradleMavenPublishPlugin)
        classpath(buildcfg.GradlePlugins.gradleVersionsPlugin)
        classpath(buildcfg.GradlePlugins.googleServices)
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
            allWarningsAsErrors = false
        }
    }
}

apply(plugin = "com.github.ben-manes.versions")
tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    checkForGradleUpdate = false
    outputFormatter = "plain,html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
    // report only stable versions:
    fun isStable(version: String): Boolean {
        val stableKeyword = version.toUpperCase() in setOf("RELEASE", "FINAL", "GA")
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        return stableKeyword || regex.matches(version)
    }
    rejectVersionIf { isStable(this.currentVersion) && !isStable(this.candidate.version) }
    // In other words, if the lib uses stable release, we'll get update only on stable versions.
    // If the lib uses any non-stable (alpha/beta/RC), we'll get update about the newer non-stable.
}
