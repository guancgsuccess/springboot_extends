package tech.aistar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.aistar.entity.AlipayProperties;
import tech.aistar.mapper.DepartmentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {

	//@Autowired
	//private DepartmentMapper departmentMapper;

	@Autowired
	private AlipayProperties alipayProperties;

	@Test
	public void contextLoads() {
		//System.out.println(departmentMapper+"===========");
		//System.out.println(departmentMapper.getById(1));

		System.out.println(alipayProperties);
		System.out.println(alipayProperties.description());
	}

}
