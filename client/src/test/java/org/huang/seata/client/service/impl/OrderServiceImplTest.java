package org.huang.seata.client.service.impl;

import org.huang.seata.client.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    //@Test
    public void update() {
        try {
            orderService.update(9, 123456);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}