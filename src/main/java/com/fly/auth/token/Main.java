package com.fly.auth.token;

import com.fly.service.SystemLogMessageService;
import com.fly.service.SystemService;

import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        SystemService systemService=new SystemService();
        Token token = new Token();
        String str=token.createToken(systemService.queryAll(),30*60*60*1000);
        System.out.println(str);
    }
}
