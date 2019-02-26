package tech.aistar.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.aistar.entity.Department;

/**
 * Created by Administrator on 2019/2/21 0021.
 */
@Mapper
public interface DepartmentMapper {

    Department getById(Integer id);
}
