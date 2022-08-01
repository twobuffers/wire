// ktlint-disable max-line-length

@file:Suppress("unused", "MemberVisibilityCanBePrivate", "RemoveRedundantQualifierName")

package buildcfg

// Android Studio:
// https://developer.android.com/studio/releases
// https://developer.android.com/studio/preview/features

object Deps {

    object AndroidX {
        // Sources:
        // - repo: https://android.googlesource.com/platform/frameworks/support/+/HEAD/
        // - code search: https://cs.android.com/androidx
        // Versions: https://developer.android.com/jetpack/androidx/versions

        object Annotation {
            const val annotation = "androidx.annotation:annotation:1.4.0"
        }

        // MVN: https://maven.google.com/web/index.html#androidx.appcompat:appcompat
        const val appcompat = "androidx.appcompat:appcompat:1.4.2"

        object Activity {
            // INFO: https://developer.android.com/jetpack/androidx/releases/activity
            // MVN:  https://maven.google.com/web/index.html#androidx.activity
            private const val version = "1.5.0"
            const val activity = "androidx.activity:activity:$version"
            const val activityKtx = "androidx.activity:activity-ktx:$version"
            const val activityCompose = "androidx.activity:activity-compose:$version"
        }

        object ArchCore {
            // https://maven.google.com/web/index.html#android.arch.core
            // https://mvnrepository.com/artifact/androidx.arch.core/core-testing
            const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        }

        const val cardview = "androidx.cardview:cardview:1.0.0"

        // https://developer.android.com/jetpack/androidx/releases/constraintlayout
        // https://maven.google.com/web/index.html#androidx.constraintlayout:constraintlayout
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        const val coreKtx = "androidx.core:core-ktx:1.8.0"

        object Compose {
            // Versions: https://developer.android.google.cn/jetpack/androidx/releases/compose?hl=en#versions
            // Sources: https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-compose-release/
            const val version = "1.2.0-rc03"  // stable: 1.1.1

            object Animation {
                // https://developer.android.com/jetpack/androidx/releases/compose-animation
                // https://maven.google.com/web/index.html#androidx.compose.animation
                const val animation = "androidx.compose.animation:animation:$version"
            }
            object Compiler {
                // CHANGELOG: https://developer.android.com/jetpack/androidx/releases/compose-compiler
                // MAVEN:     https://maven.google.com/web/index.html#androidx.compose.compiler
                // Compatibility map:
                // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
                // 1.0.5      - requires Kotlin 1.5.31
                // 1.1.0-rc01 - requires Kotlin 1.6.0
                // 1.1.0-rc02 - requires Kotlin 1.6.10
                // 1.2.0      - requires Kotlin 1.7.0
                // to support newer Kotlin version use develop builds:
                // https://androidx.dev/storage/compose-compiler/repository
                // more about versioning of Compose Compiler:
                // https://android-developers.googleblog.com/2022/06/independent-versioning-of-Jetpack-Compose-libraries.html
                const val version = "1.2.0"
                const val compiler = "androidx.compose.compiler:compiler:$version"
                // NOTE: Compose Compiler's version can be set explicitly
                //       with android.composeOptions.kotlinCompilerExtensionVersion.
            }
            object Foundation {
                // https://developer.android.com/jetpack/androidx/releases/compose-foundation
                // https://maven.google.com/web/index.html#androidx.compose.foundation
                const val foundation = "androidx.compose.foundation:foundation:$version"
            }
            object Material {
                // https://developer.android.com/jetpack/androidx/releases/compose-material
                // https://maven.google.com/web/index.html#androidx.compose.material
                const val material = "androidx.compose.material:material:$version"
                const val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
                const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            }
            object Runtime {
                // https://developer.android.com/jetpack/androidx/releases/compose-runtime
                // https://maven.google.com/web/index.html#androidx.compose.runtime
                const val runtime = "androidx.compose.runtime:runtime:$version"
                const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
                const val runtimeRxjava2 = "androidx.compose.runtime:runtime-rxjava2:$version"
            }
            object Ui {
                // https://developer.android.com/jetpack/androidx/releases/compose-ui
                // https://maven.google.com/web/index.html#androidx.compose.ui
                const val ui = "androidx.compose.ui:ui:$version"
                // Tooling support (Previews, etc.)
                const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
                const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
                const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
            }
        }

        object DataBinding {
            // https://maven.google.com/web/index.html#androidx.databinding
            // https://mvnrepository.com/artifact/androidx.databinding/databinding-compiler
            private const val version = Deps.ComAndroidToolsBuild.agpVersion
            const val databindingCompiler = "androidx.databinding:databinding-compiler:$version"
            const val databindingRuntime = "androidx.databinding:databinding-runtime:$version"
            const val viewBinding = "androidx.databinding:viewbinding:$version"
            // You usually don't import these libraries. They are by default provided by AGP.
            // The only reason to provide necessary symbols for libraries databinding or viewbinding related.
        }

        object Fragment {
            // CHANGELOG: https://developer.android.com/jetpack/androidx/releases/fragment
            // MVN:       https://maven.google.com/web/index.html#androidx.fragment
            private const val version = "1.5.0"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Hilt {
            // https://developer.android.com/jetpack/androidx/releases/hilt
            // https://maven.google.com/web/index.html#androidx.hilt
            private const val version = "1.0.0"
            const val hiltCommon = "androidx.hilt:hilt-common:$version"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:$version"
            const val hiltLifecycleViewmodel = "hilt-lifecycle-viewmodel:$version"
            const val hiltNavigation = "hilt-navigation:$version"
            const val hiltNavigationCompose = "hilt-navigation-compose:$version"
            const val hiltNavigationFragment = "hilt-navigation-fragment:$version"
            const val hiltWork = "androidx.hilt:hilt-work:$version"
        }

        const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"

        object Lifecycle {
            // INFO: https://developer.android.com/jetpack/androidx/releases/lifecycle
            // https://maven.google.com/web/index.html#androidx.lifecycle
            // https://mvnrepository.com/artifact/androidx.lifecycle
            // Sources: https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/lifecycle/
            private const val version = "2.5.0"
            // ViewModel and LiveData
            const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:$version"
            const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:$version"
            // alternatively - just ViewModel
            const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:$version"
            const val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            // alternatively - just ViewModel (for Kotlin)
            const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifecycleService = "androidx.lifecycle:lifecycle-service:$version"
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

        object Navigation {
            // INFO: https://developer.android.com/jetpack/androidx/releases/navigation
            // MVN:  https://maven.google.com/web/index.html#androidx.navigation
            private const val version = "2.5.0"
            const val navigationFragment = "androidx.navigation:navigation-fragment:$version"
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        // https://developer.android.com/jetpack/androidx/releases/recyclerview
        // https://maven.google.com/web/index.html#androidx.recyclerview:recyclerview
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"

        object Room {
            // https://maven.google.com/web/index.html#androidx.room
            // https://developer.android.com/jetpack/androidx/releases/room
            private const val roomVersion = "2.4.2"
            const val roomKtx = "androidx.room:room-ktx:$roomVersion"
            const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
            const val roomTestImpl = "androidx.room:room-testing:$roomVersion"
        }

        object Security {
            // https://maven.google.com/web/index.html#androidx.security
            const val securityCrypto = "androidx.security:security-crypto:1.1.0-alpha03"                          // https://maven.google.com/web/index.html#androidx.security:security-crypto
            const val securityCryptoKtx = "androidx.security:security-crypto:1.1.0-alpha03"                       // https://maven.google.com/web/index.html#androidx.security:security-crypto-ktx
            const val securityIdentityCredential = "androidx.security:security-identity-credential:1.0.0-alpha03" // https://maven.google.com/web/index.html#androidx.security:security-identity-credential
        }

        const val swypeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0" // latest: 1.2.0-alpha01

        object Test {
            private const val version = "1.4.0"
            // Core library
            const val core = "androidx.test:core:$version"
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
            // AndroidJUnitRunner
            const val runner = "androidx.test:runner:$version"
            // JUnit Rules
            const val rules = "androidx.test:rules:$version"
            // Orchestrator
            const val orchestrator = "androidx.test:orchestrator:1.2.0" // latest: 1.3.0-alpha05
            // UIAutomator
            const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
        }

        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0" // latest: 1.1.0-alpha01
    }

    object AppCashTurbine {
        const val turbine = "app.cash.turbine:turbine:0.8.0"
    }

    object ComAirbnbAndroid {

        object Epoxy {
            // https://github.com/airbnb/epoxy/releases
            private const val version = "3.11.0" // lastest: 4.0.0-beta5
            const val epoxy = "com.airbnb.android:epoxy:$version"
            const val paging = "com.airbnb.android:epoxy-paging:$version"
            const val epoxyDataBinding = "com.airbnb.android:epoxy-databinding:$version"
            const val epoxyProcessor = "com.airbnb.android:epoxy-processor:$version"
        }

        object EpoxyBeta {
            // https://github.com/airbnb/epoxy/releases
            private const val version = "5.0.0-beta05"
            const val epoxy = "com.airbnb.android:epoxy:$version"
            const val epoxyDataBinding = "com.airbnb.android:epoxy-databinding:$version"
            const val epoxyProcessor = "com.airbnb.android:epoxy-processor:$version"
        }

        object Mavericks {
            // https://search.maven.org/search?q=g:com.airbnb.android
            // https://github.com/airbnb/mavericks/releases
            private const val version = "2.7.0"
            const val mavericks = "com.airbnb.android:mavericks:$version"
            const val mavericksCompose = "com.airbnb.android:mavericks-compose:$version"
            const val mavericksHilt = "com.airbnb.android:mavericks-hilt:$version"
            const val mavericksNavigation = "com.airbnb.android:mavericks-navigation:$version"
            const val mavericksRxJava2 = "com.airbnb.android:mavericks-rxjava2:$version"
        }

        // On version bump up, keep in mind about updating `MyBaseMvRxFragment`.
        const val mvrx = "com.airbnb.android:mvrx:1.5.1" // latest: 2.0.0-alpha3

        const val lottie = "com.airbnb.android:lottie:3.4.1"
    }

    object ComAndroidToolsBuild {
        // Android Gradle Plugin
        // https://developer.android.com/studio/releases/gradle-plugin
        // https://maven.google.com/web/index.html#com.android.tools.build:gradle
        const val agpVersion = "7.2.1"
        const val gradle = "com.android.tools.build:gradle:$agpVersion"
    }

    object ComAmazonAws {
        object AwsAndroid {
            private const val version = "2.19.2"
            private const val versionForApi21 = "2.16.0" // versions 2.16.1 to 2.16.4 crashes
            const val awsAndroidSdkCore = "com.amazonaws:aws-android-sdk-core:$version"
            const val awsAndroidSdkIot = "com.amazonaws:aws-android-sdk-iot:$version"
            const val awsAndroidSdkForApi21 = "com.amazonaws:aws-android-sdk-iot:$versionForApi21"
            const val awsAndroidSdkMobileClient = "com.amazonaws:aws-android-sdk-mobile-client:$version"
            const val awsAndroidSdkMobileClientForApi21 = "com.amazonaws:aws-android-sdk-mobile-client:$versionForApi21"
        }
        object AwsJava {
            private const val version = "1.11.845"
            const val awsJavaSdkIot = "com.amazonaws:aws-java-sdk-iot:$version"
        }
    }

    object ComAmitShekharAndroid {
        // https://mvnrepository.com/artifact/com.amitshekhar.android/debug-db
        // https://github.com/amitshekhariitbhu/Android-Debug-Database
        const val version = "1.0.6"
        const val debugDb = "com.amitshekhar.android:debug-db:$version"
        // Alternatively, there is JitPack-based group Debs.ComGithubAmitShekharIitbhu.

        // Warning!
        // This library uses com.android.support:support-compat:26.1.0, therefore it requires a jetifier being enabled.
        // If you include this in the project, either update the library or enable jetifier.
    }

    object ComAuth0Android {
        const val jwtdecode = "com.auth0.android:jwtdecode:2.0.1"
    }

    object ComChargeMap {
        // https://github.com/ChargeMap/Compose-NumberPicker
        const val composeNumberPicker = "com.chargemap.compose:numberpicker:1.0.3"
    }

    object ComCrashlyticsSdkAndroid {
        // Crashlytics V1 - libraries
        const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
        const val crashlyticsNdk = "com.crashlytics.sdk.android:crashlytics-ndk:2.1.1"
        // They are obsolete artifacts. Thet had been migrated to Deps.ComGoogle.Firebase.
    }

    object ComDiffPlugSpotless {
        // https://plugins.gradle.org/plugin/com.diffplug.spotless
        const val spotlessPluginGradle = "com.diffplug.spotless:spotless-plugin-gradle:5.17.0"
    }

    object ComDipien {
        // https://github.com/dipien/bye-bye-jetifier
        const val byeByeJetifier = "com.dipien:bye-bye-jetifier:1.2.2"
        const val byeByeJetifierName = "com.dipien.byebyejetifier"
    }

    object ComFacebook {

        object Android {
            const val facebookAndroidSdk = "com.facebook.android:facebook-android-sdk:6.0.0"
        }

        object Stetho {
            private const val version = "1.6.0"
            const val stetho = "com.facebook.stetho:stetho:$version"
            const val stethoOkHttp = "com.facebook.stetho:stetho-okhttp:$version"
            const val stethoOkHttp3 = "com.facebook.stetho:stetho-okhttp3:$version"
            const val stethoUrlConnection = "com.facebook.stetho:stetho-urlconnection:$version"
        }
    }

    object ComGetkeepsafeDexcount {
        const val dexcountGradlePlugin = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:3.1.0"
    }

    object ComGithubAmitShekharIitbhu {
        // https://github.com/amitshekhariitbhu/Android-Debug-Database
        // https://jitpack.io/#amitshekhariitbhu/Android-Debug-Database
        const val version = "1.0.6"
        const val debugDb = "com.github.amitshekhariitbhu.Android-Debug-Database:debug-db:$version"
        const val debugDbBase = "com.github.amitshekhariitbhu.Android-Debug-Database:debug-db-base:$version"
        const val debugDbEncrypt = "com.github.amitshekhariitbhu.Android-Debug-Database:debug-db-encrypt:$version"
        // If available better use depenedencies from Deps.ComAmitShekharAndroid group.

        // Warning!
        // This library uses com.android.support:support-compat:26.1.0, therefore it requires a jetifier being enabled.
        // If you include this in the project, either update the library or enable jetifier.
    }

    object ComGithubBenManes {
        // https://github.com/ben-manes/gradle-versions-plugin/releases
        const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.42.0"
        const val gradleVersionsPluginName = "com.github.ben-manes.versions"
    }

    object ComGithubBumpTech {
        private const val version = "4.13.2"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$version"
    }

    object ComGithubDevnied {
        const val emvNfcCardLibrary = "com.github.devnied.emvnfccard:library:3.0.1"
    }

    object ComGithubFabioCollini {
        // https://github.com/fabioCollini/DaggerMock
        // http://www.albertgao.xyz/2018/04/24/how-to-mock-dagger-android-injection-in-instrumented-tests-with-kotlin/
        // https://medium.com/@fabioCollini/android-testing-using-dagger-2-mockito-and-a-custom-junit-rule-c8487ed01b56
        const val daggerMock = "com.github.fabioCollini.daggermock:daggermock:0.8.5"
    }

    object ComGithub1Jajen1 { // #pretty
        // https://github.com/1Jajen1/kotlin-pretty
        const val kotlinPretty = "com.github.1Jajen1.kotlin-pretty:kotlin-pretty:0.6.0" // #pretty
    }

    object ComGithubMazenrashed {
        // https://github.com/mazenrashed/Printooth
        // requires: `maven { url 'https://jitpack.io' }`
        const val printooth = "com.github.mazenrashed:Printooth:1.2.1"
    }

    object ComGithubOngakuer {
        // https://github.com/ongakuer/CircleIndicator
        // https://jitpack.io/#ongakuer/CircleIndicator
        const val version = "2.1.6"
        const val circleIndicator = "com.github.ongakuer.CircleIndicator:circleindicator:$version"
        const val loopingViewPager = "com.github.ongakuer.CircleIndicator:LoopingViewPager:$version"
        // If available better use depenedencies from Deps.MeRelax group.
    }

    object ComGithubPhilJay {
        const val MPAndroidCharts = "com.github.PhilJay:MPAndroidChart:3.1.0"
    }

    object ComGithubRazir {
        const val progressButton = "com.github.razir.progressbutton:progressbutton:2.1.0"
    }

    object ComGithubSagarViradiya {
        object EazyPermissions { // #permissions
            // https://github.com/sagar-viradiya/eazypermissions
            // https://jitpack.io/#sagar-viradiya/eazypermissions
            private const val version = "2.0.3"
            const val common = "com.github.sagar-viradiya.eazypermissions:common:v$version"
            const val dslpermission = "com.github.sagar-viradiya.eazypermissions:dslpermission:v$version"
            const val coroutinespermission = "com.github.sagar-viradiya.eazypermissions:coroutinespermission:$version"
            const val livedatapermission = "com.github.sagar-viradiya.eazypermissions:livedatapermission:v$version"
            // if available better to use deps from Deps.ComSagar
        }
    }

    object ComGithubTakahirom {
        // simple_item - allows to add code snippets into Hyperion menu (requires: maven { url 'https://jitpack.io' })
        // https://github.com/takahirom/Hyperion-Simple-Item/tags
        private const val version = "0.5.0"
        const val hyperionSimpleItem = "com.github.takahirom:Hyperion-Simple-Item:$version"
    }

    object ComGithubTbruyelle {
        const val rxPermissions = "com.github.tbruyelle:rxpermissions:0.10.2"
    }

    object ComGithubTony19 {
        const val logbackAndroid = "com.github.tony19:logback-android:2.0.0"
    }

    object ComGithubTripletGradle {
        const val playPublisher = "com.github.triplet.gradle:play-publisher:2.8.0"
    }

    object ComGoogle {

        object Accompanist {
            // https://mvnrepository.com/artifact/com.google.accompanist
            // (strangely this com.google.accompanist is not available on maven.google.com)
            private const val version = "0.20.3"
            // When using a MDC theme w/ Compose (https://developer.android.com/jetpack/compose/interop/adding#reuse-view):
            const val accompanistAppcompatTheme = "com.google.accompanist:accompanist-appcompat-theme:$version"
        }

        object Android {

            // Google Play Services - libraries
            // INFO: https://developers.google.com/android/guides/releases
            object GMS {
                // https://maven.google.com/web/index.html#com.google.android.gms:play-services-base
                const val playServicesBase = "com.google.android.gms:play-services-base:17.6.0"
                const val playServicesAuthApiPhone = "com.google.android.gms:play-services-auth-api-phone:17.4.0"
                const val playServicesAdsIdentifier = "com.google.android.gms:play-services-ads-identifier:17.0.0"
                const val playServicesWallet = "com.google.android.gms:play-services-wallet:18.0.0"
                const val playServicesWalletObsolete = "com.google.android.gms:play-services-wallet:16.0.0"
                const val playServicesMaps = "com.google.android.gms:play-services-maps:17.0.0"
                const val playServicesLocation = "com.google.android.gms:play-services-location:17.0.0"
            }

            // CHANGELOG: https://github.com/material-components/material-components-android/releases
            // MVN:       https://maven.google.com/web/index.html#com.google.android.material
            object Material {
                const val material = "com.google.android.material:material:1.6.1"
                const val materialObsolete = "com.google.android.material:material:1.0.0"
                // When using AppCompat theme w/ Compose (https://developer.android.com/jetpack/compose/interop/adding#reuse-view):
                const val composeThemeAdapter = "com.google.android.material:compose-theme-adapter:${AndroidX.Compose.version}"
            }

            const val playCore = "com.google.android.play:core:1.6.5"
        }

        object Code {
            // https://github.com/google/gson/blob/master/CHANGELOG.md
            // https://github.com/google/gson/releases
            const val gson = "com.google.code.gson:gson:2.9.0"
        }

        object Dagger {
            // https://github.com/google/dagger/releases
            // https://repo1.maven.org/maven2/com/google/dagger/
            private const val version = "2.42"
            // WARNING:
            // There is an issue introduced in 2.40.4, that caused build failure.
            // The issue happen  to Jetpack Compose and Dagger.
            // More about the issue: https://github.com/google/dagger/issues/3090
            // An example build scan: https://scans.gradle.com/s/cndq2gclvgm4m/failure
            // Workaround: use 'api' for 'androidx.compose.runtime:runtime' from modules, or add it to the app module.
            const val dagger = "com.google.dagger:dagger:$version"
            const val daggerAndroid = "com.google.dagger:dagger-android:$version"
            const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$version"
            const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
            const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$version"
            const val hiltAndroid = "com.google.dagger:hilt-android:$version"
            const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:$version"
            const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
            const val hiltCore = "com.google.dagger:hilt-core:$version"
        }

        object Firebase {
            // Code search: https://cs.opensource.google/firebase-sdk/firebase-android-sdk
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-core
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-analytics
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-analytics-ktx
            const val verCore = "21.1.0"
            const val firebaseCore = "com.google.firebase:firebase-core:$verCore"
            const val firebaseAnalytics = "com.google.firebase:firebase-analytics:$verCore"
            const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx:$verCore"
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-auth
            const val firebaseAuth = "com.google.firebase:firebase-auth:21.0.5"
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-bom
            const val firebaseBom = "com.google.firebase:firebase-bom:30.1.0"
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-config
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-config-ktx
            const val verConfig = "21.1.1"
            const val firebaseConfig = "com.google.firebase:firebase-config:$verConfig"
            const val firebaseConfigKtx = "com.google.firebase:firebase-config-ktx:$verConfig"
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-firestore
            const val firebaseFirestore = "com.google.firebase:firebase-firestore:24.1.2"
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-messaging
            // https://maven.google.com/web/index.html#com.google.firebase:firebase-messaging-ktx
            const val firebaseMessaging = "com.google.firebase:firebase-messaging:23.0.6"
            const val firebaseMessagingKtx = "com.google.firebase:firebase-messaging-ktx:23.0.6"
            // Crashlytics
            const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"
            const val firebaseCrashlyticsNdk = "com.google.firebase:firebase-crashlytics-ndk:17.2.1"
            // Crashlytics Gradle Plugin
            const val firebaseCrashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.3.0"
        }

        object Guava {
            // Versions: https://github.com/google/guava/releases
            // Code search: https://cs.opensource.google/guava/guava
            // Maven: https://search.maven.org/search?q=g:com.google.guava
            const val version = "31.1"
            const val guavaJre =     "com.google.guava:guava:$version-jre"
            const val guavaAndroid = "com.google.guava:guava:$version-android"
            const val guavaTestlibJre =     "com.google.guava:guava-testlib:$version-jre"
            const val guavaTestlibAndroid = "com.google.guava:guava-testlib:$version-android"
        }

        object GMS {
            // Google Play Services - gradle plugin
            // INFO: https://developers.google.com/android/guides/google-services-plugin
            // MVN:  https://maven.google.com/web/index.html#com.google.gms:google-services
            const val googleServices = "com.google.gms:google-services:4.3.13"
        }

        // Versions: https://github.com/google/truth/releases
        // Code search: https://cs.opensource.google/truth/truth
        const val truth = "com.google.truth:truth:1.1.3"

        const val zxingCore = "com.google.zxing:core:3.5.0" // 3.3.2 is the last working with zxing-android-embedded:3.6.0
    }

    object ComGoogleCode {
        // https://mvnrepository.com/artifact/com.googlecode.libphonenumber/libphonenumber
        const val libPhoneNumber = "com.googlecode.libphonenumber:libphonenumber:8.12.51"
    }

    object ComGuAndroid {
        const val toolargetool = "com.gu.android:toolargetool:0.3.0"
    }

    object ComIntuit {
        // https://github.com/intuit/sdp
        const val sdpAndroid = "com.intuit.sdp:sdp-android:1.1.0"
    }

    object ComJakeWharton {

        object ButterKnife {
            private const val version = "10.2.1"
            const val butterknife = "com.jakewharton:butterknife:$version"
            const val butterknifeCompiler = "com.jakewharton:butterknife-compiler:$version"
        }

        // https://github.com/JakeWharton/timber/releases
        const val timber = "com.jakewharton.timber:timber:5.0.1"

        const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:1.4.0"
    }

    object ComJaredrummler {
        const val androidShell = "com.jaredrummler:android-shell:1.0.0"
    }

    object ComJourneyapps {
        // zxing-android-embedded
        // In my case, I don't really need it. I used it for Bitmap creation only.
        const val zxingAndroidEmbedded = "com.journeyapps:zxing-android-embedded:3.6.0" // latest: 4.0.2 (but minSdk required is 24)
    }

    object ComLouisCAD {
        // https://github.com/LouisCAD/Splitties/releases/tag/v3.0.0
        // https://repo1.maven.org/maven2/com/louiscad/splitties/
        const val version = "3.0.0"
        const val splittiesCoroutines = "com.louiscad.splitties:splitties-coroutines:$version"
        const val splittiesToast = "com.louiscad.splitties:splitties-toast:$version"
    }

    object CommonsIo {
        // https://mvnrepository.com/artifact/commons-io/commons-io
        const val commonsIo = "commons-io:commons-io:2.6"
    }

    object ComSagar {
        // https://github.com/sagar-viradiya/eazypermissions
        // https://mvnrepository.com/artifact/com.sagar/coroutinespermission
        private const val version = "2.0.3"
        const val coroutinespermission = "com.sagar:coroutinespermission:$version"
        const val livedatapermission = "com.sagar:livedatapermission:$version"
        const val dslpermission = "com.sagar:dslpermission:$version"
        // alternatively use JitPack version at Deps.ComGithubSagarViradiya
    }

    object ComSquereup {

        object Inject {
            private const val version = "0.8.1"
            const val assistedInjectAnnotations = "com.squareup.inject:assisted-inject-annotations:$version"
            const val assistedInjectAnnotationsDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
            const val assistedInjectProcessor = "com.squareup.inject:assisted-inject-processor:$version"
            const val assistedInjectProcessorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
            const val inflationInject = "com.squareup.inject:inflation-inject:$version"
            const val inflationInjectProcessor = "com.squareup.inject:inflation-inject-processor:$version"
        }

        const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"

        object Moshi {
            // https://mvnrepository.com/artifact/com.squareup.moshi/moshi
            // https://search.maven.org/search?q=g:com.squareup.moshi
            private const val version = "1.13.0"
            const val moshi = "com.squareup.moshi:moshi:$version"
            const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$version"
            const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
            const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
        }

        object OkHttp3 {
            // https://github.com/square/okhttp/blob/master/CHANGELOG.md
            // https://square.github.io/okhttp/changelogs/changelog_4x/
            // https://search.maven.org/search?q=g:com.squareup.okhttp3
            // https://search.maven.org/artifact/com.squareup.okhttp3/okhttp
            private const val version = "4.10.0"
            const val okhttp = "com.squareup.okhttp3:okhttp:$version"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
            const val mockwebserver = "com.squareup.okhttp3:mockwebserver:$version"
        }

        object Retrofit {
            // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
            // https://search.maven.org/search?q=g:com.squareup.retrofit2
            private const val version = "2.9.0"
            private const val versionConverters = "2.8.1"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val retrofitConverters = "com.squareup.retrofit2:retrofit-converters:$versionConverters"
            const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:$version"
            const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
            const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
            const val converterScalars = "com.squareup.retrofit2:converter-scalars:$version"
        }
    }

    object ComStripe {
        // TODO: update!
        const val stripeTerminal = "com.stripe:stripeterminal:2.2.0"
        const val stripeAndroid = "com.stripe:stripe-android:13.2.0"
        const val stripeAndroidObsolete = "com.stripe:stripe-android:10.4.2"
    }

    object ComSunmi {
        /* https://mvnrepository.com/artifact/com.sunmi/printerlibrary
        Latest: 1.0.15
        Cannot be updated due to issue: https://githubmemory.com/repo/shangmisunmi/SunmiPrinterDemo/issues/60
        This had been previously resolved by applying the right Proguard rules, but after implementing Jetpack Compose
        it began crashing again, as reported by another user. The issue had been reported.
        todo: Consider decompiling the printerlibrary and modifying it to use reflection to get SystemProperties,
         like Compose does it, here:
          https://android.googlesource.com/platform/frameworks/support/+/f9ec7ad7786a020bb4cd02354c6fcc9efa9a08e0/compose/ui/ui/src/androidMain/kotlin/androidx/compose/ui/platform/AndroidComposeView.android.kt#1624
        */
        const val printerLibrary = "com.sunmi:printerlibrary:1.0.14"

        const val sunmiUi = "com.sunmi:sunmiui:1.1.27"
        const val deviceManagerLibrary = "com.sunmi:DeviceManagerLibrary:1.0.17"
        const val sunmiSdk = "com.sunmi:SunmiSDK:1.0.11"
        const val commonUtils = "com.sunmi:common-utils:1.0.5"
    }

    object ComTbuonomoAndrui {
        const val viewpagerDotsIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
        const val viewpagerDotsIndicatorObsolete = "com.tbuonomo.andrui:viewpagerdotsindicator:3.0.3"
    }

    object ComTwoBuffers {

        object Wire {
            // https://search.maven.org/search?q=g:com.twobuffers.wire
            // https://s01.oss.sonatype.org/content/groups/public/com/twobuffers/wire/ (RELEASES)
            // https://s01.oss.sonatype.org/content/groups/staging/com/twobuffers/wire/ (SNAPSHOTS)
            private const val version = "0.4.10"
            const val wireCoroutines = "com.twobuffers.wire:wire-coroutines:$version"
            const val wireCoroutinesAndroid = "com.twobuffers.wire:wire-coroutines-android:$version"
            const val wireDiAnnotationsCommon = "com.twobuffers.wire:wire-di-annotations-common:$version"
            const val wireDiAnnotations = "com.twobuffers.wire:wire-di-annotations:$version"
            const val wireInitializer = "com.twobuffers.wire:wire-initializer:$version"
            const val wireFirebaseConfig = "com.twobuffers.wire:wire-firebase-config:$version"
            const val wireUtils = "com.twobuffers.wire:wire-utils:$version"
            const val wireUtilsAndroid = "com.twobuffers.wire:wire-utils-android:$version"
            const val wireThreeTenABP = "com.twobuffers.wire:wire-threetenabp:$version"
            const val wireMoshi = "com.twobuffers.wire:wire-moshi:$version"
            const val wireRetrofit = "com.twobuffers.wire:wire-retrofit:$version"
            const val wireRetrofitAndroid = "com.twobuffers.wire:wire-retrofit-android:$version"
            const val wireRxjava = "com.twobuffers.wire:wire-rxjava:$version"
            const val wireRxjavaAndroid = "com.twobuffers.wire:wire-rxjava-android:$version"
        }

        object Suricate {
            // https://search.maven.org/search?q=g:com.twobuffers.suricate
            // https://s01.oss.sonatype.org/content/groups/public/com/twobuffers/suricate/ (RELEASES)
            // https://s01.oss.sonatype.org/content/groups/staging/com/twobuffers/suricate/ (SNAPSHOTS)
            private const val version = "0.1.0"
            const val suricate = "com.twobuffers.suricate:suricate:$version"
        }
    }

    object ComTylerThrailkillHelpers {
        // https://github.com/snowe2010/pretty-print
        // https://github.com/snowe2010/pretty-print/releases/tag/v2.0.8
        // https://mvnrepository.com/artifact/com.tylerthrailkill.helpers/pretty-print
        const val prettyPrint = "com.tylerthrailkill.helpers:pretty-print:v2.0.8" // #pretty
    }

    object ComVanniktech {
        // https://github.com/vanniktech/gradle-maven-publish-plugin/blob/master/CHANGELOG.md
        const val gradleMavenPublishPlugin = "com.vanniktech:gradle-maven-publish-plugin:0.20.0"

        // rxpermission uses rxpermissions, but provides a bit simplified API
        const val rxPermission = "com.vanniktech:rxpermission:0.7.0"
    }

    object ComWillowTreeApps {
        object Hyperion {
            // https://mvnrepository.com/artifact/com.willowtreeapps.hyperion
            private const val version = "0.9.34"
            const val hyperionCore = "com.willowtreeapps.hyperion:hyperion-core:$version"
            const val hyperionAttr = "com.willowtreeapps.hyperion:hyperion-attr:$version"
            const val hyperionBuildConfig = "com.willowtreeapps.hyperion:hyperion-build-config:$version"
            const val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:$version"
            const val hyperionDisk = "com.willowtreeapps.hyperion:hyperion-disk:$version"
            const val hyperionGeigerCounter = "com.willowtreeapps.hyperion:hyperion-geiger-counter:$version"
            const val hyperionMeasurement = "com.willowtreeapps.hyperion:hyperion-measurement:$version"
            const val hyperionPhoenix = "com.willowtreeapps.hyperion:hyperion-phoenix:$version"
            const val hyperionRecorder = "com.willowtreeapps.hyperion:hyperion-recorder:$version"
            const val hyperionSharedPreferences = "com.willowtreeapps.hyperion:hyperion-shared-preferences:$version"
            const val hyperionTimber = "com.willowtreeapps.hyperion:hyperion-timber:$version"
        }
    }

    object IoArrowKt {
        // https://mvnrepository.com/artifact/io.arrow-kt/arrow-core
        private const val version = "1.1.2"
        const val arrowCore = "io.arrow-kt:arrow-core:$version"
    }

    object IoCodearteGradle {
        // Plugin for publishing artifacts: https://github.com/Codearte/gradle-nexus-staging-plugin
        const val gradleNexusStagingPlugin = "io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.21.2"
    }

    object IoCoilKt {
        private const val version = "0.11.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object IoFabric {
        // Crashlytics V1 - gradle plugin
        const val gradle = "io.fabric.tools:gradle:1.31.2"
        // It is obsolete artifact. It had been migrated to Deps.ComGoogle.Firebase.
    }

    object IoGithubChaosleung {
        // https://github.com/ChaosLeung/PinView
        // https://jitpack.io/#ChaosLeung/PinView
        // https://search.maven.org/search?q=g:io.github.chaosleung%20AND%20a:pinview
        const val pinView = "io.github.chaosleung:pinview:1.4.4"
    }

    object IoGithubMatij {
        const val wifiP2pLibrary = "io.github.Matij:WiFiP2PLibrary:1.0.22"
    }

    object IoIntercomAndroid {
        const val intercomSdk = "io.intercom.android:intercom-sdk:10.3.0"
    }

    object IoKotest {
        // https://github.com/kotest/kotest/releases
        // https://kotest.io/docs/changelog.html
        // https://mvnrepository.com/artifact/io.kotest
        const val version = "5.3.2"
        const val kotestCommon = "io.kotest:kotest-common:$version"
        const val kotestAssertionsCore = "io.kotest:kotest-assertions-core:$version"
        const val kotestFrameworkApi = "io.kotest:kotest-framework-api:$version"
        const val kotestProperty = "io.kotest:kotest-property:$version"
        const val kotestRunnerJunit5Jvm = "io.kotest:kotest-runner-junit5-jvm:$version"
    }

    object IoMockk {
        // https://mvnrepository.com/artifact/io.mockk/mockk
        const val mockk = "io.mockk:mockk:1.12.4"
    }

    object IoReactivex {
        // https://mvnrepository.com/artifact/io.reactivex.rxjava2
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.21"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    }

    object IoSentry {
        // https://github.com/getsentry/sentry-android-gradle-plugin/releases
        const val sentryAndroidGradlePlugin = "io.sentry:sentry-android-gradle-plugin:3.1.2"
        // CHANGELOG: https://github.com/getsentry/sentry-java/blob/main/CHANGELOG.md
        //            https://github.com/getsentry/sentry-java/releases/
        // MAVEN:     https://mvnrepository.com/artifact/io.sentry/sentry-android
        const val version = "6.2.1"
        const val sentryAndroid = "io.sentry:sentry-android:$version"
        // how to integrate: https://docs.sentry.io/platforms/android/configuration/integrations/fragment/
        const val sentryAndroidFragment = "io.sentry:sentry-android-fragment:$version"
        // how to integrate: https://docs.sentry.io/platforms/android/configuration/integrations/navigation/
        const val sentryAndroidNavigation = "io.sentry:sentry-android-navigation:$version"
        // how to integrate: https://docs.sentry.io/platforms/android/configuration/integrations/okhttp/
        const val sentryAndroidOkhttp = "io.sentry:sentry-android-okhttp:$version"
        // how to integrate: https://docs.sentry.io/platforms/android/configuration/integrations/timber/
        const val sentryAndroidTimber = "io.sentry:sentry-android-timber:$version"
        // how to integrate: https://docs.sentry.io/platforms/android/configuration/integrations/jetpack-compose/
        const val sentryCompose = "io.sentry:sentry-compose:$version"
    }

    object JavaxAnnotation {
        const val jsr250Api = "javax.annotation:jsr250-api:1.0" // for Dagger
        const val javaxAnnotationApi = "javax.annotation:javax.annotation-api:1.3.2"
    }

    object JUnit {
        // https://search.maven.org/artifact/junit/junit
        const val junit4 = "junit:junit:4.13.2"
    }

    object MeChampeau { // #benchmarking
        const val jmhGradlePlugin = "me.champeau.jmh:jmh-gradle-plugin:0.6.6"
    }

    object MeRelax {
        // https://github.com/ongakuer/CircleIndicator
        // https://search.maven.org/artifact/me.relex/circleindicator
        const val version = "2.1.6"
        const val circleIndicator = "me.relex:circleindicator:$version"
        // Alternatively, there is JitPack-based group Debs.ComGithubOngakuer.
    }

    object MeTatarka {
        // https://github.com/evant/binding-collection-adapter/tags
        const val bindingCollectionAdapter = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:4.0.0"
        const val bindingCollectionAdapterRecyclerView = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:4.0.0"
        const val bindingCollectionAdapterViewPager2 = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-viewpager2:4.0.0"
    }

    object OrgAmshoveKluent {
        // https://markusamshove.github.io/Kluent/
        const val kluent = "org.amshove.kluent:kluent:1.68"
    }

    object OrgAssertJ {
        const val assertjCore = "org.assertj:assertj-core:3.23.1"
    }

    object OrgApache {
        object Commons {
            // On Largestation, we could not use the version 3.10 due to lambda use
            // in src/main/java/org/apache/commons/lang3/Validate.java:
            // https://github.com/apache/commons-lang/commit/374426397
            const val commonsLang3 = "org.apache.commons:commons-lang3:3.12.0"
            const val commonsLang3Old = "org.apache.commons:commons-lang3:3.9"
            const val commonsCollections4 = "org.apache.commons:commons-collections4:4.4"
        }
    }

    object OrgBouncycastle {
        const val bcprovJdk15on = "org.bouncycastle:bcprov-jdk15on:1.66"
    }

    object OrgHamcrest {
        const val hamcrestIntegration = "org.hamcrest:hamcrest-integration:1.3"
    }

    object OrgJetBrains {

        object Anko {
            private const val version = "0.10.8"
            const val ankoCommon = "org.jetbrains.anko:anko-common:$version"
            const val ankoCoroutines = "org.jetbrains.anko:anko-coroutines:$version"
        }

        object Kotlin {
            // CHANGELOG: https://kotlinlang.org/docs/releases.html#release-details
            //            https://github.com/JetBrains/kotlin/releases
            const val version = "1.7.0" // latest: 1.7.10, compose supports 1.7.0 the latest
            const val group = "org.jetbrains.kotlin"
            const val artifactKotlinStdlib = "kotlin-stdlib"
            const val artifactKotlinStdlib7 = "kotlin-stdlib-jdk7"
            const val artifactKotlinStdlib8 = "kotlin-stdlib-jdk8"
            const val kotlinStdlib = "$group:$artifactKotlinStdlib:$version"
            const val kotlinStdlib7 = "$group:$artifactKotlinStdlib7:$version"
            const val kotlinStdlib8 = "$group:$artifactKotlinStdlib8:$version"
            const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
            const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
            const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        }

        object KotlinX {
            object Benchmark { // #benchmarking
                const val version = "0.4.4"
                const val pluginClasspath = "org.jetbrains.kotlinx.benchmark:org.jetbrains.kotlinx.benchmark.gradle.plugin:$version"
                const val pluginName = "org.jetbrains.kotlinx.benchmark"
                const val kotlinxBenchmarkRuntime = "org.jetbrains.kotlinx:kotlinx-benchmark-runtime:$version"
                const val kotlinxBenchmarkRuntimeJvm = "org.jetbrains.kotlinx:kotlinx-benchmark-runtime-jvm:$version"
                // Example setup: https://github.com/Kotlin/kotlinx-benchmark/blob/7e4cf49/examples/kotlin/build.gradle
            }
            object Collections {
                // List of libraries providing immutable collections: https://www.jishuwen.com/d/28FX
                const val kotlinxCollectionsImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.2"
                const val kotlinxCollectionsImmutableJvm = "org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.2"
            }
            object Coroutines {
                // CHANGELOG: https://github.com/Kotlin/kotlinx.coroutines/blob/master/CHANGES.md
                // MAVEN:     https://mvnrepository.com/artifact/org.jetbrains.kotlinx/
                //            https://search.maven.org/search?q=g:org.jetbrains.kotlinx%20AND%20a:kotlinx-coroutines*
                private const val version = "1.6.4"
                const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
                const val kotlinxCoroutinesRx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
                const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
                const val kotlinxCoroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
                const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
                const val kotlinxCoroutinesDebug = "org.jetbrains.kotlinx:kotlinx-coroutines-debug:$version"
            }
        }
    }

    object OrgMockito {
        const val mockitoCore = "org.mockito:mockito-core:3.3.0"
        const val mockitoAndroid = "org.mockito:mockito-android:3.3.2"
    }

    object OrgOpenJdk {
        object Jmh { // #benchmarking
            // https://mvnrepository.com/artifact/org.openjdk.jmh/jmh-core
            // Examples: https://www.baeldung.com/java-microbenchmark-harness
            const val version = "1.35"
            const val jmhCore = "org.openjdk.jmh:jmh-core:$version"
            const val jmhGeneratorAnnprocess = "org.openjdk.jmh:jmh-generator-annprocess:$version"
        }
    }

    object OrgOw2 {
        // https://mvnrepository.com/artifact/org.ow2.asm
        const val version = "9.3"
        const val asm = "org.ow2.asm:asm:$version"
        const val asmCommons = "org.ow2.asm:asm-commons:$version"
        const val asmUtil = "org.ow2.asm:asm-util:$version"
    }

    object OrgRobolectric {
        const val robolectric = "org.robolectric:robolectric:4.3.1" // latest: 4.4-alpha-1
    }

    object OrgSlf4j {
        const val slf4jApi = "org.slf4j:slf4j-api:1.7.30" // latest: 2.0.0-alpha1
    }

    object OrgThreeTen {
        // https://search.maven.org/search?q=g:org.threeten%20AND%20a:threetenbp&core=gav
        const val threeTenBp = "org.threeten:threetenbp:1.6.0"
        const val threeTenBpNoTzdb = "$threeTenBp:no-tzdb"
    }

    object OrgOrganicDesign {
        // https://mvnrepository.com/artifact/org.organicdesign/Paguro
        // https://search.maven.org/artifact/org.organicdesign/Paguro
        const val paguro = "org.organicdesign:Paguro:3.10.3"
    }

    object PubDevrel {
        // https://github.com/googlesamples/easypermissions
        const val easypermissions = "pub.devrel:easypermissions:3.0.0" // #permissions
    }
}
