package tech.aistar.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
public class UserVo {
    private User user;

    private String uname;

    private List<String> roles;

    private List<String> permissions;

    public UserVo() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
