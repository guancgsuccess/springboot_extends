package tech.aistar.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.aistar.entity.Role;
import tech.aistar.entity.UserRole;
import tech.aistar.mapper.DepartmentMapper;
import tech.aistar.mapper.RoleMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRoleMapper {

	@Autowired
	private RoleMapper roleMapper;

	@Test
	public void testFindByUserId() {
		List<Role> idList = roleMapper.findByUserId(2);
		idList.forEach(System.out::println);
	}
}
