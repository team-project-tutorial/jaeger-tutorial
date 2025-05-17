import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

allprojects {
    group = providers.gradleProperty("PROJECT_GROUP").get()

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<BootJar> { archiveFileName.set("${project.name}.jar") }
    tasks.withType<Jar> { enabled = false }
    tasks.withType<Test> { useJUnitPlatform() }
}

tasks.withType<BootJar> { enabled = false }
tasks.withType<Test> { useJUnitPlatform() }
