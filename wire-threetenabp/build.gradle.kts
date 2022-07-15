import buildcfg.Deps

plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.MAVEN_PUBLISH)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION
    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
    }
    buildTypes {
        getByName("release") {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = buildcfg.Versions.JAVA_VERSION
        targetCompatibility = buildcfg.Versions.JAVA_VERSION
    }
}

dependencies {
    api(project(":wire-di-annotations-common"))
    api(project(":wire-initializer"))
    api(Deps.OrgJetBrains.Kotlin.kotlinStdlib)
    api(Deps.OrgJetBrains.KotlinX.Coroutines.kotlinxCoroutinesCore)
    api(Deps.ComGoogle.Dagger.dagger)
    kapt(Deps.ComGoogle.Dagger.daggerCompiler)
    api(Deps.ComJakeWharton.threeTenABP)
}
