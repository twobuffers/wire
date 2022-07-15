import buildcfg.Deps

plugins {
    id(GradlePluginId.KOTLIN_JVM)
    id(GradlePluginId.MAVEN_PUBLISH)
}

java {
    sourceCompatibility = buildcfg.Versions.JAVA_VERSION
    targetCompatibility = buildcfg.Versions.JAVA_VERSION
}

dependencies {
    api(Deps.OrgJetBrains.Kotlin.kotlinStdlib)
    api(Deps.IoReactivex.rxJava)
}
