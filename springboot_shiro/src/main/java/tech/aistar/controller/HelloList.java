package tech.aistar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.aistar.entity.Department;
import tech.aistar.mapper.DepartmentMapper;

/**
 * Created by Administrator on 2019/2/21 0021.
 */
@RestController
public class HelloList {

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/find/{id}")
    public Department find(@PathVariable("id") Integer id){
        return departmentMapper.getById(id);
    }
}
