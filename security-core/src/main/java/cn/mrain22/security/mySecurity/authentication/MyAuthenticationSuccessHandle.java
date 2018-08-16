package cn.mrain22.security.mySecurity.authentication;

import cn.mrain22.security.mySecurity.Properties.LoginType;
import cn.mrain22.security.mySecurity.Properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("marinAuthenticationSuccessHandle")
public class MyAuthenticationSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler { //自定义的

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功！");
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){ //如果是Json格式
            //        要做的工作就是将Authentication以json的形式返回给前端。 需要工具类ObjectMapper，Spring已自动注入。
            httpServletResponse.setContentType("application/json;charset=UTF-8");  //设置返回类型
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));  //将Authentication写入

        }else { //调用父类的的方法跳转。
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }


    }
}
