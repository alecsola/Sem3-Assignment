plugins {
    id("java")
    id ("org.springframework.boot") version ("3.1.0")
    id ("io.spring.dependency-management") version ("1.1.0")
    id("org.sonarqube") version "4.2.1.3168"
    id ("jacoco")
}
jacoco {
    toolVersion = "0.8.7" // Use the latest version available
}


group = "fontys.sem3.school"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.security:spring-security-crypto:5.5.0")
    //implementation ("org.flywaydb:flyway-mysql")
    implementation ("mysql:mysql-connector-java:8.0.33")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("mysql:mysql-connector-java")
    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-starter-websocket")
    testRuntimeOnly ("com.h2database:h2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JacocoReport> {
    reports {
        xml.required.set(true)
    }
}

tasks.named("test").configure {
    finalizedBy("jacocoTestReport")
}

tasks.named("sonar").configure {
    dependsOn("test")
}