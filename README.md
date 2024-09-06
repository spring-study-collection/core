<!--
<details>
<summary>소제목</summary>

- 내용
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

<details>
<summary>싱글톤 컨테이너</summary>

- 싱글톤 패턴은 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
- private 생성자를 사용해서 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
- 이 패턴을 구현하면 DIP, OCP 위반, 자식 클래스 생성 x, 테스트 어려움 등 여러 문제를 가진다.
- 스프링 컨테이너는 싱글턴 패턴의 모든 단점을 해결하면서 객체 인스턴스를 싱글톤으로 유지한다.
- @Bean 이 붙은 메서드마다 스프링 빈이 존재하면 존재하는 빈을 반환, 없으면 생성 & 등록 후 반환하는 코드가 동적으로 만들어진다. (CGLIB 기술)
- 하지만 @Bean 만 사용하면 싱글톤을 보장하지 않는다. @Configuration 도 같이 사용해야 한다.
  - @Configuration 을 적용하지 않으면 CGLIB 기술 없이 순수한 빈을 등록한다.
</details>

<details>
<summary>싱글톤 방식의 주의점</summary>

- 싱글톤 방식은 하나의 객체 인스턴스를 공유하기 때문에 무상태로 설계해야 한다.
  - 가급적 읽기만 가능하도록
  - 특정 클라이언트가 값을 변경할 수 있는 필드가 없도록
  - 필드 대신 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용
</details>

<details>
<summary>컴포넌트 스캔</summary>

- 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔(@ComponentScan) 이라는 기능을 제공한다.
- @ComponentScan 은 @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
- 탐색할 패키지의 시작 위치를 지정할 수 있다.
  - 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
    ```java
    @ComponentScan(
      basePackages = {"package1.core", "package2.service"},
      basePackageClasses = AppConfig.class,
    )
      ```
</details>

<details>
<summary>의존관계 자동 주입</summary>

- 의존관계 주입 방법에는 4가지 방법이 있다.
  - 생성자 주입: 호출 시점에 딱 1번만 호출되는 것을 보장한다. 불변한 설계가 가능하다.
    - 생성자가 딱 1개만 있으면 @Autowired 를 생략해도 자동 주입된다.
  - 수정자 주입(setter 주입): 가끔 옵션이 필요할 경우에만 사용
  - 필드 주입: 쓰지 말자!, 필드 injection 할 때 setter를 만들어줘야 한다.
  - 일반 메서드 주입: 잘 사용 x
- 스프링은 의존관계를 자동으로 주입하는 @Autowired 기능도 제공한다.
- @Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다.
  - 주입할 대상이 없어도 동작하게 하는 방법
    - @Autowired(required = false): 없으면 메서드 호출 자체가 안된다.
    - @Nullable Member member: 없으면 null이 입력된다.
    - Optional<Member> member: 없으면 Optional.empty가 입력된다.
- 의존관계 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.
- lombok 라이브러리가 제공하는 @RequiredArgsConstructor 기능을 사용하면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
</details>

<details>
<summary>Autowired 매칭</summary>

- 조회 대상 빈이 2개 이상일 경우 해결 방법
  - @Autowired 필드명 매칭
    - 먼저 타입 매칭을 시도하고, 결과가 2개 이상이면 필드명(파라미터명)으로 빈 이름 매칭한다.
  - @Qulifier 사용
    - 추가 구분자를 붙여주는 방법
    - 주입 시에 @Qulifier("등록한 이름")을 적어준다.
    - @Qulifier 끼리 매칭을 시도 -> 빈 이름 매칭 시도 -> 없으면 예외 발생
  - @Primary 사용
    - 우선순위를 정하는 방법
- @Qulifier 가 @Primary 보다 우선권이 더 높다.
  - 스프링은 (수동 > 자동, 좁은 범위의 선택권 > 넓은 범위의 선택권) 우선순위를 가진다.
</details>