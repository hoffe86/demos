import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

val modelProperties = Properties().apply {
    val config = file("model.properties")
    if (config.exists()) {
        config.reader(Charsets.UTF_8).use { load(it) }
    }
}

fun modelResource(name: String): String = modelProperties.getProperty(name, "")
    .replace("&", "&amp;")
    .replace("<", "&lt;")
    .replace(">", "&gt;")
    .replace("'", "\\'")
    .replace("\"", "\\\"")

android {
    namespace = "com.customvoice.tts"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        resValue("string", "voice", modelResource("voice"))
        resValue("string", "license", modelResource("license"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(libs.client.sdk.embedded)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.client.sdk.embedded)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}