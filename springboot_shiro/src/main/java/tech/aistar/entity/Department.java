package tech.aistar.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/2/21 0021.
 */
public class Department implements Serializable{
    private Integer id;

    private String departmentName;

    public Department(String departName) {
        this.departmentName = departName;
    }

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("id=").append(id);
        sb.append(", departName='").append(departmentName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
