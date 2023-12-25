plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}

buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}
