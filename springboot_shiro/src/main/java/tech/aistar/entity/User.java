package tech.aistar.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
public class User implements Serializable{
    private Integer id;

    private String username;

    private String password;

    private String stat;

    public User() {
    }

    public User(String username, String password, String stat) {
        this.username = username;
        this.password = password;
        this.stat = stat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", stat=").append(stat);
        sb.append('}');
        return sb.toString();
    }
}
