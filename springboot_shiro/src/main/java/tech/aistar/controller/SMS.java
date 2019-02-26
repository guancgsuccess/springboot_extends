package tech.aistar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.aistar.util.SMSCode;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by Administrator on 2019/2/26 0026.
 */
@Controller
public class SMS {
    @GetMapping("/sms")
    public String sms(){
        return "juhe/sms";
    }

    @GetMapping("/code")
    @ResponseBody
    public String code(String phoneNumber, HttpSession session){
        //生成一个6位0~9之间的随机字符串
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(10));
        }
        try {
            if(!SMSCode.sendCode(phoneNumber, buffer.toString())) {
                return "验证码发送失败";
            } else {
                //将验证码、手机号码和当前的系统时间存储到session中
               session.setAttribute("code", buffer.toString());
               session.setAttribute("number", phoneNumber );
               //session.setAttribute("time", System.currentTimeMillis());
               // out.println("验证码发送成功！");
                return "验证码发送成功！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no";
    }

    @PostMapping
    @RequestMapping("/sms/logincl")
    public String logincl(String phoneNumber,String code,HttpSession session){
        //从session中获取
        String phone = (String) session.getAttribute("number");
        String check_code = (String) session.getAttribute("code");

        //清除session中的数据
        session.removeAttribute("code");
        session.removeAttribute("number");

        if(phone.equals(phoneNumber) && check_code.equals(code)){
            return "success";
        }else{
            return "error/4xx";
        }
    }
}
