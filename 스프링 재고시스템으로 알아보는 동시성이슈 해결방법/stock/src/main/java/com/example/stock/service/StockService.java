package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    // stockId 감소시킬 상품의 stockId
    // 감소 개수
    @Transactional
    public void decrease(Long stockId, Long quantity) {
        // Stock 조회
        // 재고를 감소시킨뒤
        // 갱신된 값을 저장하도록 함

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("재고를 찾을 수 없음"));
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
