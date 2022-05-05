package Cyber_Community.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Enzo Cotter on 03/05/2022.
 */
@Configuration
public class WebHandlerConfiguration implements WebMvcConfigurer {
    private WebHandlerInterceptor webHandlerInterceptor;
    public WebHandlerConfiguration(WebHandlerInterceptor webHandlerInterceptor){
        this.webHandlerInterceptor=webHandlerInterceptor;
    }


}
