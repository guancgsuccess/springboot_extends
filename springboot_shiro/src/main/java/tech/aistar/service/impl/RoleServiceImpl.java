package tech.aistar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aistar.entity.Role;
import tech.aistar.mapper.RoleMapper;
import tech.aistar.service.IRoleService;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByUserId(Integer uid) {
        return roleMapper.findByUserId(uid);
    }
}
