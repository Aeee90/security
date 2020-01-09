import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
}

group = "aeee.api"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	compile("com.fasterxml.jackson.module:jackson-module-kotlin")
	compile("org.jetbrains.kotlin:kotlin-reflect")
	compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	
	compile("org.springframework.boot:spring-boot-starter-web")
	
	//Security
	compile("org.springframework.security:spring-security-web:5.2.1.RELEASE")
	compile("org.springframework.security:spring-security-oauth2-client:5.2.1.RELEASE")

	//Resource Server
	compile("org.springframework.boot:spring-boot-starter-oauth2-resource-server:2.2.2.RELEASE")

	//Authorization Server
	compile("org.springframework.security.oauth:spring-security-oauth2:2.4.0.RELEASE")
	compile("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.2.1.RELEASE")
	compile("com.nimbusds:nimbus-jose-jwt:8.4")
	
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
