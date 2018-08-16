package cn.mrain22.security.Controller;

import cn.mrain22.security.mySecurity.Properties.SecurityProperties;
import cn.mrain22.security.Support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MainController {
     //日志
    private Logger logger = LoggerFactory.getLogger(getClass());

    //请求的缓存，用来拿到引发跳转的请求。
    private RequestCache requestCache = new HttpSessionRequestCache();

    // spring的重定向策略，用来跳转页面
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/hello")
    public String hello(){
        return "hello Mrain!";
    }

//    处理需要身份验证的请求
//    当需要身份认证时跳转到这里
    @GetMapping("/authentication")
//    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) //返回的状态码
    public SimpleResponse authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        拿到之前引发跳转的的请求。
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
//                如果是html请求，跳转的指定页
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }else {
                response.setStatus(401);  //设置状态码
                return new SimpleResponse("访问的URL需要身份权限，请先登录！");
            }
        }
//        如果不是
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
        return new SimpleResponse("URL不正确");
    }

}
