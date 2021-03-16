# Ourdus Backend
> [Idus](https://www.idus.com/) 의 Clone Project입니다.


## Member
| 김서연 | 우혜진 | 이화경 |
| :----: | :----: | :----: |
| [@ksy991018](https://github.com/ksy991018) | [@HJ-Woo](https://github.com/HJ-Woo) | [@HwaGyeong](https://github.com/HwaGyeong) |


## Docuemnt
**✔ ERD**  
![image](https://user-images.githubusercontent.com/59992230/111411874-b297e600-871e-11eb-8072-397a86065a71.png)
[ERD Cloud](https://www.erdcloud.com/d/3d3aooW9fSG2fyfQZ)

**✔ API document**
![image](https://user-images.githubusercontent.com/59992230/111412048-06a2ca80-871f-11eb-8fe5-a2133074d24e.png)
[API Document WIKI](https://github.com/Ourdus/Ourdus/wiki)

## 기술 스택
- Java 11
- Spring Boot 
- JPA
- AWS EC2, Docker
- Travis CI
- AWS RDS (MySQL) - Prod
- H2 Database - Dev

-> [왜 이런 기술 스택을 선택했는가?]()

## 프로젝트 구조
```
(main)
.
├─ ourdus-spring(backend)
│  ├─ src
│  │  ├─ main
│  │  │  └─ java.ourdus.ourdusspring
│  │  │      ├─ common
│  │  │      ├─ controller
│  │  │      ├─ domain
│  │  │      ├─ dto
│  │  │      ├─ interceptor
│  │  │      ├─ respository
│  │  │      └─ service
│  │  └─ test
│  │     └─ java.ourdus.ourdusspring
│  │
│  ├─ build.gradle
│  ├─ gradlew
│  ├─ gradlew.bat
│  └─ settings.gradle
│
│
└── Dockerfile


(document)
.
└─ document/crawling
    ├─ crawling-code
    │  
    └─ crawling-csv

```

## 프로젝트 사용 방법

1. use dockerfile
```
docker build -f Dockerfile.dev . -t name

docker run -d -p 8080:8080 name 
```

2. console (window)
```
cd ourdus-spring

gradlew build

cd build/libs

java -jar -Dspring.profiles.active=dev ourdus-spring-0.0.1-SNAPSHOT.jar
```

3. Intellij 설정
```
File>Settings>Gradle>Gradle JVM: version11

File>Settings>Java Compiler>version: 11

Run/Debug Configuration>Environment>VM Options: -Dspring.profiles.active=dev
```