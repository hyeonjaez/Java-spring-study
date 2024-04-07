# 학습 내용

## 팀별 학습


- 팀을 만들어서 java의 대략적인 내용 학습
- 과제(스타크래프트, dictionary file parse)

## 자바 iterator 실습

- 자바 이터레이터에 대한 개념 학습
- 예외상황 처리 및 test 코드 작성 학습

## Servlet/Jsp를 이용한 쇼핑몰 Web Application 개발

- Servlet/Jsp 환경에서 구현

## Spring Core

- Spring Framework 특징
- 스프링 DI, IOC, Spring Bean 생명주기
- AOP
- Mockit
- Spring 기반의 테스트 코드

### Spring Core 1 과제
- practice 0
  - ClassPathXmlApplicationContext + beans.xml 을 사용하여 프로그램을 작성한다. 단 스프링 빈 선언은 beans.xml 에 한다.
- practice 1
  - AnnotationConfigApplicationContext + JavaConfig 를 사용하여 프로그램을 작성한다. 단 스프링 빈 선언은 @Bean 을 사용한다.
- practice 2
 -  AnnotationConfigApplicationContext + JavaConfig 를 사용하여 프로그램을 작성한다. 단 스프링 빈 선언은 Stereotype annotations 을 사용한다.

### Spring Core 2 과제 (springframework 마이그레이션)
- Spring Framework로 적절하게 Migration 하기
- DI, IOC , AOP 학습

### Spring Core 3 과제 (상수도)
- 제공하는 상수도 요금표를 메모리에 로드하고 사용자가 입력한 사용량에 따라 요금을 지자체별, 업종별로 표시하는 기능을 제공하는 기능 구현해보기
- IOC, DI 실습

## Spring MVC
- Spring MVC Framework의 중심이 되는 DispatcherServlet의 동작하는 흐름 이해
- 실제 웹 요청을 처리하는 @Controller를 구현을 통한 URL Mapping , HTTP Method Mapping, Request Parameter와 연결, ModelAndView, Header 값 읽기, Cookie 값 읽기 등 Servlet/JSP와 비교하여 학습
- JAVA Validation API Specification을 구현한 jakarta.validation-api를 이용하여 Bean Validation 방법을 학습하며 추가로 Spring에서 제공하는 Validator를 이용하여 검증할 수 있도록 학습
- Spring MVC Components의 구조와 동작하는 흐름을 이해하고, 각각의 Components의 기능과 역할을 학습합니다.
- File upload와 관련된 HTTP Specfication을 이해하고 이를 Abstraction 한 Servlet 3.0에서 추가된 File Upload API를 학습합니다.
- File Upload API를 구현한(StandardServletMultipartResolver,CommonsMultipartResolver)구현체를 MultipartResolver로 등록하여 Multipart 요청을 처리하고 구현할 수 있도록 학습
- spring-test, junit.jupiter, mockito-core을 이용한 @Controller Unit Test, Integration Test 하는 방법을 학습
- @RestController 기반으로 Students CRUD 실습을 통해서 @RestControllerAdvice(예외 처리), HTTP Response status code 응답 등 적절한 REST-API를 구현할 수 있도록 학습

### Spring MVC 1,2,3 과제 
- 요구사항에 맞춰 mvc 실습

### Spring MVC 최종 과제 (nhnmart-cs)
- NHN Mart 고객 센터 요구사항에 맞게 mvc 적용하여 구현

## Spring JPA
- Spring @Bean 기반의 JPA 환경설정을 할 수 있도록 학습
- Entity의 저장, 수정, 삭제, 조회 등 Entity와 관련된 모든 일을 처리하는 EntityManager을 생성하고 관리하는 방법을 학습
- 데이터베이스의 Table과 Java Object를 맵핑하며 @IdClass와 @EmbeddedId를 이용해 복합 key 맵핑할 수 있도록 학습
- 영속성 컨텍스트의 생명주기 (비 영속, 영속, 준 영속,삭제)의 상태와 특징에 대해서 학습하며 영속성 컨텍스트가 Entity를 관리함으로써 얻을 수 있는 이점에 대해서 학습
- 데이터베이스 테이블 간의 연과 관계를(@OneToOne, @OneToMany, @ManyToOne,@ManyToMany)직접 구현하고, Fetch 전략(즉시 로딩, 지연 로딩)에 따라서 연관관계에 있는 Entity를 어떻게 가져올 것인지에 대해서 학습
- Entity가 영속화될 때 자식 Entity로 전이되는 영속성 전의 속성의 특징에 대해서 학습
- Spring Data JPA가 제공하는 Method 기반의 query를 작성할 수 있으며 QueryDSL를 이용하여 복잡한 Query를 작성할 수 있습니다. 또한 반환하는 데이터는 원하는 필드만 뽑아서 DTO (Data Transfer Object) 형태로 사용할 수 있도록 학습

### Spring JPA 1 과제 
- entity 맵핑

### Spring JPA 2 과제
- 연관관계 매핑 및 Repository interface 작성

### Spring JPA 3 과제
- repository 기능 완성

### Spring JPA 최종 과제 (CertificateSystem)
- 증명서 발급 시스템 요구사항에 맞게 JPA 적용하여 구현

## Spring boot
- Spring Boot Initializer을 통해서 프로젝트를 생성하고 Dependency Injection 실습, MYSQL을 이용한 실습, 테스트코드 실습 등 단계별로 진행하여 학생 점수 조회 REST-API 구현하고, 이를 통해 전체적인 Spring Boot 기반의 Application 개발하는 방법을 학습
- Spring Boot의 다양한 View 지원 중 spring-boot-starter-thymeleaf을 통해서 thymeleaf을 활용한 view 구현을 학습
- Spring Framework 관련 기술을 사용하기 위한 의존성 관리 세트인 Spring Boot Starter의 의존성 관리와 설정하는 방식을 학습
- Spring Boot의 Auto Configuration에 대해서 학습하고 핵심 Annotation인 @ConditionalOnXXX을 사용하여 조건별 Bean을 생성하는 방법을 학습
- RestTemplate Bean을 설정하고, 설정된 빈을 사용하여 REST API를 호출하는 방법을 학습
- Unit Test를 작성하는 방법을 학습

### Spring boot 1 과제 
- 학생 관리 시스템

### Spring boot 2 과제
- Account 시스템

## Spring 전체 최종 과제

- 팀 프로젝트 - mini dooray 개발
- [minidooray](https://github.com/nhn-academy-minidooray)

## Spring 최종 시험
- 프로젝트 과정 선발 과제 시험
- 스프링 과정에서 배운 내용을 시험
- 간단한 부소 조회 REST API 개발
