package cn.mrain22.security.validate;

import cn.mrain22.security.mySecurity.Properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateCodeFilter extends OncePerRequestFilter {  //自己实现的security过滤器，用于验证码验证 // OncePerRequestFilter是由Spring提供的工具类，保证每次执行只调用一次该过滤器

    private AuthenticationFailureHandler authenticationFailureHandler;
//    配置
    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //    获取所有的urls
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidateCode().getImage().getUrl(),",");

        boolean isTrue = false;
        for (String configUrl : configUrls){
            if(StringUtils.equals(configUrl,httpServletRequest.getRequestURI())){
                isTrue = true;
            }
        }
        //判断是不是登录请求，
        if(isTrue){
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
//                如果捕获到异常用AuthenticationFailureHandler处理，将信息返回给前端。
//                之前我们已经自己写过自定义的AuthenticationFailureHandler，该处抛给自定义的处理
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
//        如果不是，调用后面的过滤器
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
//        获取session的值
        String kaptchaSession = (String) request.getRequest().getSession().getAttribute("kaptchaCode");
//        获取前台传过来的验证码
        String requestCode = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        System.out.println(kaptchaSession + "----------------" + requestCode);
//        清理验证码session
        request.getRequest().getSession().removeAttribute("kaptchaCode");
        if (StringUtils.isBlank(requestCode)){
            throw new ValidateCodeException("验证码不能为空");
        }
        if (StringUtils.isBlank(kaptchaSession)){
            throw new ValidateCodeException("验证码不存在");
        }
        if (!(StringUtils.equalsIgnoreCase(kaptchaSession,requestCode))){
            throw new ValidateCodeException("验证码错误");
        }
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
