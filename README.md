# 프로젝트 소개

## 1. 프로젝트 개요

Spring Boot와 Thymeleaf로 구현한 기업 소개 웹 애플리케이션입니다. `MY COMPANY`의 소개, 핵심 가치, 문의 안내를 하나의 페이지에서 제공합니다.

홈 요청(`GET /`)을 `HomeController`에서 처리하고, `index.html` 템플릿을 렌더링합니다.

## 2. 제공 기능

- **기업 소개**: 메인 배너와 회사 소개 문구를 표시합니다.
- **핵심 가치 안내**: 신뢰, 도전, 동반 성장의 세 가지 가치를 소개합니다.
- **페이지 내 이동**: 상단 메뉴에서 홈, 회사 소개, 핵심 가치, 문의 안내 영역으로 이동합니다.
- **반응형 화면**: 화면 너비에 따라 메뉴와 콘텐츠 배치를 조정합니다.
- **접근성 지원**: 본문 바로가기, 키보드 포커스 표시, 이미지 대체 텍스트, 동작 줄이기 설정을 지원합니다.
- **문의 안내**: 하단에 안내 영역을 표시합니다. 현재 연락처와 주소는 준비 중이며, 문의 접수 기능은 구현되어 있지 않습니다.

## 3. 실행 환경 구축

### 준비 사항

- **JDK 21**: Gradle Java Toolchain에 지정된 버전입니다.
- **인터넷 연결**: 최초 실행 시 Gradle과 Maven Central 의존성을 다운로드합니다.
- **웹 브라우저**: 실행한 홈페이지를 확인할 때 사용합니다.

프로젝트에 Gradle Wrapper가 포함되어 있으므로 Gradle을 별도로 설치할 필요는 없습니다. Wrapper에 설정된 버전은 **9.7.1**입니다.

### Java 설정 확인

JDK 21을 설치하고 `JAVA_HOME`을 JDK 설치 경로로, `PATH`에 JDK의 `bin` 경로를 설정합니다. 터미널을 다시 열고 다음 명령으로 확인합니다.

```powershell
java -version
javac -version
```

### 개발 서버 실행

Windows PowerShell에서 `project01` 폴더로 이동한 뒤 실행합니다.

```powershell
cd C:\gen-ai-course\projects\agentic-coding\project01
.\gradlew.bat bootRun
```

macOS 또는 Linux에서는 프로젝트 루트에서 다음 명령을 사용합니다.

```bash
chmod +x gradlew
./gradlew bootRun
```

실행 후 브라우저에서 [http://localhost:8080](http://localhost:8080)에 접속합니다. 서버는 터미널에서 `Ctrl+C`로 종료합니다.

### 실행 설정

`src/main/resources/application.properties`에서 서버 포트와 템플릿·정적 리소스 설정을 관리합니다.

- 기본 서버 포트: `8080`
- Thymeleaf 템플릿: `src/main/resources/templates/`
- CSS 및 이미지: `src/main/resources/static/`
- 개발 중 변경 반영: 템플릿을 소스 폴더에서 읽고 템플릿·정적 리소스 캐시를 비활성화합니다.

현재 템플릿 경로가 상대 경로인 `file:./src/main/resources/templates/`로 설정되어 있으므로, 개발 서버와 JAR 실행 명령 모두 **project01 루트 폴더에서 실행**해야 합니다.

## 4. 빌드 방법

프로젝트 루트에서 다음 명령을 실행합니다. `build`는 컴파일, 테스트, 패키징을 수행합니다.

```powershell
.\gradlew.bat clean build
```

실행 가능한 JAR 파일은 다음 위치에 생성됩니다.

```text
build/libs/project01-0.0.1-SNAPSHOT.jar
```

빌드한 애플리케이션을 프로젝트 루트에서 실행합니다.

```powershell
java -jar build/libs/project01-0.0.1-SNAPSHOT.jar
```

현재 설정에서는 JAR 실행 시에도 소스 폴더의 템플릿을 사용합니다. JAR만 다른 폴더에 배포하려면 `spring.thymeleaf.prefix`를 `classpath:/templates/`로 변경하거나 실행 옵션으로 지정합니다.

```powershell
java -jar build/libs/project01-0.0.1-SNAPSHOT.jar --spring.thymeleaf.prefix=classpath:/templates/
```

테스트만 실행하려면 다음 명령을 사용합니다. 현재 테스트는 Spring 애플리케이션 컨텍스트가 정상적으로 로드되는지 확인합니다.

```powershell
.\gradlew.bat test
```

macOS 또는 Linux에서는 위 명령의 `.\gradlew.bat`를 `./gradlew`로 바꿉니다.

## 5. 주요 사용 기술

| 기술 | 버전 또는 구성 | 용도 |
| --- | --- | --- |
| Java | 21 | 서버 애플리케이션 구현 |
| Spring Boot | 4.1.1 | 애플리케이션 실행 및 자동 설정 |
| Spring Web MVC | Spring Boot 관리 버전 | HTTP 요청 처리 및 MVC 컨트롤러 |
| Thymeleaf | Spring Boot 관리 버전 | 서버 측 HTML 템플릿 렌더링 |
| HTML / CSS | HTML5, CSS 미디어 쿼리·Grid·Flexbox | 페이지 구성과 반응형 스타일 |
| Gradle Wrapper | 9.7.1 | 빌드와 테스트 실행 |
| Spring Dependency Management Plugin | 1.1.7 | 의존성 버전 관리 |
| Spring Boot DevTools | Spring Boot 관리 버전 | 개발 편의 기능 |
| JUnit Jupiter / Spring Boot Test | Spring Boot 관리 버전 | 애플리케이션 컨텍스트 테스트 |
