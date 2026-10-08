---
name: build-company-homepage
description: Build or update the company homepage using its existing index.html, CSS, and Spring MVC Thymeleaf setup. Use for company landing page layout, content, styling, and responsive improvements in this project.
---

# 기업 홈페이지 제작

기존 홈페이지를 기준으로 기업 소개 화면을 제작하거나 수정한다. 사용자에게 받은 회사 정보와 디자인 요구를 우선 적용하며, 별도 요청이 없으면 기존 Thymeleaf 구성을 유지한다.

## 작업 범위와 참조 파일

이 스킬은 `.agents/skills/build-company-homepage/`에 위치한다. 이 디렉터리에서 `../../../`가 프로젝트 루트다. 아래 경로는 모두 프로젝트 루트를 기준으로 한다.

- 먼저 `AGENTS.md`를 읽고 최신 프로젝트 지침을 적용한다.
- `src/main/resources/templates/index.html`을 홈페이지 구조와 콘텐츠의 기준으로 읽는다.
- `src/main/resources/static/css/home.css`를 함께 읽어 HTML 클래스와 연결된 디자인을 확인한다.
- 경로나 서버 처리가 관련되면 기존 `HomeController.java`와 `src/main/resources/application.properties`를 확인한다. 컨트롤러는 `src/main/java/`에서 검색한다.
- `README.md`는 수정하지 않는다. 홈페이지 요청을 이유로 패키지, 의존성, 로그 설정을 변경하지 않는다.

## 참고 화면의 구성

다음은 현재 참고 화면의 구성이다. 새 요구사항에 따라 필요한 영역을 조정하되, 기존 화면을 수정할 때 관련 없는 사용자 콘텐츠를 제거하지 않는다.

| 영역 | 기준 요소 | 구현 의도 |
| --- | --- | --- |
| 본문 바로가기 | `.skip-link`, `#main` | 키보드로 본문에 바로 접근 |
| 헤더 | `.header-inner`, `.brand`, `nav` | 브랜드와 페이지 내 메뉴 |
| 메인 배너 | `#home`, `.hero`, `.hero-image`, `.hero-shade` | 배경 이미지, 핵심 문구, 회사 소개 버튼 |
| 회사 소개 | `#about`, `.about`, `.about-description` | 제목과 소개문을 두 열로 배치 |
| 핵심 가치 | `#values`, `.value-grid` | 신뢰·도전·동반 성장 카드 |
| 문의 및 푸터 | `#contact`, `.footer-main`, `.footer-bottom` | 연락 안내와 저작권 |

메뉴의 `href`와 대상 `id`를 일치시킨다. 영역을 바꾸면 연결된 메뉴와 버튼도 함께 갱신한다. 참고 파일의 `#div1`, `#div2`와 “맨 위에 추가한 내용”은 기존 사용자 콘텐츠다. 새 홈페이지의 필수 구성으로 복제하지 않으며, 기존 페이지에서는 요청 없이 삭제하지 않는다.

## 디자인 적용

- 기존 팔레트는 `--ink: #182b3c`, `--muted: #64717c`, `--accent: #16566c`다. 기존 페이지 개선 시 이 변수를 활용한다. 새 브랜드 색상이 주어지면 일관되게 조정한다.
- 기존 `.container`는 최대 1180px이며, 배너는 이미지 위에 그라데이션을 겹쳐 흰색 문구의 가독성을 확보한다.
- 회사 소개는 두 열, 핵심 가치는 세 열을 기본으로 하고, 기존 760px 이하 미디어 쿼리에서는 한 열로 전환한다. 콘텐츠가 넘치면 중간 너비에서도 배치를 조정한다.
- 한국어 본문이 자연스럽게 줄바꿈되도록 하고 긴 회사명이나 메뉴가 잘리거나 가로 스크롤을 만들지 않도록 한다.
- 참고 글꼴은 Pretendard, Noto Sans KR, Malgun Gothic 순의 폴백이다. 글꼴 선언만으로 웹폰트가 다운로드되는 것은 아니므로 새 외부 폰트 의존성을 임의로 추가하지 않는다.
- 기존 배너는 `static/images/company-banner.png`다. 다른 이미지를 적용할 때 실제 파일 존재와 경로를 확인하고 대체 텍스트를 이미지 내용에 맞춘다.

## Thymeleaf 구현

- HTML은 `src/main/resources/templates/`에, CSS는 `static/css/`, 이미지는 `static/images/`, 필요한 JavaScript는 `static/js/`에 둔다.
- `lang="ko"`, UTF-8, viewport, `xmlns:th="http://www.thymeleaf.org"`를 유지한다.
- 스타일시트는 `th:href="@{/css/home.css}"`, 이미지는 `th:src="@{/images/company-banner.png}"`처럼 연결한다. 다른 파일을 만들면 실제 파일명에 맞춘다.
- 기존 `GET /` 컨트롤러가 `"index"`를 반환하는 흐름을 유지한다. 정적인 화면 변경에 불필요한 서비스나 API를 추가하지 않는다.
- 동적 데이터가 필요한 경우 `Model`로 전달하고 `th:text`, `th:each`, `th:if`로 표현한다. 검증되지 않은 값을 `th:utext`로 출력하지 않는다.
- 제공되지 않은 회사 실적, 주소, 전화번호를 사실처럼 만들지 않는다. 현재 문의 영역은 준비 중 안내이며, 접수 기능을 추가하려면 사용자 요청에 맞는 실제 서버 처리까지 구현한다.
- 제목 계층, `aria-labelledby`, 메뉴 이름, 이미지 대체 텍스트와 키보드 포커스를 유지한다. 장식 요소는 필요 시 `aria-hidden="true"`로 처리한다.
- 부드러운 스크롤이나 애니메이션을 사용할 때 `prefers-reduced-motion` 설정을 존중한다.

## 검증과 작업 결과

- 변경한 HTML과 CSS의 경로, 앵커 연결, 중복 ID 및 리소스 존재를 확인한다.
- 화면을 실행할 때 프로젝트 루트에서 `.\gradlew.bat bootRun`을 사용한다. 현재 템플릿 설정이 소스 폴더의 상대 경로를 사용하기 때문이다.
- 브라우저 도구를 사용할 수 있으면 `http://localhost:8080`에서 데스크톱과 모바일 너비의 배너, 메뉴, 카드, 푸터를 확인한다. 키보드 본문 바로가기와 링크 이동도 확인한다.
- Java 또는 컨트롤러를 변경한 경우 `.\gradlew.bat test`나 `.\gradlew.bat build`로 해당 변경을 검증한다. 정적인 문구·스타일 변경에 구현을 그대로 따라 하는 테스트를 추가하지 않는다.
- 완료 시 변경 파일과 구현한 화면 동작을 간단히 안내한다. 실행 또는 브라우저 확인을 하지 못했다면 그 사실을 명시한다.
