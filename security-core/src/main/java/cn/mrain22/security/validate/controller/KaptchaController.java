package cn.mrain22.security.validate.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@RestController
public class KaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/validate/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        //生产验证码字符串并保存到session中
        String kaptchaCode = defaultKaptcha.createText();
        httpServletRequest.getSession().setAttribute("kaptchaCode", kaptchaCode);
        //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage kaptchaImage = defaultKaptcha.createImage(kaptchaCode);
        ImageIO.write(kaptchaImage, "JPEG", httpServletResponse.getOutputStream());

    }
}
