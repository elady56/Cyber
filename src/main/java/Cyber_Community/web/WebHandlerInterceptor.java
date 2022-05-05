package Cyber_Community.web;

import Cyber_Community.services.JwtUitls;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Enzo Cotter on 03/05/2022.
 */
@Component
public class WebHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS") ){
            response.setStatus(HttpServletResponse.SC_OK);
            return  true;
        }
        response.setCharacterEncoding("utf-8");
        String token =request.getHeader("token");
        if(token!=null){
            int  result= JwtUitls.verify(token);
            if(result==1){
                System.out.println("Pass interceptor");
                return true;
            }
        }

        return false;
    }
}
