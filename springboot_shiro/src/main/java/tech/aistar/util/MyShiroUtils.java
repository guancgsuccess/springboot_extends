package tech.aistar.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import tech.aistar.entity.User;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
public class MyShiroUtils {

    /**
     *
     * @param password 页面获取的明文密码
     * @param salt 盐值
     * @return
     */
    public static String shiroMd5(String password,String salt){
        int hashIterations = 1;//加密的次数
        Object salt_str = salt;
        Object credentials = password;//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,salt_str,hashIterations);
        return simpleHash.toString();
    }

}
