plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Assignment-HSM"

include("storage:db-core")
include("core:core-api")
include("core:core-domain")
include("support:logging")
