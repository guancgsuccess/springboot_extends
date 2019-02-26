package tech.aistar.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import tech.aistar.entity.Permission;
import tech.aistar.entity.Role;
import tech.aistar.entity.User;
import tech.aistar.entity.UserVo;
import tech.aistar.service.IPermissionService;
import tech.aistar.service.IRoleService;
import tech.aistar.service.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
public class UserShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 执行用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("用户授权...");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        UserVo userVo = (UserVo) subject.getPrincipal();


        System.out.println(subject.getPrincipal()+"===");
        System.out.println(subject.getPrincipal().getClass());

        //传统的利用Url关键字来确定...
        //simpleAuthorizationInfo.addStringPermission(userVo.getUser().getStat());
      //  simpleAuthorizationInfo.addStringPermission("how_are_you]");

        //添加权限
        simpleAuthorizationInfo.addStringPermissions(userVo.getPermissions());
        //添加角色
        simpleAuthorizationInfo.addRoles(userVo.getRoles());

        return simpleAuthorizationInfo;
    }

    /**
     * 执行用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("用户认证...");

        //编写shiro判断逻辑,判断用户名和密码

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.login(token.getUsername());

        if(null==user){
            System.out.println("用户不存在...");
            //用户不存在,底层会抛出UnkownAccountException
            return null;//
        }
        //=========角色权限==============
        //获取当前用户的所有的角色
        List<Role> roles = roleService.findByUserId(user.getId());
        //获取当前用户角色所对应的权限
        List<Permission> permissions = permissionService.findByUserId(user.getId());

        List<String> roleStrList = new ArrayList<>();
        List<String> permissionStrList = new ArrayList<>();

        for(Role r:roles){
            roleStrList.add(r.getName());
        }

        for(Permission p:permissions){
            permissionStrList.add(p.getName());
        }
        UserVo userVo = new UserVo();

        userVo.setUser(user);
        userVo.setRoles(roleStrList);
        userVo.setPermissions(permissionStrList);
        userVo.setUname(user.getUsername());

        //=======================


        //return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        //获取加密盐
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        //System.out.println("salt:"+salt);

        //return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        //return new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
        return new SimpleAuthenticationInfo(userVo,user.getPassword(),salt,getName());
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
