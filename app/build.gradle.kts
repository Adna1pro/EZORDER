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
    // BOM predates Compose 1.12's bump to a required compileSdk 37 (that
    // landed in the 2026.08.00 BOM) — this version works with the
    // compileSdk 36 cap above, which is what AGP 9.0.0 supports.
    val composeBom = platform("androidx.compose:compose-bom:2026.05.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Core Android + Kotlin
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Lifecycle / ViewModel / StateFlow (architecture rule: UI -> ViewModel -> Repository)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")
    implementation("androidx.activity:activity-compose:1.10.0")

    // Compose UI + Material 3
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Navigation Compose — dependency added now per the tech-stack list in
    // the spec; the actual nav graph is not wired up until customer/restaurant
    // screens exist in later phases.
    implementation("androidx.navigation:navigation-compose:2.9.0")

    // Debug-only tooling
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}
