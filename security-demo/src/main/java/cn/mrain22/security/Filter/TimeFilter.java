package cn.mrain22.security.Filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------------------->TimeFilter is init!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("--------------------->TimeFilter is start!");
        Date date = new Date();
        long start = date.getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        Date endDate = new Date();
        long end = endDate.getTime();
        System.out.println("--------------------->TimeFilter is finish!耗时"+ (end - start));

    }

    @Override
    public void destroy() {
        System.out.println("--------------------->TimeFilter is destroy!");

    }
}
