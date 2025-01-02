plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //Vid 301
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    //Vid 308
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.pjff.geminibot"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pjff.geminibot"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        //Vid 301
        buildConfig = true
    }
}

dependencies {
    //Vid 300
    implementation(libs.generativeai)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Vid 308
    //ksp("androidx.room:room-compiler:2.6.1")
    ksp(libs.androidx.room.compiler)
    implementation("androidx.compose.material:material-icons-core:1.7.4")
    implementation("androidx.compose.material:material-icons-extended:1.7.4")
    implementation("io.coil-kt:coil-compose:2.3.0")



}