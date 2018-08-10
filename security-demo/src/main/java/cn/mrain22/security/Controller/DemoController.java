package cn.mrain22.security.Controller;

import cn.mrain22.security.Entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DemoController {

    @GetMapping("/user")
    public User getUser(@RequestParam(required = false) String username){
        User user = new User();
        user.setId(123456);
        user.setName("mrain");
        user.setPass("123456789");
//        user.setDate(new Date().getTime());
        return user;
    }
    @PostMapping("/user")
    public User postUser(@Valid @RequestBody User user, BindingResult errors){
            if (errors.hasErrors()){
                errors.getAllErrors().stream().forEach((err)->{
                    System.out.println(err.getDefaultMessage());
                });
            }
        System.out.println(user.getIdate());
        System.out.println("User=========>"+user);
        return user;
    }
}
