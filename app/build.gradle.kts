plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")

    // No org.jetbrains.kotlin.android and no org.jetbrains.kotlin.plugin.compose:
    // AGP 9's built-in Kotlin support compiles the Kotlin sources itself and
    // supplies a Compose compiler that's already version-matched to it, since
    // buildFeatures.compose is enabled below. Applying either standalone
    // plugin on top of that fails with "Cannot add extension with name
    // 'kotlin'" — see README.md.
}

android {
    namespace = "com.ezorder.app"
    // AGP 9.0's max supported API level is 36.1 — see root build.gradle.kts
    // for why AGP is pinned to 9.0.0. Raise this alongside AGP if you
    // later update Android Studio.
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ezorder.app"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0-phase0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // No kotlinOptions{} block: with built-in Kotlin, jvmTarget defaults to
    // compileOptions.targetCompatibility (VERSION_17, set above) automatically.

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2026.05.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")
    implementation("androidx.activity:activity-compose:1.10.0")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-core")

    implementation("androidx.navigation:navigation-compose:2.9.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}