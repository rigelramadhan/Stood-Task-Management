pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Stood"
include(":app")
include(":core")
include(":core:domain")
include(":core:data")
include(":features:task")
include(":cosmoe")
include(":features:auth")
