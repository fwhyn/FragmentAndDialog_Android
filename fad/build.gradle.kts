@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `maven-publish`

    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    val lSdk = rootProject.extra["lSdk"] as Int
    val javaVersion: JavaVersion by rootProject.extra

    namespace = "com.fwhyn.fad"
    compileSdk = rootProject.extra["mSdk"] as Int

    defaultConfig {
        minSdk = lSdk

        aarMetadata {
            minCompileSdk = lSdk
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }

    testFixtures {
        enable = true
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

    publishing {
        multipleVariants {
            allVariants()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.fwhyn"
            artifactId = "fad"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }

    repositories {
        maven {
            name = "fad"
            url = uri("https://github.com/fwhyn/FragmentAndDialog_Android.git")
        }
    }
}

//group = "com.fwhyn"
//version = "1.0.0"
//
//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            from(components["java"])
//        }
//    }
//
//    repositories {
//        maven {
//            name = "fad"
//            url = uri("https://github.com/fwhyn/FragmentAndDialog_Android.git")
//        }
//    }
//}

dependencies {
    // ----------------------------------------------------------------
    // Main


    // ----------------------------------------------------------------
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}