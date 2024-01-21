plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false

}

buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }

}

