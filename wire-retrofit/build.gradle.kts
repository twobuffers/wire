import buildcfg.Libs

plugins {
    id(GradlePluginId.KOTLIN_JVM)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.MAVEN_PUBLISH)
}

java {
    sourceCompatibility = buildcfg.Versions.JAVA_VERSION
    targetCompatibility = buildcfg.Versions.JAVA_VERSION
}

dependencies {
    api(project(":wire-di-annotations-common"))
    api(Libs.Kotlin.kotlinStdlib)
    api(Libs.Dagger.dagger)
    kapt(Libs.Dagger.daggerCompiler)
    api(Libs.Retrofit.retrofit) { exclude(group = Libs.Kotlin.group, module = Libs.Kotlin.artifactKotlinStdlib7) }
    api(Libs.Retrofit.converterMoshi)

    api(Libs.OkHttp.okhttp)
    api(Libs.OkHttp.loggingInterceptor)

    api(Libs.Guava.guavaAndroid)
}
