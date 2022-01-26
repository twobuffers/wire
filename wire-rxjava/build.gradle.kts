import buildcfg.Libs

plugins {
    id(GradlePluginId.KOTLIN_JVM)
    id(GradlePluginId.MAVEN_PUBLISH)
}

java {
    sourceCompatibility = buildcfg.Versions.JAVA_VERSION
    targetCompatibility = buildcfg.Versions.JAVA_VERSION
}

dependencies {
    api(Libs.Kotlin.kotlinStdlib)
    api(Libs.RxJava.rxjava)
}
