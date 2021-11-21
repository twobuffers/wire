// ktlint-disable max-line-length

@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package buildcfg

import org.gradle.api.JavaVersion

object Versions {
    const val JAVA_VERSION_STR = "11"
    val JAVA_VERSION = JavaVersion.VERSION_11

    // https://kotlinlang.org/docs/releases.html#release-details
    // https://github.com/JetBrains/kotlin/releases
    const val kotlin = "1.6.0"
}


object GradlePlugins {
    // https://maven.google.com/web/index.html#com.android.tools.build:gradle
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.3"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    // https://github.com/vanniktech/gradle-maven-publish-plugin/blob/master/CHANGELOG.md
    const val gradleMavenPublishPlugin = "com.vanniktech:gradle-maven-publish-plugin:0.18.0"
    // https://github.com/ben-manes/gradle-versions-plugin/releases
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.39.0"
}

object Libs {
    object Kotlin {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val kotlinStdlib7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val kotlinStdlib8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }

    object Coroutines {
        private const val version = "1.5.2"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coroutinesRx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val coroutinesDebug = "org.jetbrains.kotlinx:kotlinx-coroutines-debug:$version"
    }

    object Dagger {
        private const val version = "2.40.1" // https://github.com/google/dagger/releases
        const val dagger = "com.google.dagger:dagger:$version"
        const val daggerAndroid = "com.google.dagger:dagger-android:$version"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$version"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    const val jsr250Api = "javax.annotation:jsr250-api:1.0" // for Dagger

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.0" // https://maven.google.com/web/index.html#androidx.appcompat:appcompat
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        const val cardview = "androidx.cardview:cardview:1.0.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.2" // https://maven.google.com/web/index.html#androidx.constraintlayout:constraintlayout
        const val swypeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        // optional - Test helpers for LiveData
        const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"

        object Annotation {
            const val annotation = "androidx.annotation:annotation:1.3.0" // https://maven.google.com/web/index.html#androidx.annotation:annotation
        }

        object Activity {
            private const val version = "1.2.2"
            const val activity = "androidx.activity:activity:$version"
            const val activityKtx = "androidx.activity:activity-ktx:$version"
        }

        object Fragment {
            private const val version = "1.3.6"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Navigation {
            private const val version = "2.3.5" // latest: 2.4.0-alpha10
            const val navigationFragment = "androidx.navigation:navigation-fragment:$version"
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Lifecycle {
            private const val version = "2.4.0" // https://maven.google.com/web/index.html#androidx.lifecycle
            // ViewModel and LiveData
            const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:$version"
            const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:$version"
            // alternatively - just ViewModel
            const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:$version"
            // alternatively - just ViewModel (for Kotlin)
            const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val service = "androidx.lifecycle:lifecycle-service:$version"
            // alternatively - just LiveData
            const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata:$version"
            // alternatively - Lifecycles only (no ViewModel or LiveData). Some UI
            //     AndroidX libraries use this lightweight import for Lifecycle
            const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            // Annotations compiler (when to use: https://stackoverflow.com/a/58286301/3023806)
            const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$version"
            // alternately - if using Java8, use the following instead of lifecycle-compiler
            const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
            // optional - ReactiveStreams support for LiveData
            const val lifecycleReactivestreams = "androidx.lifecycle:lifecycle-reactivestreams:$version"
            // optional - ReactiveStreams support for LiveData (for Kotlin)
            const val lifecycleReactivestreamsKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$version"
        }

        object Test {
            private const val version = "1.4.0"
            // Core library
            const val core = "androidx.test:core:$version"
            // AndroidJUnitRunner and JUnit Rules
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"
            // Assertions
            object Ext {
                const val junitVersion = "1.1.3"
                const val junit = "androidx.test.ext:junit:$junitVersion"
                const val junitKtx = "androidx.test.ext:junit-ktx:$junitVersion"
                const val truth = "androidx.test.ext:truth:$version"
            }
            // Espresso dependencies
            object Espresso {
                private const val version = "3.4.0"
                const val espressoCore = "androidx.test.espresso:espresso-core:$version"
                const val espressoContrib = "androidx.test.espresso:espresso-contrib:$version"
                const val espressoIntents = "androidx.test.espresso:espresso-intents:$version"
                const val espressoAccessibility = "androidx.test.espresso:espresso-accessibility:$version"
                const val espressoWeb = "androidx.test.espresso:espresso-web:$version"
                const val espressoConcurrent = "androidx.test.espresso:idling-concurrent:$version"
                // The following Espresso dependency can be either "implementation"
                // or "androidTestImplementation", depending on whether you want the
                // dependency to appear on your APK's compile classpath or the test APK
                // classpath.
                const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:$version"
            }
            // UIAutomator
            const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
            // Orchestrator
            const val orchestrator = "androidx.test:orchestrator:1.2.0" // latest: 1.3.0-alpha05
        }

        object Security {
            const val securityCrypto = "androidx.security:security-crypto:1.1.0-alpha03" // 1.0.0-rc04
            const val securityIdentityCredential = "androidx.security:security-identity-credential:1.0.0-alpha01"
        }

        // Persistence

        object Room {
            private const val roomVersion = "2.3.0"
            const val roomKtx = "androidx.room:room-ktx:$roomVersion"
            const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
            const val roomTestImpl = "androidx.room:room-testing:$roomVersion"
        }
    }

}
