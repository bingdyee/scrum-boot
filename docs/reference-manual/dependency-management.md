# 依赖管理

## ScrumBoot依赖管理 - Maven

```xml
<parent>
    <groupId>com.yomursin.scrum</groupId>
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
        mavenCentral()
    }
    dependencies {
        classpath("com.yomursin.scrum:scrum-boot-gradle-plugin:${scrumBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'com.yomursin.scrum.boot'
apply plugin: 'io.spring.dependency-management'

repositories {
    mavenLocal()
    maven { url "https://maven.aliyun.com/repository/public" }
    mavenCentral()
}

dependencies {
    implementation('com.yomursin.scrum:web-scrum-boot-starter')
    testImplementation('org.springframework.boot:spring-boot-starter-test')
}


```