package tech.aistar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.aistar.entity.Permission;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
@Mapper
public interface PermissionMapper {

    @Select("select  distinct up.* from u_user_role ur join u_role_permission rp on ur.rid = rp.rid \n" +
            "join u_permission up on up.id = rp.pid where uid = #{uid}")
    List<Permission> findByUserId(Integer uid);
}
