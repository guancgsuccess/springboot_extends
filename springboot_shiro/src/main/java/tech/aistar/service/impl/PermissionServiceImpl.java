package tech.aistar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aistar.entity.Permission;
import tech.aistar.mapper.PermissionMapper;
import tech.aistar.service.IPermissionService;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService{
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByUserId(Integer uid) {
        return permissionMapper.findByUserId(uid);
    }
}
