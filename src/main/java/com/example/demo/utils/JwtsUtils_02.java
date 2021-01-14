package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtsUtils_02 {
    /**
     * 过期时间15分钟
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    /**
     * token密钥
     */
    private static final String TOKEN_SECRET = "cg971024cheyinbo";


    public static String getToken(User user) {
        try{
            //获取过期时间对象
            Date expire_time = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //密钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            //附带username,userId信息,生成签名
            return JWT.create()
                    //头信息
                    .withHeader(header)
                    //消息体信息
                    .withClaim("userName",user.getName())
                    .withClaim("userId",user.getId())
                    //过期时间
                    .withExpiresAt(expire_time)
                    //签名
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }

    }

    public static boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static String getTokenUserId() {
        String token = getRequest().getHeader("accessToken");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }



}
