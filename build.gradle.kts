

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:${com.wadud.buildsrc.Config.Versions.gradle_version}")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${com.wadud.buildsrc.Config.Versions.kotlin}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files

        classpath ("com.google.gms:google-services:${com.wadud.buildsrc.Config.Versions.google_Service_version}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}


