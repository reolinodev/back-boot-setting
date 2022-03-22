# 4.RDBMS

### # 구현 내용
* RDBMS 설정
  * 프로필 별 DB 설정
    * dev : h2 
    * qa : postgresql
    * prod : maria 
  * DB 타입에 따라 mapper 분기
* log4jdbc 적용
* transaction 처리
* 테스트 코드 예제 추가
    
<hr/>

### # 권장사항
1. 로그 스타일은 각자의 성향에 맞게 수정할 것
2. 사용하는 DB에 맞춰서 DataConfig.java 파일을 수정해서 사용할 것
3. h2 테스트시 sql에 있는 data.sql, schema.sql를 resources로 옮겨서 사용하면 
해당 스크립트가 자동 실행됨


