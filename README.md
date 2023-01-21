# Flow_Assignment

# 빌드 시 필요한 설정이 있습니다.
local.properties 내에 API 값을 설정해야 합니다.
```properties
~
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
- [ ] 검색이력화면 구현

## 라이브러리 적용사항

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
