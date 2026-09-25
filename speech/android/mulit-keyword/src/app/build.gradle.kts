plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

// Keyword engine selection: FROM_CONFIG (default, one model with N wake words) or
// MULTI_TABLE (fallback, N KeywordRecognizers each with its own .table file).
// Override with: ./gradlew assembleDebug -PkwsEngine=MULTI_TABLE
val kwsEngine = (findProperty("kwsEngine") as String?)?.uppercase() ?: "FROM_CONFIG"
require(kwsEngine in setOf("FROM_CONFIG", "MULTI_TABLE")) { "kwsEngine must be FROM_CONFIG or MULTI_TABLE" }

android {
    namespace = "com.hoffe86.speechmultikeyword"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.hoffe86.speechmultikeyword"
        minSdk = 35
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // AAOS is 64-bit only; Azure Embedded Speech is validated here on arm64.
        ndk { abiFilters += "arm64-v8a" }

        buildConfigField("String", "KWS_ENGINE", "\"$kwsEngine\"")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.azure.speech.embedded)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
