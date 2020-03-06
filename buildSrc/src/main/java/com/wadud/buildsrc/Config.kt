package com.wadud.buildsrc

import com.wadud.buildsrc.Config.Versions.dagger_version
import com.wadud.buildsrc.Config.Versions.firebase_version
import com.wadud.buildsrc.Config.Versions.nav_version

class Config {
    object Apps {
        const val compileSdk = 29
        const val minSdk = 21
        const val targetSdk = 29
        const val versionCode = 1
        const val versionName = "1.0.0"
    }

    object Versions {
        const val kotlin = "1.3.61"
        const val appcompat = "1.0.2"
        const val dagger_version = "2.26"
        const val nav_version = "2.3.0-alpha01"
        const val constraint_layout_version = "1.1.3"
        const val google_Service_version = "4.3.3"
        const val gradle_version = "4.0.0-beta01"
        const val firebase_version = "17.2.3"
        const val cardView_version = "1.0.0"
        const val lifecycle_version = "2.2.0"
        const val legacySupport_version = "1.0.0"


        /* test */
        const val junit = "4.12"
    }

    object Libs {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val nav_ui = "androidx.navigation:navigation-ui-ktx:$nav_version"
        const val nav_fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
        const val constraint_layout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"

        const val legacy_support =
            "androidx.legacy:legacy-support-v4:${Versions.legacySupport_version}"
        const val lifecycle_extension =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
        const val lifecycle_viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
        const val firebase_analytics = "com.google.firebase:firebase-analytics:${firebase_version}"
        const val cardView = "androidx.cardview:cardview:${Versions.cardView_version}"
    }

    object Misc {
        const val dagger = "com.google.dagger:dagger:$dagger_version"
        const val dagger_compiler = "com.google.dagger:dagger-compiler:$dagger_version"
    }

    object TestLibs {
        const val junit = "junit:junit:${Versions.junit}"
    }
}