plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
}

android {
    namespace = "com.wower.selfcareapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.wower.selfcareapp"
        minSdk = 26
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.appcompat:appcompat:${rootProject.extra.get("appCompatVersion") as String}")
    implementation("androidx.activity:activity-ktx:${rootProject.extra.get("activityVersion") as String}")

    // Dependencies for working with Architecture components
    // You'll probably have to update the version numbers in build.gradle (Project)

    // Room components
    implementation("androidx.room:room-ktx:${rootProject.extra.get("roomVersion") as String}")
    kapt("androidx.room:room-compiler:${rootProject.extra.get("roomVersion") as String}")
    androidTestImplementation("androidx.room:room-testing:${rootProject.extra.get("roomVersion") as String}")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.extra.get("lifecycleVersion") as String}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${rootProject.extra.get("lifecycleVersion") as String}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${rootProject.extra.get("lifecycleVersion") as String}")

    // Kotlin components
    // implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version") // kotlin-stdlib is already provided by the kotlin plugin
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.extra.get("coroutines") as String}")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${rootProject.extra.get("coroutines") as String}")

    // UI
    implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra.get("constraintLayoutVersion") as String}")
    implementation("com.google.android.material:material:${rootProject.extra.get("materialVersion") as String}")

    // Testing
    testImplementation("junit:junit:${rootProject.extra.get("junitVersion") as String}")
    androidTestImplementation("androidx.arch.core:core-testing:${rootProject.extra.get("coreTestingVersion") as String}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra.get("espressoVersion") as String}") {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    androidTestImplementation("androidx.test.ext:junit:${rootProject.extra.get("androidxJunitVersion") as String}")

    testImplementation("io.mockk:mockk:1.13.11") // Or the latest version
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")

    implementation("com.google.dagger:dagger:2.56")
    kapt("com.google.dagger:dagger-compiler:2.56")

    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    implementation("androidx.navigation:navigation-compose:2.7.7")
}

fun DependencyHandlerScope.kapt(dependency:
                                ProviderConvertible<MinimalExternalModuleDependency>) {
    add("kapt", dependency)
}
