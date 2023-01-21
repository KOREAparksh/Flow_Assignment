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
  poster주소가 없을 경우 drawable 내 default image를 가져오게 하였습니다.
- viewBinding을
  viewBinding을 사용하여 layout과 코드 간 보일러플레이트를 제거하였고,
  loading progressbar visible 등을 편하게 처리하였습니다.

</br>

- hilt
  정확한 동작원리의 이해가 더 필요하다고 판단하여 이번 과제에서 제외하였습니다.
  남들이 하는대로만 따라하기보단 정확히 아는 상태에서 써야한다고 생각하였습니다.
- databinding
  기능구현 완료 후 적용하려 하였으나 적용하지 못하였습니다.

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
