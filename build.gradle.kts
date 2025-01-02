// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    //id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
}
//Vid 301
buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}