package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
// 초반에만 사용하였고 현재 이 방법은 사용하지 않는다.
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    //service 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);

    }

    public void call (String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //service 종료시 호출
    public void disconnect(){
        System.out.println("close " + url);
    }

        //afterPropertiesSet   -> 의존 관계 주입이 끝나면 호출해주겠다
    @PostConstruct
    public void init(){
        connect();
        call("초기화 연결 메시지");
        System.out.println("NetworkClient.init");
    }

         //bean이 종료될때 호출된다.
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();

    }


}
