package tech.aistar.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.aistar.entity.Permission;
import tech.aistar.entity.UserRole;
import tech.aistar.mapper.PermissionMapper;
import tech.aistar.mapper.RoleMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPermissonMapper {

	@Autowired
	private PermissionMapper permissionMapper;

	@Test
	public void testFindByUserId() {
		List<Permission> idList = permissionMapper.findByUserId(3);
		idList.forEach(System.out::println);
	}

}
