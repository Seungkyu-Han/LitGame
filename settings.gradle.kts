plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "LitGame"
include("container")
include("king")
include("king:king-presentation")
findProject(":king:king-presentation")?.name = "king-presentation"
include("king:king-core")
findProject(":king:king-core")?.name = "king-core"
include("error-reporter")
