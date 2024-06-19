package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    /*
     stockId 감소시킬 상품의 stockId
     감소 개수
     Stock 조회
     재고를 감소시킨뒤
     갱신된 값을 저장하도록 함
     */

    /*
    synchronized를 써도 의미 없음
    Proxy 객체를 생성하여 메서드 종료시에, 트랜잭션 커밋을 실행하지만, 다른 스레드가 decrease 메서드를 호출할 수 있기 때문
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void decrease(Long stockId, Long quantity) {

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("재고를 찾을 수 없음"));
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
