package tech.aistar.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**https://blog.csdn.net/qq_34346915/article/details/80228711 - ehcash
 * Created by Administrator on 2019/2/22 0022.
 *
 * https://blog.csdn.net/weixin_42151186/article/details/82725070 - 聚合数据手机验证码
 *
 * https://blog.csdn.net/qq_24708791/article/details/78521396 - 阿里云手机验证
 *
 * http://www.cppcns.com/ruanjian/java/200058.html - 阿里云
 *
 * https://blog.csdn.net/qq_40914991/article/details/80584843 - 支付宝沙箱环境
 */
@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，
     * 使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     * shiro内置过滤器,可以实现权限相关的拦截器
     *  常用的过滤器:
     *      anon:无需认证(登录)可以访问
     *      authc:必须认证才可以访问
     *      user:入股欧使用rememberMe的功能可以直接访问
     *      perms:该资源必须得到资源权限才可以访问
     *      role:该资源必须得到角色权限才可以访问
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

       // filterChainDefinitionMap.put("/add","authc");

      //  filterChainDefinitionMap.put("/update","anon");

        //静态资源文件
        filterChainDefinitionMap.put("/asserts/**","anon");

        //放行的路径
        filterChainDefinitionMap.put("/login","anon");

        filterChainDefinitionMap.put("/gotoWapPage","anon");
        filterChainDefinitionMap.put("/gotoPagePay","anon");
        filterChainDefinitionMap.put("/alipay/page/returnUrl","anon");
        filterChainDefinitionMap.put("/alipay/page/returnUrl","anon");


        filterChainDefinitionMap.put("/index","anon");
        filterChainDefinitionMap.put("/logincl","anon");
        filterChainDefinitionMap.put("/","anon");

        //配置某个url需要某个权限码
        //filterChainDefinitionMap.put("/add","perms[user:add]");
        //filterChainDefinitionMap.put("/update","perms[user:update]");

        filterChainDefinitionMap.put("/add","authc,roles[admin]");
        filterChainDefinitionMap.put("/update","authc,roles[user]");
        filterChainDefinitionMap.put("/select","authc,roles[shop]");

        //安全退出
        //filterChainDefinitionMap.put("/loginout","logout");

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");



        //此句代码一定要放在最下面,这里是一个坑.切记!!!
        //屏蔽所有用户
        filterChainDefinitionMap.put("/**","authc");
//
        //如果被拦截跳转到/login对应的html
        shiroFilterFactoryBean.setLoginUrl("/login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;

    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();

        securityManager.setRealm(userShiroRealm());

        //配置ehcache
        securityManager.setCacheManager(ehCacheManager());

        //注入記住我
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public UserShiroRealm userShiroRealm(){
        UserShiroRealm userShiroRealm = new UserShiroRealm();
        //配置加密(在加密后,不配置的话会导致登录密码失败)
        userShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        //关于shiro+ehcache
        userShiroRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        userShiroRealm.setAuthenticationCachingEnabled(true);
        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        userShiroRealm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        userShiroRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        userShiroRealm.setAuthorizationCacheName("authorizationCache");

        return userShiroRealm;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 告诉shiro是经过加密的
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //使用md5算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数:意外加密几次
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return cacheManager;
    }

    /**  * cookie对象;  * rememberMeCookie()方法是设置Cookie的生成模版，
     * 比如cookie的name，cookie的有效时间等等。  * @return */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     17   * cookie管理对象;
     18   * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     19   * @return
     20  */
     @Bean
     public CookieRememberMeManager rememberMeManager() {
         //System.out.println("ShiroConfiguration.rememberMeManager()");
         CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
         cookieRememberMeManager.setCookie(rememberMeCookie());
         //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
         cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
         return cookieRememberMeManager;
     }
}
