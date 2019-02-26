package tech.aistar.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.aistar.entity.User;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,stat) values(#{username},#{password},#{stat})")
    int save(User user);

}
