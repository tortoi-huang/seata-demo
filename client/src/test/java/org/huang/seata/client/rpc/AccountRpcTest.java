package org.huang.seata.client.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRpcTest {

    @Autowired
    private AccountRpc accountRpc;

    //@Test
    void updateAccountById() {
        try {
            Integer integer = accountRpc.updateAccountById(3, 100);
            assertEquals(1, integer.intValue());
            System.out.println("updateAccountById size: " + integer);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
