package com.example.SpringBasicReview.controller;

import com.example.SpringBasicReview.common.Logger;
import com.example.SpringBasicReview.service.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<Logger> loggerProvider;
//     Logger를 주입받는 것이 아닌 Logger를 주입하기 위해 찾는 Lookup이 주입 됨

    @GetMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        Logger logger = loggerProvider.getObject();
        String requestURL = request.getRequestURL().toString();
        logger.setRequestURL(requestURL);

        logger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
