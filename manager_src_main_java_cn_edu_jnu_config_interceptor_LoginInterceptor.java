//package cn.edu.jnu.config.interceptor;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//    /**
//     * 在请求被处理之前调用
//     *
//     * @param request
//     * @param response
//     * @param handler
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
//        //在方法中编写拦截的业务代码，返回true就放行，返回false就抛异常。以下的两个方法是一样的。比如我在这里就写了一个拦截的例子
//        // 检查每个到来的请求对应的session域中是否有登录标识
//
//        String adminName = (String) request.getSession().getAttribute("adminName");
//        if (null == adminName) {
//            // 未登录，重定向到登录页
//            response.sendRedirect("index");
//            return false;
//        }
//        return true;
//
//
//    }
//}
//
//
