import buildcfg.Deps

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
    api(Deps.OrgJetBrains.Kotlin.kotlinStdlib)
    api(Deps.ComGoogle.Dagger.dagger)
    kapt(Deps.ComGoogle.Dagger.daggerCompiler)
    api(Deps.ComSquereup.Retrofit.retrofit) { exclude(group = Deps.OrgJetBrains.Kotlin.group, module = Deps.OrgJetBrains.Kotlin.artifactKotlinStdlib7) }
    api(Deps.ComSquereup.Retrofit.converterMoshi)

    api(Deps.ComSquereup.OkHttp3.okhttp)
    api(Deps.ComSquereup.OkHttp3.loggingInterceptor)

    api(Deps.ComGoogle.Guava.guavaAndroid)
}
