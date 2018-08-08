package cn.mrain22.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = cn.mrain22.security.SecurityDemoApplication.class)
public class SecurityDemoApplicationTests {
    //    伪造web环境
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;//伪造的mvc环境

    @Before //每次测试用例启动前启动
    public  void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void  QuerySucess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user") //请求路径及请求方法
                .param("username", "mrain") //请求参数
                .contentType(MediaType.APPLICATION_JSON_UTF8)) //请求的类型
                .andExpect(MockMvcResultMatchers.status().isOk()) //期望返回的状态
                .andReturn().getResponse().getContentAsString(); //接受返回的结果
        System.out.println(result);//打印返回的结果
    }

}
