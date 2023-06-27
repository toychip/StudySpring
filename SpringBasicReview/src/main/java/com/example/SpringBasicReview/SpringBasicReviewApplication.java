package com.example.SpringBasicReview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// SpringBoot가 이미 @ComponentScan 을 사용하므로, 직접 쓸 필요가 없음. @Component만 사용하면 됨.
public class SpringBasicReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicReviewApplication.class, args);
	}

}
