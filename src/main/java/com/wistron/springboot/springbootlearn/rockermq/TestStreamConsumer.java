package com.wistron.springboot.springbootlearn.rockermq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * @description: spring cloud stream的测试类
 * @author: K21064736
 * @date: 2021/9/22 11:02
 */
@Service
@Slf4j
public class TestStreamConsumer {

    @Bean
    Consumer<String> send() {
        return str -> {
            log.info("收到消息, messageBody = {}", str);
            System.out.println("我是消费者，我收到了消息：" + str);
        };
    }

//    @Bean
//        //这里接收rabbitmq的条件是参数为Consumer 并且 方法名和supplier方法名相同
//        //这里的返回值是一个匿名函数 返回类型是consumer 类型和提供者的类型一致
//        //supplier发送的exchange是 send-in-0 这里只需要用send方法名即可
//    Consumer<String> send() {
//        log.info("我是消费者，我收到了消息：");
//        return str -> {
//            System.out.println("我是消费者，我收到了消息："+str);
//        };
//    }
}
