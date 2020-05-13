package com.gradle.service;


import com.gradle.service.IService;
import com.gradle.service.impl.IServiceBImpl;
import com.gradle.service.impl.MockService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IServiceTest {


    @InjectMocks  IService iService = new MockService();

    @Mock
    RestTemplate restTemplate;

    @Mock
    IServiceBImpl iServiceB ;

    @Before
    public void setUp() {
        iService = new MockService();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void exchange(){

        String url = "http://localhost:8080/test/2";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        when(iServiceB.sb()).thenCallRealMethod();

        //设置访问参数
        HashMap<String, Object> params = new HashMap<>();

        //设置访问的Entity
        HttpEntity entity = new HttpEntity<>(params, headers);
        ResponseEntity result= new ResponseEntity<>("mock body",HttpStatus.OK);

        when(restTemplate.exchange(Mockito.anyString(),Mockito.<HttpMethod> any(), Mockito.<HttpEntity<?>> any(),Mockito.<Class<?>> any())).thenReturn(result);
        String realBody = restTemplate.exchange("",HttpMethod.GET, entity,String.class).getBody();
        String mockbody = iService.exchange("2");

        Assert.assertEquals("mock body",mockbody);
    }
}
