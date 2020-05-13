package com.gradle.service.impl;

import com.gradle.service.IService;
import com.gradle.service.IServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class MockService implements IService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IServiceB iServiceB;

    @Override
    public String exchange(String id) {

        String url = "http://localhost:8080/test/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        String sb = iServiceB.sb();
        //设置访问参数
        HashMap<String, Object> params = new HashMap<>();

        //设置访问的Entity
        HttpEntity entity = new HttpEntity<>(params, headers);
        ResponseEntity<String> result = null;
        result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return result.getBody()+":"+sb;
    }
}
