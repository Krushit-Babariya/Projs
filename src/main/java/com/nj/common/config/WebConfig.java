//package com.nj.common.config;
//
//import com.nj.common.interceptor.RoleValidationInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private RoleValidationInterceptor roleValidationInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("RoleValidationInterceptor registered...");
//        registry.
//                addInterceptor(roleValidationInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login", "/logout", "/css/**", "/js/**");
//    }
//}
