package tech.aistar.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.aistar.entity.User;
import tech.aistar.mapper.UserMapper;
import tech.aistar.util.MyShiroUtils;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindByUserName(){
        System.out.println(userMapper.findByUserName("admin"));
    }

    @Test
    public void testPwd(){
        /**	 * 明文密码进行加密	 * @param args	 */


            int hashIterations = 1;//加密的次数
             Object salt = "tom";//盐值
         Object credentials = "123456";//密码
         	String hashAlgorithmName = "MD5";//加密方式
//         Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
         		//	salt, hashIterations);
         Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,salt,hashIterations);
         System.out.println("加密后的值----->" + simpleHash);

    }

    @Test
    public void testSave(){
//
//        int hashIterations = 1;//加密的次数
//        Object salt = "tom";//盐值
//        Object credentials = "123456";//密码
//        String hashAlgorithmName = "MD5";//加密方式
////         Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
//        //	salt, hashIterations);
//        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,salt,hashIterations);
//        System.out.println("加密后的值----->" + simpleHash);

        //获取md5+盐的加密字符串
        //String simpleHash = MyShiroUtils.shiroMd5("123456","tom");

       // User u = new User("tom",simpleHash.toString(),"user:add");
        //userMapper.save(u);

        String simpleHash = MyShiroUtils.shiroMd5("123456","success");

        User u1 = new User("success",simpleHash,"user:add");

        String simpleHash2 = MyShiroUtils.shiroMd5("admin","admin");

        User u2 = new User("admin",simpleHash2,"user:update");

        String simpleHash3 = MyShiroUtils.shiroMd5("tom","tom");

        User u3 = new User("tom",simpleHash3,"user:select");

        userMapper.save(u1);
        userMapper.save(u2);
        userMapper.save(u3);
    }

}
