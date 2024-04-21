plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin") //pt currency
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.barbuceanuconstantin.proiectlicenta"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.barbuceanuconstantin.proiectlicenta"
        minSdk = 26
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
        viewBinding = true //pt currency
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
    implementation("androidx.compose.material:material:1.6.5")  //

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.material3:material3-android:1.2.1") ///
    implementation("com.google.android.libraries.places:places:3.4.0") ///Pt. LocalDate.now()
    testImplementation("junit:junit:4.13.2") //
    androidTestImplementation("androidx.test.ext:junit:1.1.5") //
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") //
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.5")   //
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.5") //
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.5")   //

    implementation("androidx.compose.material:material-icons-extended:1.6.5") /////
    implementation("androidx.graphics:graphics-shapes:1.0.0-alpha05") //

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    androidTestImplementation ("androidx.room:room-testing:2.6.1")

    //Navigation Compose
    dependencies {
        val navVersion = "2.7.7"

        // Java language implementation
        implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
        implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

        // Kotlin
        implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
        implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

        // Feature module Support
        implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

        // Testing Navigation
        androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")

        // Jetpack Compose Integration
        implementation("androidx.navigation:navigation-compose:$navVersion")
    }

    //
    implementation("androidx.compose.material:material-icons-extended-android:1.6.5") //
    //

    //pt. currency
    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.28-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.28-alpha")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.8.2")

    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    //DatePicker
    implementation ("androidx.compose.material3:material3:1.2.1")
}