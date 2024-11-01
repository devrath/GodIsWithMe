// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.google.services) apply false
}

extra["versionCode"] = 1
extra["versionName"] = "1.0"

extra["compileSdkVersion"] = 35
extra["minSdkVersion"] = 24
extra["targetSdkVersion"] = 34