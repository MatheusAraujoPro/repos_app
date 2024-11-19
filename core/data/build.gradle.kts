plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "${rootProject.projectDir}/android-common.gradle")

android {
    namespace = "com.example.core.data"
}
dependencies {
    implementation(project(":core:domain"))
}