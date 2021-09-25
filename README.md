# RestApi BackEnd Sample

## 목표
기본적인 RestApi 백엔드 서버입니다. DB 별로 분기처리할 예정입니다.
## 적용내용
1. AOP 적용
- service 실행시간 체크
- 파라미터 및 리턴값 로그 적용

2. Exception 처리
- Valid 위반
- 무결성 조건 오류
- request param 이 없는 경우 오류 코드 반환

3. Swagger 적용

## 주의점
1. 테스트는 주로 크롬 확장 플러그인인 Talended Api Tester 사용하였습니다.
2. Lombok을 설치하였습니다. 각 IDE에 맞게 설정을 해주셔야 합니다.

## 추후 적용예정
1. 로그적용(예정)
2. XSS 적용 (예정)
3. 개인정보 암호화 (예정)
4. Transaction 처리(예정)
5. API 권한 적용
