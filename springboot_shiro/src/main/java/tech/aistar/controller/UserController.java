package tech.aistar.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.aistar.config.UserShiroRealm;
import tech.aistar.entity.User;
import tech.aistar.service.IUserService;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/403")
    public String error(){
        return "error/4xx.html";
    }

    @RequestMapping("/select")
    public String select(){
        return "user/select";
    }

    @GetMapping("/loginout")
    public String logout(){
        //清除缓存
        Subject subject = SecurityUtils.getSubject();

        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        UserShiroRealm shiroRealm = (UserShiroRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();

        subject.logout();

        return "redirect:/";
    }

    /**
     * 登录处理
     * @return
     */
    @PostMapping("/logincl")
    public String logincl(User user, Model model,boolean rememberMe){
        //从SecurityUtils中创建一个subject
        Subject subject = SecurityUtils.getSubject();
        //在认证提交前准备token(令牌)
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword(),rememberMe);
        //执行认证登录
        try {
            subject.login(token);
            //登录成功
            return "success";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            model.addAttribute("msg","用户名不存在!");
            return "user/login";
        }catch(IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "user/login";
        }
    }

}
