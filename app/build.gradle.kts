plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.abi.simplecountrypicker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.abi.simplecountrypicker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)

    implementation(project(":simpleCountryPicker"))
    androidTestImplementation("junit:junit:4.12")
}


//publishing {
//    publications {
//        register<MavenPublication>("release") {
//            groupId = "com.github.abi9567"
//            artifactId = "simpleCountryPicker"
//            version = "1.0"
//            artifact("${layout.buildDirectory}/outputs/aar/${artifactId}-release.aar")
//
//            afterEvaluate {
//                from(components["release"])
//            }
//        }
//    }
//
//    repositories {
//        maven {
//            name = "SimpleCountryPicker"
//            /** Configure path of your package repository on Github
//             *  Replace GITHUB_USERID with your/organisation Github userID and REPOSITORY with the repository name on GitHub
//             */
//            url = uri("https://maven.pkg.github.com/GITHUB_USERID/REPOSITORY") // Github Package
//            credentials {
//                //Fetch these details from the properties file or from Environment variables
////                username = githubProperties.get("gpr.usrr") as String? ?: System.getenv("GPR_USER")
////                password = githubProperties.get("gpr.kery") as String? ?: System.getenv("GPR_API_KEY")
//            }
//        }
//    }
//}