package tech.aistar.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
public class RolePermission implements Serializable{
    private Integer rid;

    private Integer pid;

    public RolePermission() {
    }

    public RolePermission(Integer rid, Integer pid) {
        this.rid = rid;
        this.pid = pid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RolePermission{");
        sb.append("rid=").append(rid);
        sb.append(", pid=").append(pid);
        sb.append('}');
        return sb.toString();
    }
}
