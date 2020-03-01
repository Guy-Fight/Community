package love.xuqinqin.community.Config;

import love.xuqinqin.community.component.CookieInterceptor;
import love.xuqinqin.community.component.IndexInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author FGuy
 * @Date 2020/2/26 19:30
 */
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/publish").setViewName("publish");
//        registry.addViewController("/publish.html").setViewName("publish");
    }

    @Bean
    public HandlerInterceptor getCookieInterceptor(){
        return new CookieInterceptor();
    }

    @Bean
    public HandlerInterceptor getIndexInterceptor(){
        return new IndexInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> paths = new ArrayList<String>();
//        paths.add("/");
//        paths.add("/index");
//        paths.add("/index.html");
//        paths.add("/asserts/**");
//        paths.add("/webjars/**");
//        paths.add("/callback");
        registry.addInterceptor(getCookieInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(getIndexInterceptor()).addPathPatterns("/**").excludePathPatterns(paths);
    }
}
