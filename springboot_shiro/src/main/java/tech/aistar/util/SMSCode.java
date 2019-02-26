package tech.aistar.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 调用短信接口类
 */
public class SMSCode {
    //把手机号码和随机验证码传递过来
    public static boolean sendCode(String phoneNumber, String code) throws Exception {

        String str_code = URLEncoder.encode("#code#=" + code, "UTF-8");
        //包装好URL对象，将接口地址包装在此对象中
        URL url = new URL("http://v.juhe.cn/sms/send?mobile=" + phoneNumber +
                "&tpl_id=128251&tpl_value=" + str_code + "&key=b1cbc085bea6c9d71ede3d8c79ee3b77");
                /* 短信模板id */                            /* 短信应用接口的key */
        //打开连接，得到连接对象
        URLConnection connection = url.openConnection();
        //向服务器发送连接请求
        connection.connect();
        //获得服务器响应的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String lineDate = null;
        while((lineDate = bufferedReader.readLine()) != null) {
            buffer.append(lineDate);
        }
        bufferedReader.close();
        if(buffer.toString().indexOf("\"error_code\":0")>=0 ) {
            return true;
        }
        return false;
    }
}
