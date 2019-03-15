import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String = "1.1.3"
val logbackVersion: String = "1.2.1"
val koinVersion: String = "2.0.0-beta-3"

val ebeanVersion: String = "11.36.1"
val ebeanQueryBeanVersion: String = "11.35.3"
val ebeanQueryGeneratorVersion: String = "11.35.1"
val jaxbVersion: String = "2.3.1"

val hikariCpVersion: String = "3.3.1"
val h2DatabaseVersion: String = "1.4.199"
val flywayVersion: String = "5.2.4"

plugins {
    id("com.github.johnrengelman.shadow").version("4.0.3")
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
    id("org.jetbrains.kotlin.kapt").version("1.3.21")
    id("io.ebean").version("11.34.1")

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
    implementation("org.koin:koin-core:$koinVersion")
    implementation("org.koin:koin-ktor:$koinVersion")
    implementation("org.koin:koin-logger-slf4j:$koinVersion")

    runtime("com.h2database:h2:$h2DatabaseVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("com.zaxxer:HikariCP:$hikariCpVersion")

    implementation("io.ebean:ebean:$ebeanVersion")
    implementation("io.ebean:ebean-querybean:$ebeanQueryBeanVersion")
    kapt("io.ebean:kotlin-querybean-generator:$ebeanQueryGeneratorVersion")
    implementation("javax.xml.bind:jaxb-api:$jaxbVersion")
    implementation("org.glassfish.jaxb:jaxb-runtime:$jaxbVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.koin:koin-test:$koinVersion")

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

ebean {
    kotlin = true
    queryBeans = true
}

tasks.shadowJar {
    manifest {
        attributes("Main-Class" to ktorEngineName)
    }
}
