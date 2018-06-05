package com.fly.auth.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Token {

    private static final String SECRET="secret";
    private  Gson gson = new Gson();

    private Map createHeader(){
        HashMap hashMap=new HashMap();
        hashMap.put("type","jwk");
        hashMap.put("alg","HS256");
        return  hashMap;
    }

    public <T> String createToken(Object t,long maxTimeWait) throws UnsupportedEncodingException {
        JWTCreator.Builder builder =JWT.create();
        builder.withHeader(createHeader());
        builder.withSubject(gson.toJson(t));
        if(maxTimeWait>1){

            Long startTime = System.currentTimeMillis();
            Long endTime = startTime + maxTimeWait;
            Date date = new Date(endTime);
            builder.withExpiresAt(date);

        }

        return  builder.sign(Algorithm.HMAC256(SECRET));

    }

    public <T> T unCreateToken(Class<T> tClass,String token) throws UnsupportedEncodingException {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        Date date = decodedJWT.getExpiresAt();
        if(date!=null && date.after(new Date())){

            String subject = decodedJWT.getSubject();
            return gson.fromJson(subject,tClass);
        }
        return null;
    }
}
