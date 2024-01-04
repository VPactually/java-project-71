plugins {
    application
    jacoco
    id("checkstyle")
    id("io.freefair.lombok") version "8.4"
    id("java")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"


application {
    mainClass.set("hexlet.code.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

tasks.jacocoTestReport { reports { xml.required.set(true) } }


tasks.test {
    useJUnitPlatform()
}

tasks.compileJava {
    options.release.set(11)
}
