package com.example.stock.facade;

import com.example.stock.repository.RedisLockRepository;
import com.example.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

    private RedisLockRepository repository;
    private StockService stockService;

    public LettuceLockStockFacade(final RedisLockRepository repository, final StockService stockService) {
        this.repository = repository;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        // lock 휙득 시도
        while (!repository.lock(id)) {
            // 부하를 줄이기 위해 텀을 둠
            Thread.sleep(100);
        }

        try{
            stockService.decrease(id, quantity);
        } finally {
            repository.unlock(id);
        }
    }
}
