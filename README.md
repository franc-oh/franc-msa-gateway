# SpringCloud MSA - Gateway Server
> 스프링클라우드를 통한 MSA 구축 - 게이트웨이 서버 

<br/>

## ⚙️ 개발 환경
- JDK 17
- Spring Boot 3.2.1
- Spring Cloud Gateway 4.1.5
- Spring Cloud Eureka Client 4.1.3
- Lombok
- Gradle 8.10

<br/>

## 📌 서비스 설명

### 프로파일
> `test`
- VM Option = `-Dspring.profiles.active={profile}`

<br/>

### 라우팅 정보
> {hostname}:{port}/franc/api
- 유저 서비스 : `/franc/api/users/**`
- 주문 서비스 : `/franc/api/orders/**`


