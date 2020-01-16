package com.ssm.crm.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import java.util.Properties;
import java.util.Random;

/** kaptcha验证码
 * 验证码工具
 */
public class CaptchaUtil {

    private static CaptchaUtil instance;
    private int _width = 250;// 验证码宽度
    private int _height = 90;// 验证码高度
    private int _length = 4;// 验证码长度

    public static CaptchaUtil getInstance(){
        if (instance == null){
            instance = new CaptchaUtil();
        }
        return instance;
    }

    /**
     * 设置长度
     * @param length
     * @return
     */
    public CaptchaUtil length(int length){
        this._length = length;
        return this;
    }

    /**
     * 设置宽度
     * @param width
     * @return
     */
    public CaptchaUtil width(int width){
        this._width = width;
        return this;
    }

    /**
     * 设置高度
     * @param height
     * @return
     */
    public CaptchaUtil height(int height){
        this._height = height;
        return this;
    }

    /**
     * 参数设置并返回 DefaultKaptcha 对象
     * @return
     */
    public DefaultKaptcha getDefaultKaptcha(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border","no");
        properties.setProperty("kaptcha.border.color","105,179,90,0");
        properties.setProperty("kaptcha.image.width",String.valueOf(this._width));
        properties.setProperty("kaptcha.image.height",String.valueOf(this._height));
        properties.setProperty("kaptcha.textproducer.char.length",String.valueOf(this._length));
        properties.setProperty("kaptcha.session.key","code");
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");

        Random random = new Random();
        //properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
        //properties.setProperty("kaptcha.obscurificator.impl",style[random.nextInt(style.length)]);
        properties.setProperty("kaptcha.textproducer.font.size",String.valueOf(this._height - random.nextInt(10)));
        properties.setProperty("kaptcha.textproducer.font.color",getColor());

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        // String code = defaultKaptcha.createText();
        // BufferedImage image = defaultKaptcha.createImage(code);
        return defaultKaptcha;
    }

    /**
     * 获取颜色
     * @return
     */
    private String getColor(){
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return r + "," + g + "," + b;
    }
}
