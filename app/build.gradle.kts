plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)

    /* Ksp */
    id("com.google.devtools.ksp")

    /* Kapt */
    kotlin("kapt")

    /* Dagger Hilt */
    id("com.google.dagger.hilt.android")

    /* Firebase */
    id("com.google.gms.google-services")
}

android {
    namespace = "com.github.adriianh.countryguesser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.adriianh.countryguesser"
        minSdk = 24
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
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    /* Android & Compose*/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.runtime.livedata)
    implementation(libs.androidx.runtime.rxjava2)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /* Dagger Hilt */
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    /* Retrofit */
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)

    /* Room */
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    annotationProcessor(libs.androidx.room.compiler.v252)

    /* Arrow */
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)

    /* Firebase */
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    /* Images (Coil, Haze & Shimmer) */
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.compose.shimmer)
    implementation(libs.haze)
    implementation(libs.haze.materials)
    implementation(libs.glassmorphic.composables)
    implementation(libs.core.ktx)

    /* Icons */
    implementation(libs.feather)
}

kapt {
    correctErrorTypes = true
}