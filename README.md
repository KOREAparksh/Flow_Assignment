# Flow_Assignment

# 빌드 시 필요한 설정이 있습니다.
local.properties 내에 API 값을 설정해야 합니다.
```properties
~
api.url="https://openapi.naver.com/v1/search/"
api.id="{api id 값}"
api.pw="{api pw 값}"
```
필요한 값은 Flow 업무 댓글을 참고 부탁드립니다.


# 개발환경
kotlin
`1.7.21`
Gradle
`7.5.0`

Android Studio
`Android Studio Electric Eel | 2022.1.1`

compileSdk
`33`
minSdk
`26`
targetSdk
`33`

# Flow_Assignment
## 기능구현목록
- [x] model클래스, dto클래스 작성
- [x] Retrofit 사용하여 네이버 API 연결 및 테스트
- [x] Room 사용하여 DB 연결/저장 및 테스트
- [x] MVVM 구현
- [x] 코루틴 적용
- [x] hilt 적용
- [x] 검색화면 구현
- [x] 검색이력화면 구현

## 주요 구현내용

- local.properties를 활용한 외부키 분리
  - appkey와 같은 민감한 정보를 BuildConfig파일 내 생성되게 구현하였습니다.

- 검색창 이외의 곳을 터치하면 키보드 포커스가 해제되게 구현하였습니다.

- 영화 검색 시 10개 이상의 영화가 있을 경우 infinity scroll을 사용하여 추가 데이터를 받아오도록 하였습니다.

- 최근 검색 10개 중 같은 검색어가 있다면 최신 검색으로 replace됩니다.

## 라이브러리 적용사항

- `livedata`

  영화리스트, 최근이력, loading 데이터들을 livedata로 구현하였고,
  MVVM패턴을 활용하여 데이터(로직)와 화면의 분리를 적용하였습니다.

- `lifecycle`

  ViewModel들을 상태주기를 각 액티비트 생명주기와 결합되어 있고 ViewModel KTX 를 활용하여,
  activity 종료 시 실행 중인 coroutine도 함께 종료되게 구현하였습니다.

- `retrofit`

  retrofit을 사용하여 API 통신을 구현하였습니다.
  특히 `local.properties`내에 api주소와 appkey를 적용하여,
  보안 및 여러 API 확장성을 신경써보았습니다.


- `glide`
  network 이미지 편리함을 위해 사용하였습니다.


- `databinding`
  데이터바인딩을 적용하여 observing된 데이터를 자동으로 업데이트하도록 하였습니다.


- `hilt`
  DB, 통신 등 외부 라이브러리 사용과 각종 ViewModel, Service 클래스들의 의존성 주입을 위해 Hilt를 사용했습니다.



# 컨벤션

## 브런치
- main
  - 프로젝트 생성
  - Docs, HOTFIX, code 정리, file 정리
- dev
  - 개발 브랜치
    - 구현된 기능들이 적용된 개발브랜치
  - Docs
- {feat}-{내용}
  - 한 기능의 개발 브랜치

## 커밋
- `Init`
  첫 생성 시
- `Feat`
  기능 개발, 추가 등 새로이 추가된 내용
- `Fix`
  정상작동되지 않아 무언가 고칠 때
- `Refactor`
  기존 구현사항과 다르게 해결하고자 할 때
- `Add`
  asset file 등 추가 시
- `Build`
  라이브러리 추가 삭제 시
- `Docs`
  문서작성
