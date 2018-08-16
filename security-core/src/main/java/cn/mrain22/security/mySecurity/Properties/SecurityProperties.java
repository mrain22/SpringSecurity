package cn.mrain22.security.mySecurity.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mrain22.security")  //读取以mrain22.security的配置项
public class SecurityProperties {  //用来封装所有的配置

    //mrain22.security.browser
    private BrowserProperties browser = new BrowserProperties();



    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
