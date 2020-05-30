package org.huang.seata.account.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AccountConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
