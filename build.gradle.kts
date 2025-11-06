plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.crewcash"
version = "0.0.1-SNAPSHOT"
description = "template-project"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter:2.8.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
		
	runtimeOnly("org.postgresql:postgresql")
	// runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	// runtimeOnly("io.opentelemetry.javaagent:opentelemetry-javaagent:2.21.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    enabled = false
}
