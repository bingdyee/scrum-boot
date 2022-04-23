# 依赖管理

## ScrumBoot依赖管理 - Maven

```xml
<parent>
    <groupId>io.github</groupId>
    <artifactId>scrum-boot-dependencies</artifactId>
    <version>${scrum.boot.version}</version>
</parent>
```

## ScrumBoot依赖管理 - Gradle

```groovy
buildscript {
    ext {
        scrumBootVersion = '1.0.0'
    }
    repositories {
        mavenLocal()
        maven { url "https://maven.aliyun.com/repository/public" }
        mavenCentral()
    }
    dependencies {
        classpath("io.github:scrum-boot-gradle-plugin:${scrumBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'io.github.scrumboot'
apply plugin: 'io.spring.dependency-management'

repositories {
    mavenLocal()
    maven { url "https://maven.aliyun.com/repository/public" }
    mavenCentral()
}

dependencies {
    implementation('io.github:web-scrum-boot-starter')
    testImplementation('org.springframework.boot:spring-boot-starter-test')
}


```