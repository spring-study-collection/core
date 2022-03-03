package hello.core.lifecycle;

//설정 정보(LifeCycleConfig)에 초기화 메서드, 종료 메서드 지정
public class NetworkClient2 {

    private String url; //접속할 서버 주소

    public NetworkClient2() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
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

    public void init() { //초기화 콜백
        System.out.println("NetworkClient2.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() { //소멸전 콜백
        System.out.println("NetworkClient2.close");
        disconnect();
    }
}
