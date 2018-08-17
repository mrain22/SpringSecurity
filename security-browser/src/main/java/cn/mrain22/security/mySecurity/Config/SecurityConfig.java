package cn.mrain22.security.mySecurity.Config;

import cn.mrain22.security.mySecurity.Properties.SecurityProperties;
import cn.mrain22.security.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    //将自己写的登录成功处理器引入。
    @Autowired
    private AuthenticationSuccessHandler marinAuthenticationSuccessHandle;
    //将自己写的登录失败处理器引入。
    @Autowired
    private AuthenticationFailureHandler mrainAuthenticationFailureHandle;

//

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        自己写的过滤器
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(mrainAuthenticationFailureHandle);
        validateCodeFilter.setSecurityProperties(securityProperties);

        http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication")
                .loginProcessingUrl("/login")
//                使用自己的登录成功和失败处理器
                .successHandler(marinAuthenticationSuccessHandle)
                .failureHandler(mrainAuthenticationFailureHandle)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getLoginPage(),"/validate/defaultKaptcha").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

//     密码加盐加密
    @Bean
    public PasswordEncoder passwordEncoder (){
        //Spring自带的每次会随机生成盐值，即使密码相同，加密后也不同
        return  new BCryptPasswordEncoder();
    }
}
