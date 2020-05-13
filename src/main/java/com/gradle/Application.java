package com.gradle;


import com.gradle.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@SpringBootApplication
@EnableScheduling
public class Application {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private IService iService;

    @ResponseBody
    @RequestMapping("/test/{id}")
    public String home(@PathVariable ("id") String id){
        return "Hello "+id+"! This is Spring-boot test One";
    }

    @ResponseBody
    @RequestMapping("/mock/{id}")
    public String mock(@PathVariable ("id") String id){
        return iService.exchange(id);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }


}
