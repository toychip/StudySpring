package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.domain.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
