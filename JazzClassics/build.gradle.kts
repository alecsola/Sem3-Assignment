plugins {
    id("java")
    id ("org.springframework.boot") version ("3.1.0")
    id ("io.spring.dependency-management") version ("1.1.0")
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
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")

}

tasks.test {
    useJUnitPlatform()
}