package com.example.SpringBasicReview.service;

import com.example.SpringBasicReview.common.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final Logger logger;
    public void logic(String id) {
        logger.log("service id = " + id);
    }
}
