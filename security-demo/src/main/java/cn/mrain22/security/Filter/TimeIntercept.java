package cn.mrain22.security.Filter;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
//@Component
public class TimeIntercept implements HandlerInterceptor {

    //    开始
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("preHandle");

        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        Date date = new Date();
//        将开始时间放到request
        request.setAttribute("startTime", date.getTime());
        return true;
    }
//  结束
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
//        取出开始时间
        Long start = (Long) request.getAttribute("startTime");
        Date date = new Date();
        System.out.println("time interceptor 耗时:"+ (date.getTime() - start));

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long) request.getAttribute("startTime");
        Date date = new Date();
        System.out.println("time interceptor 耗时:"+ (date.getTime() - start));
        System.out.println("ex is "+ex);

    }

}
