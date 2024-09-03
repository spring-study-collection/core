<!--
<details>
<summary>소제목</summary>

내용
</details>
-->

<details>
<summary>객체지향 프로그래밍</summary>

- 객체지향 특징에는 추상화, 캡슐화, 상속, 다형성이 있다.
- 객체 설계 시, 자바 언어의 다형성을 활용하여 역할과 구현을 분리한다.
  - 역할 = 인터페이스
  - 구현 = 인터페이스를 구현한 클래스, 구현 객체
- 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경할 수 있다.
</details>

<details>
<summary>SOLID 원칙</summary>

좋은 객체지향 설계의 5가지 원칙이다.
- SRP (Single Responsibility Principle)
  - 단일 책임 원칙
  - 변경이 있을 때, 파급 효과가 적으면 잘 따른 것이다.
- OCP (Open/Closed Principle)
  - 개방-폐쇄 원칙
  - 확장에는 열려있으나, 변경에는 닫혀있어야 한다.
- LSP (Liskov Substitution Principle)
  - 리스코프 치환 원칙
  - 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다.
- ISP (Interface Segregation Principle)
  - 인터페이스 분리 원칙
  - 범용 인터페이스 하나보다 특정 클라이언트를 위한 여러 인터페이스가 낫다.
- DIP (Dependency Inversion Principle)
  - 의존관계 역전 원칙
  - 구현 클래스에 의존하지 말고 인터페이스에 의존해야 한다.

다형성만으로 OCP, DIP를 지킬 수 없다.
스프링의 DI(의존성 주입) 컨테이너 제공으로 다형성 + OCP, DIP를 가능하게 한다.
즉, 클라이언트의 코드 변경 없이 기능 확장이 가능하다.
</details>

<details>
<summary>프로젝트 환경설정 관련</summary>

- Preferences > gradle 검색 > Build and run using, Run test using을 IntelliJ IDEA로 변경
- gradle을 통해서 돌리면 시간이 약간 오래 걸림
</details>

<details>
<summary>JUnit (단위 테스트 프레임워크)</summary>

- given - when - then 순서로 작성
- 검증 시, Assertions
</details>

<details>
<summary>AppConfig</summary>

- 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
- AppConfig를 통해 생성자를 주입하여 OCP, DIP를 위반하지 않게 한다.
- 스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)한다. 
</details>

<details>
<summary>스프링 컨테이너</summary>

- ApplicationContext를 스프링 컨테이너라 한다.
  ```java
  // 스프링 컨테이너 생성
  ApplicationContext applicaionContext = new AnnotationConfigApplicationContext(AppConfig.class);
  ```
- @Configuration 이 붙은 AppConfig를 구성 정보로 한다.
- @Bean 이 붙은 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. (빈 이름이 중복되면 안 됨)
- 이제 스프링 컨테이너를 통해 스프링 빈 객체를 조회해야 한다.
- @Bean 이 붙은 메서드 이름을 스프링 빈의 이름으로 사용한다. (따로 지정 가능)
> - BeanFactory는 스프링 컨테이너의 최상위 인터페이스다. 
> - ApplicationContext는 BeanFactory 기능(빈 관리 기능)을 상속받아서 제공한다. (+ 편리한 부가기능 상속)
> - 둘 다 스프링 컨테이너라 한다.
</details>

<details>
<summary>스프링 빈 조회</summary>

- 스프링 빈 객체 조회
  - applicationContext.getBean('빈 이름', 타입)
  - applicationContext.getBean(타입)
- 타입으로 조회 시, 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. -> 빈 이름을 지정해서 해결
- ac.getBeansOfType(타입) -> 해당 타입의 모든 빈 조회
- 상속 관계에서 부모 타입으로 조회하면, 자식 타입도 함께 조회된다.
  - 그래서 중복 오류(NoUniqueBeanDefinitionException)가 발생할 수 있으니 주의
  - 자바 객체의 최상위인 Object 타입으로 조회 시, 모든 스프링 빈을 조회한다.
</details>