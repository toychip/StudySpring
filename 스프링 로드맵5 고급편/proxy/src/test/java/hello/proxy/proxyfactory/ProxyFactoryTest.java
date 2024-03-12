package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();

        // AopUtils 는 ProxyFactory를 통해서 만들었어야만 가능함

        boolean isProxy = AopUtils.isAopProxy(proxy);
        Assertions.assertThat(isProxy).isTrue();

        boolean isJdkProxy = AopUtils.isJdkDynamicProxy(proxy);
        Assertions.assertThat(isJdkProxy).isTrue();

        boolean isCglibProxy = AopUtils.isCglibProxy(proxy);
        Assertions.assertThat(isCglibProxy).isFalse();

    }
}
