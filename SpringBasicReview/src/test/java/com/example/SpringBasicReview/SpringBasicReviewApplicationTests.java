package com.example.SpringBasicReview;

import com.example.SpringBasicReview.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBasicReviewApplicationTests {

	@Autowired
	OrderService orderService;	// 이런 식으로 테스트에서는 @Autowired 사용해도 괜찮음.
	@Test
	void contextLoads() {
	}

}
