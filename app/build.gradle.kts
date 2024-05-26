plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
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

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") //
    implementation("androidx.activity:activity-compose:1.9.0")  //
    implementation("androidx.compose.ui:ui:1.6.6")  //
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.6") //
    implementation("androidx.compose.material:material:1.6.6")  //

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.material3:material3-android:1.2.1") ///
    implementation("com.google.android.libraries.places:places:3.4.0") ///Pt. LocalDate.now()
    testImplementation("junit:junit:4.13.2") //
    androidTestImplementation("androidx.test.ext:junit:1.1.5") //
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") //
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.6")   //
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.6") //
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.6")   //

    implementation("androidx.compose.material:material-icons-extended:1.6.6") /////
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

        // Hilt
        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    }

    //
    implementation("androidx.compose.material:material-icons-extended-android:1.6.6") //
    //

    ///////////////////////////////////////////////////////////////////////////
    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48") //dont touch
    kapt("com.google.dagger:hilt-android-compiler:2.48")  //dont touch
    //implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03") //scos conform video yt
    //kapt("androidx.hilt:hilt-compiler:1.2.0")                              //nu e nevoie

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    ///////////////////////////////////////////////////////////////////////////

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.9.0")

    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    //DatePicker
    implementation ("androidx.compose.material3:material3:1.2.1")

    //CollectAsStateWithLifeCycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    //YCharts
    implementation("co.yml:ycharts:2.1.0")
}