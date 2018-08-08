package cn.mrain22.security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/user")
    public String getUser(@RequestParam(required = false) String username){
        return "hello "+username+"!";
    }
}
