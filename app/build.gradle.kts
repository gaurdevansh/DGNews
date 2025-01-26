import com.android.utils.TraceUtils.simpleId
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

val apiKeyProperties = Properties()
val apiKeyFile = rootProject.file("apikey.properties")
if (apiKeyFile.exists()) {
    apiKeyProperties.load(apiKeyFile.inputStream())
}

android {
    namespace = "com.example.dgnews"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dgnews"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /*buildConfigField(
            "String",
            "NEWS_API_KEY",
            "\"${apiKeyProperties["API_KEY"]}\""
        )*/
    }
    buildFeatures {
        buildConfig = true
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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.livedata)
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.room)
    implementation(libs.androidx.retrofit)
    implementation(libs.androidx.gson)
    implementation(libs.androidx.glide)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.datastore)
    kapt(libs.androidx.glide.compiler)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigation.ui)

}