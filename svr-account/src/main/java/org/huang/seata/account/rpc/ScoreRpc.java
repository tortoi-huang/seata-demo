package org.huang.seata.account.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ScoreRpc {
    private static final String SERVICE = "svr-score";

    private final LoadBalancerClient loadBalancer;
    private final RestTemplate restTemplate;

    public ScoreRpc(LoadBalancerClient loadBalancer, RestTemplate restTemplate) {
        this.loadBalancer = loadBalancer;
        this.restTemplate = restTemplate;
    }

    public Integer updateScoreById(long id, long score) throws Exception {
        log.info("== request param: id={}, account={}", id, score);
        final String url = loadBalancer.choose(SERVICE).getUri().toString();
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("score", score);
        HttpEntity entity = new HttpEntity(param, null);

        Map<String, Object> urlVars = new HashMap<>(2);
        urlVars.put("id", id);
        ResponseEntity<Integer> exchange = restTemplate.exchange(url + "/updateScoreById/{id}", HttpMethod.PUT, entity, Integer.class, urlVars);
        if (HttpStatus.OK == exchange.getStatusCode()) {
            log.info("== updateScoreById rpc call result: {}", exchange.getBody());
            return exchange.getBody();
        }
        if (HttpStatus.INTERNAL_SERVER_ERROR == exchange.getStatusCode()) {
            if (exchange.getBody() != null && -1 == exchange.getBody()) {
                log.info("== updateScoreById rpc call result: {}", exchange.getBody());
                throw new RuntimeException("updateStockById runtime exception");
            }
        }
        log.info("== updateScoreById rpc call result: {}", exchange.getBody());
        throw new Exception("updateStockById exception");
    }
}
