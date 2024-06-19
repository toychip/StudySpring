package com.example.stock.transaction;

import com.example.stock.service.StockService;

public class TransactionStockService {
    private StockService stockService;

    public TransactionStockService(final StockService stockService) {
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity) {
        startTransaction();
        stockService.decrease(id, quantity);
        endTransaction();
    }

    private void startTransaction() {
        System.out.println("Transaction Start");
    }

    private void endTransaction() {
        System.out.println("Commit");
    }
}
