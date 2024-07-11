plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.foodzone"
//    compileSdkVersion = 30.toString();
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodzone"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
  buildString {

  }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    //new
    buildFeatures{
        viewBinding =true

    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    implementation("com.google.firebase:firebase-database:21.0.0")
    implementation("com.github.ismaeldivita:chip-navigation-bar:1.4.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")

        // Easy UPI Payment library
        implementation (  "com.shreyaspatil:EasyUpiPayment:2.0")

       androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}