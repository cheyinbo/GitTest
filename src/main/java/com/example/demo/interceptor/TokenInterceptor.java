package com.example.demo.interceptor;

import com.example.demo.utils.JwtsUtils_02;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.addHeader("Set-Cookie", "uid=112; Path=/; HttpOnly");
        String token = request.getHeader("accessToken");
        System.out.println("token="+token);
        if(token != null){
            boolean result = JwtsUtils_02.verify(token);
            if(result){
                return true;
            }
        }
        System.out.println("token不正确");
        returnJson(response,"{\"code\":400,\"msg\":\"token不正确!\"}");
        return false;
    }

    /*返回客户端数据*/
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
