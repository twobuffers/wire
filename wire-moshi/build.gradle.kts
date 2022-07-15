import buildcfg.Deps

plugins {
    id(GradlePluginId.KOTLIN_JVM)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.MAVEN_PUBLISH)
}

dependencies {
    implementation(project(":wire-utils"))
    api(Deps.OrgJetBrains.Kotlin.kotlinStdlib)
    api(Deps.ComGoogle.Dagger.dagger)
    kapt(Deps.ComGoogle.Dagger.daggerCompiler)
    api(Deps.ComSquereup.Moshi.moshi)
}
