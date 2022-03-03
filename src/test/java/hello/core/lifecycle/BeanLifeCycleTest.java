package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LiftCycleConfig.class);

        NetworkClient3 client = ac.getBean(NetworkClient3.class);
        ac.close();
    }

    @Configuration
    static class LiftCycleConfig {

//        @Bean
//        public NetworkClient1 networkClient() {
//            NetworkClient1 networkClient = new NetworkClient1();
//            networkClient.setUrl("http://hello-spring.dev");
//            return networkClient;
//        }

//        @Bean(initMethod = "init", destroyMethod = "close")
//        public NetworkClient2 networkClient() {
//            NetworkClient2 networkClient = new NetworkClient2();
//            networkClient.setUrl("http://hello-spring.dev");
//            return networkClient;
//        }

        @Bean
        public NetworkClient3 networkClient() {
            NetworkClient3 networkClient = new NetworkClient3();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
