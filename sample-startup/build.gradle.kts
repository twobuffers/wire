import buildcfg.Libs

plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION
    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        applicationId = "com.twobuffers.wire.sample_initializer"
        versionCode = 1000001
        versionName = "1.0.1"
    }
//    buildFeatures {
//        buildConfig = true
//        resValues = true
//    }
//    buildTypes {
//        debug {
//            minifyEnabled false
//        }
//        release {
//            minifyEnabled true
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//
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

    implementation(project(':wire-di-annotations'))
    implementation(project(':wire-initializer'))

    implementation(Libs.Dagger.dagger)
    implementation(Libs.Dagger.daggerAndroid)
    implementation (Libs.Dagger.daggerAndroidSupport)
    kapt(Libs.Dagger.daggerCompiler)
    kapt(Libs.Dagger.daggerAndroidProcessor)

    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.startup)
}
