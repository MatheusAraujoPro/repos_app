plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

apply(from = "${rootProject.projectDir}/android-common.gradle")

android {
    namespace = "com.example.feature"
    compileSdk = 34

    buildFeatures{
        compose = true
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":core:data_local"))
    implementation(project(":core:data_remote"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.datastore.preferences)
    implementation(libs.retrofit)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.paging.compose)
    implementation(libs.android.room)
    implementation(libs.android.roomKtx)
    kapt(libs.android.room.compiler)
    implementation(libs.lottie)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test)
    androidTestImplementation(libs.androidx.expresso)
}
