plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions")
}

android {
    namespace = "com.example.medidorapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.medidorapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")

    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("androidx.compose.ui:ui:1.8.0-alpha05")
    implementation("androidx.compose.ui:ui-graphics:1.8.0-alpha05")
    implementation("androidx.compose.ui:ui-tooling-preview:1.8.0-alpha05")
    implementation("androidx.compose.material3:material3:1.4.0-alpha03")
    implementation("androidx.compose.runtime:runtime-livedata:1.8.0-alpha05")

    implementation("androidx.compose.material3:material3:<latest_stable_version>")

    implementation("androidx.room:room-runtime:2.7.0-alpha11")
    kapt("androidx.room:room-compiler:2.7.0-alpha11")
    implementation("androidx.room:room-ktx:2.7.0-alpha11")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.0-alpha06")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.0-alpha06")

    implementation("androidx.navigation:navigation-fragment-ktx:2.9.0-alpha02")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.0-alpha02")
    implementation("androidx.navigation:navigation-compose:2.9.0-alpha02")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.8.0-alpha05")

    debugImplementation("androidx.compose.ui:ui-tooling:1.8.0-alpha05")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.8.0-alpha05")
}

