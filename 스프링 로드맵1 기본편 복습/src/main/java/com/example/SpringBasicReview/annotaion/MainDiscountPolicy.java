package com.example.SpringBasicReview.annotaion;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented

@Qualifier("mainDiscountPolicy")    // 애노테이션을 직접 만들어서 스프링 빈이 mainDiscountPlicy를 찾게함
public @interface MainDiscountPolicy {
}
