package com.gradle.service.impl;

import com.gradle.service.IServiceB;
import org.springframework.stereotype.Component;

@Component
public class IServiceBImpl implements IServiceB {


    @Override
    public String sb() {
        return "hah";
    }
}
