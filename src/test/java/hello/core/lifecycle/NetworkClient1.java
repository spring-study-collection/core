package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//인터페이스 (InitializingBean, DisposableBean)
public class NetworkClient1 implements InitializingBean, DisposableBean {

    private String url; //접속할 서버 주소

    public NetworkClient1() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception { //초기화 콜백
        System.out.println("NetworkClient1.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception { //소멸전 콜백
        System.out.println("NetworkClient1.destroy");
        disconnect();
    }
}
