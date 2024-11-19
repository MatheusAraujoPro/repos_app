import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

apply(from = "${rootProject.projectDir}/android-common.gradle")

android {
    namespace = "com.example.core.data_local"
}
dependencies {
    implementation(project(":core:domain"))

    implementation(libs.android.room)
    implementation(libs.android.roomKtx)
    kapt(libs.android.room.compiler)
}