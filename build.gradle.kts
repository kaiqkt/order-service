plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "order-service"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/kaiqkt/*")
        credentials {
            username = project.findProperty("gpr.user") as String?
            password = project.findProperty("gpr.key") as String?
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("javax.jms:javax.jms-api:2.0.1")
    implementation("com.amazonaws:aws-java-sdk:1.12.687")
    implementation("com.amazonaws:amazon-sqs-java-messaging-lib:1.0.4")
    implementation("io.azam.ulidj:ulidj:1.0.1")
    implementation("com.kaiqkt:saga-core:1.0.1")
    implementation("com.kaiqkt:saga-jms:1.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
