plugins {
    id(Plugins.multiPlatformComposePlugin)
    kotlin(Plugins.kotlinSerialization) version "1.8.10"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.core))
                implementation(project(Modules.commonUi))
                implementation(Libs.kotlinSerializer)
                implementNetworkingLibraries()
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Libs.navigationCompose)
                implementation(Libs.ktorCioEngine)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Libs.ktorDarwinEngine)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(Libs.ktorCioEngine)
            }
        }
    }
}
