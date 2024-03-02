package com.example.SpringBasicReview.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();    // 본인 스스로를 내부에 private로 생성. 외부에서 생성 불가

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }
    // private 으로 생성 막음

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
