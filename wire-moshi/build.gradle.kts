import buildcfg.Libs

plugins {
    id(GradlePluginId.KOTLIN_JVM)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.MAVEN_PUBLISH)
}

dependencies {
    api(Libs.Kotlin.kotlinStdlib)
    api(Libs.Dagger.dagger)
    kapt(Libs.Dagger.daggerCompiler)
    api(Libs.Moshi.moshi)
}
