package tech.aistar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.aistar.entity.Role;
import tech.aistar.entity.UserRole;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
@Mapper
public interface RoleMapper {

    /**
     *
     * @param uid 用户id
     * @return Role
     */
    @Select("select r.* from u_role r join u_user_role ur on ur.rid = r.id\n" +
            "where ur.uid=#{uid}")
    List<Role> findByUserId(Integer uid);

}
