package com.ssm.crm.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ssm.crm.constart.UserConstart;
import com.ssm.crm.util.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CaptchaImageController {

    /**
     * 验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/captcha")
    @ResponseBody
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DefaultKaptcha defaultKaptcha = CaptchaUtil.getInstance().width(300).height(100).length(6).getDefaultKaptcha();
        String code = defaultKaptcha.createText();
        request.getSession(true).setAttribute(UserConstart.VERIFY_CODE,code);
        BufferedImage image = defaultKaptcha.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
