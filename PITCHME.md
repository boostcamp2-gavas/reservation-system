## Spring <span style="color:#99FF00">Security</span><br>
### **<span style="color:#00ff99">By 각자바스</span>**
---
|**Authentication**|**Authorization**|
|:-:|:-:|
|시스템 접근 시, 등록된 사용자인지 여부를 확인하는 것| 시스템 접근 후, 인증된 사용자에게 권한을 부여하는 것|
|시스템 로그인|권한에따른 접근 권한<br> 사용자 등급(일반/관리자)|

---
## **Why Spring Security?**

---

### 우리 시스템의 이슈
1. 시스템에 Authentication 만 존재
2. Login 과정의 로직 분리가 확실하게 되어있지 않다
<br>
<SpringSecurity 이전의 로그인 로직 그림>
---
### 해결방안
#### Spring Security 활용
1. Role 기반의 접근 권한 지원
2. Spring MVC와의 분리
<SpringSecurity 이후의 로그인 로직 그림>

---
## **Adapt Spring Security (OAuth2)**

---

### 1. Spring Security Dependency 추가

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
    <version>SPRING_SECURITY_VERSION</version>
</dependency>

<dependency>
    <groupId>org.springframework.security.oauth</groupId>
    <artifactId>spring-security-oauth2</artifactId>
    <version>SPRING_SECURITY_OAUTH2_VERSION</version>
</dependency>
```

---

### 2. Spring Security Filters

<그림으로 변경><br>
SecurityContextPersistenceFilter<br>
HeaderWriterFilter<br>
CsrfFilter<br>
LogoutFilter<br>
RequestCacheAwareFilter<br>
SecurityContextHolderAwareRequestFilter<br>
SessionManagementFilter<br>
ExceptionTranslationFilter<br>
OAuth2ClientContextFilter<br>
**CompositeFilter**

---
### 3. 인증 및 권한 제어 3가지 방법
<span style="float: left">1. @Secured</span> <br>

```java
@Secured("ROLE_USER")
@Secured("ROLE_ADMIN")
```

<span style="float: left">2. Configure in WebSecurityConfigurerAdapter</span> <br>

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/index").access("ROLE_ADMIN");
        .antMatchers("/**").access("ROLE_USER");
}
```

<span style="float: left">3. xml file</span> <br>

```xml
<intercept-url pattern="/index" access="hasRole('ADMIN')"/>
<intercept-url pattern="/**" access="hasRole('USER')" />
```

---
### 4. OAuth2를 이용하기 위한 방법으로 필터 확장 및 등록하는 법
- 다양한 리소스를 간편하게 등록해서 확장 가능
    - naver
    - facebook
    - 트위터
    - 카카오
    - 등등..

---
### 5. 필터 동작 과정

<플로우 차트>..

---
### 6. Authorization 결과물
- 사진 2장
    - ROLE_USER 접근 불가.jpg + log
    - ROLE_ADMIN 접근 승인.jpg + log
---
## 시행착오

- Spring Security Boot에는 이렇게 되어 있었는데 Spring Security에서는 달랐다(결론: 레퍼런스와 구현체 코드 분석)
- OAuth2를 사용하는 우리 시스템에 적용은 어떻게 해야 할지(결론: Add Custom Filter)
- MVC와 분리시킨 스프링 시큐리티의 컨셉을 잘 살리는 구현이 뭘지(결론: 원래 있던 로그인 코드를 완전히 걷어내고 arguementResolver에서 컨텍스트의 홀더를 통해 user를 받아오는 형태)

---
## 결론

- 시행착오를 통해 얻은 결론을 여기서 소개?
- 시큐리티의 장점을 다시 환기 및 그 이유를 적용하여 더욱 확실히 알 수 있었다.
- //Spring MVC와 Spring Security의 완전한 로직 분리

---
## Q&A
#### 감사합니다.
