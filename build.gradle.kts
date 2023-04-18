plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("org.springframework.hateoas:spring-hateoas:2.0.3")
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.5")

}

tasks.withType<Test> {
    useJUnitPlatform()
}