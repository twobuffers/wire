import buildcfg.Libs

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
    api(project(":wire-retrofit"))
    api(Libs.Dagger.dagger)
    kapt(Libs.Dagger.daggerCompiler)
}
