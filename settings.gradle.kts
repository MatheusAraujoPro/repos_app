pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "modulartemplate"
include(":app")
include(":core")
include(":ui")
include(":feature")
include(":core:data_local")
include(":core:data_remote")
include(":core:domain")
include(":core:data")
