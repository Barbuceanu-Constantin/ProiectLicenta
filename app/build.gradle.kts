plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.room5_documentatie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.room5_documentatie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    //////////////////
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    ///////////////////
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") //
    implementation("androidx.activity:activity-compose:1.8.2")  //
    implementation("androidx.compose.ui:ui:$1.6.1")  //
    implementation("androidx.compose.ui:ui-tooling-preview:$1.6.1") //
    implementation("androidx.compose.material:material:1.6.2")  //

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.material3:material3-android:1.2.0") ///
    testImplementation("junit:junit:4.13.2") //
    androidTestImplementation("androidx.test.ext:junit:1.1.5") //
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") //
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.2")   //
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.2") //
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.2")   //

    implementation("androidx.compose.material:material-icons-extended:1.6.2") /////

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    androidTestImplementation ("androidx.room:room-testing:2.6.1")

    //Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")  //
}