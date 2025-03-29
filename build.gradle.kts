// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    extra.apply {
        set("activityVersion", "1.2.3")
        set("appCompatVersion", "1.3.0")
        set("constraintLayoutVersion", "2.0.4")
        set("coreTestingVersion", "2.1.0")
        set("coroutines", "1.5.0")
        set("lifecycleVersion", "2.3.1")
        set("materialVersion", "1.3.0")
        set("roomVersion", "2.6.1")
        set("junitVersion", "4.13.2")
        set("espressoVersion", "3.1.0")
        set("androidxJunitVersion", "1.1.2")
    }
}