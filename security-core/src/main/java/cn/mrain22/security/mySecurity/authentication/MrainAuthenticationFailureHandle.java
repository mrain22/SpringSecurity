package cn.mrain22.security.mySecurity.authentication;

import cn.mrain22.security.Support.SimpleResponse;
import cn.mrain22.security.mySecurity.Properties.LoginType;
import cn.mrain22.security.mySecurity.Properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component("mrainAuthenticationFailureHandle")
public class MrainAuthenticationFailureHandle extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        logger.info("登录失败！");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) { //如果是Json格式
            //        将信息以json形式返回
            httpServletResponse.setStatus(500);  //服务器内部异常
            httpServletResponse.setContentType("application/json;charset=UTF-8");  //设置返回类型
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage()) ));  //将错误信息写入
        }else {
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }



    }
}
