package cn.mrain22.security.mySecurity.Config;

import cn.mrain22.security.mySecurity.Properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class) //使其生效
public class SecurityCoreConfig {
}
