import com.wadud.buildsrc.Config


plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.Apps.compileSdk)
    defaultConfig {
        minSdkVersion(Config.Apps.minSdk)
        targetSdkVersion(Config.Apps.targetSdk)
        versionCode = versionCode
        versionName = versionName
        multiDexEnabled = true
        setProperty("archivesBaseName", "$applicationId-v$versionName($versionCode)")
        resConfigs("en")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    bundle {
        language { enableSplit = true }
        density { enableSplit = true }
    }
    lintOptions {
        isAbortOnError = false
        isIgnoreWarnings = true
        isQuiet = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.appcompat)
    implementation(Config.Libs.nav_fragment)
    implementation(Config.Libs.nav_ui)
    implementation(Config.Misc.dagger)
    implementation(Config.Libs.constraint_layout)
    implementation(Config.Libs.legacy_support)
    implementation(Config.Libs.lifecycle_extension)
    implementation(Config.Libs.lifecycle_viewModel)
    implementation(Config.Libs.firebase_analytics)
    implementation(Config.Libs.cardView)
    kapt(Config.Misc.dagger_compiler)
    testImplementation(Config.TestLibs.junit)

//splash
//    implementation 'com.rbddevs.splashy:splashy:1.2.0'

}
