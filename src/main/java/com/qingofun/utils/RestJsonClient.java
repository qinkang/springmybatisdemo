package com.qingofun.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by qinkang on 16/6/28.
 */
public class RestJsonClient {
    static RestTemplate restTemplate;
    static HttpHeaders headers = new HttpHeaders();
    static {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setExpires(1000*5);
        ClientHttpRequestFactory clientHttpRequestFactory = new MyCustomClientHttpRequestFactory();
        restTemplate = new RestTemplate(clientHttpRequestFactory);
    }

    public static <T,V> T postForEntity(V v, String url, Class<T> c) {
        HttpEntity<V> entity = new HttpEntity<V>(v, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url,entity,c);
        return responseEntity.getBody();
    }

    public static <T,V> T postForObject(V v,  String url, Class<T> c) {
        HttpEntity<V> entity = new HttpEntity<V>(v, headers);
        return restTemplate.postForObject(url, entity, c);
    }

    public static <T,V> T getForEntity(V v, String url, Class<T> c) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url,c,v);
        return responseEntity.getBody();
    }

    public static <T> T getForEntity(String url, Class<T> c, Map<String, Object> map) {
        ResponseEntity<T> responseEntity =  restTemplate.getForEntity(url, c, map);
        return responseEntity.getBody();
    }

    public static <T> T getForObject(String url, Class<T> c, Map<String, Object> map) {
        return restTemplate.getForObject(url, c, map);
    }

    public static <T> T getForObject(String url, Class<T> c) {
        return restTemplate.getForObject(url, c);
    }


}
