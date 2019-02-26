package tech.aistar.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
public class UserRole implements Serializable{
    private Integer uid;

    private Integer rid;

    public UserRole() {
    }

    public UserRole(Integer uid, Integer rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRole{");
        sb.append("uid=").append(uid);
        sb.append(", rid=").append(rid);
        sb.append('}');
        return sb.toString();
    }
}
