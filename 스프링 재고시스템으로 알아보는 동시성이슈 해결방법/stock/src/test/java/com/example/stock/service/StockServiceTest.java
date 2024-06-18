package com.example.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void before() {
        stockRepository.saveAndFlush(new Stock(1L, 100L));
    }

    @AfterEach
    public void after() {
        stockRepository.deleteAll();
    }

    @Test
    public void 재고감소() {
        stockService.decrease(1L, 1L);

        // 100 - 1 = 99
        Stock stock = stockRepository.findById(1L).orElseThrow();

        assertEquals(99, stock.getQuantity());
    }

    @Test
    public void 동시에_100개_요청() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        /**
         * 고정된 크기의 쓰레드 풀을 생성합니다.
         * 32개의 쓰레드를 갖는 풀을 사용하여 작업을 실행합니다.
         * 이는 한 번에 32개의 쓰레드가 실행될 수 있음을 의미합니다.
         */
        CountDownLatch latch = new CountDownLatch(threadCount);
        /**
         * CountDownLatch는 비동기적으로 실행되는 여러 쓰레드가 모두 완료될 때까지 기다리게 하는 도구입니다.
         * 100개의 쓰레드가 모두 완료될 때까지 대기하도록 설정합니다.
         */

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try{
                    stockService.decrease(1L, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();
        // 100 - (1 * 100) == 0
        assertEquals(stock.getQuantity(), 0);

        // 레이스 컨디션이 일어났기 때문
        // 레이스 컨디션? 둘 이상의 쓰레드가 공유된 자원에 접근시에 나타나는 상황
        // 서로 다른 스레드가 같은 값을 갱신시키려하기 때문에 발생하므로 이를 한 스레드가 작업이 끝나면 하도록 수정하면 됨
    }
}