import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String = "1.1.3"
val logbackVersion: String = "1.2.1"

plugins {
    id("com.github.johnrengelman.shadow").version("4.0.3")
    id("org.jetbrains.kotlin.jvm").version("1.3.21")

    idea
    application
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

val ktorEngineName: String = "io.ktor.server.netty.EngineMain"

application {
    mainClassName = ktorEngineName
}

tasks.withType<KotlinCompile> {

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    kotlinOptions {
        jvmTarget = "1.8"
        apiVersion = "1.3"
        languageVersion = "1.3"
        allWarningsAsErrors = true
    }
}

tasks.shadowJar {
    manifest {
        attributes("Main-Class" to ktorEngineName)
    }
}
