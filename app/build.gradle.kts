import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "cnovaez.dev.moviesbillboard"
    compileSdk = 34

    defaultConfig {
        applicationId = "cnovaez.dev.moviesbillboard"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
    testOptions {
        fun Packaging.() {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")



    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")

    //extended icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Dagger-Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.test:core-ktx:1.5.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Room
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

    // Coil (Async Image Loading)
    implementation("io.coil-kt:coil-compose:2.5.0")

    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //For permissions
    implementation("com.google.accompanist:accompanist-permissions:0.25.0")

    //Testing
    androidTestImplementation ("androidx.arch.core:core-testing:2.1.0")

    androidTestImplementation ("androidx.test:runner:1.4.0")

    androidTestImplementation ("androidx.test:rules:1.4.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    testImplementation ("junit:junit:4.+")
    testImplementation ("io.mockk:mockk:1.12.2")
    testImplementation ("org.mockito:mockito-core:3.12.4")
    androidTestImplementation ("io.mockk:mockk-android:1.12.2")

    androidTestImplementation("org.mockito:mockito-android:3.12.4") {
        exclude(group = "net.bytebuddy", module = "byte-buddy")
    }

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}