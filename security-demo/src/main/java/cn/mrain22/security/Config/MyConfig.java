package cn.mrain22.security.Config;

import cn.mrain22.security.Filter.TimeIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class MyConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TimeIntercept timeIntercept;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeIntercept);
    }
}
