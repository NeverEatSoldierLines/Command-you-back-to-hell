//package cn.edu.jnu.config;
//
//import cn.edu.jnu.config.interceptor.LoginInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class LoginConfiguration implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器,普通用户的
////        LoginInterceptor loginInterceptor = new LoginInterceptor();
//        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
//        // 拦截路径
//        loginRegistry.addPathPatterns("/**");
//        // 排除路径,admin登录
//        loginRegistry.excludePathPatterns("/index","/ajaxAdminLogin","de_manage-user");
//        // 排除静态资源请求
//        loginRegistry.excludePathPatterns("/assets/**","/assets/bootstrap/**","/img/**","/layui/**","/profile-picture/**", "/assets/css/**","/assets/js/**","/assets/plugins/**","/assets/img/**","/bootstrap/**");
//        loginRegistry.excludePathPatterns("/fonts/**","/js/**","/manager/**","/css/**");
//    }
//}
//
//
