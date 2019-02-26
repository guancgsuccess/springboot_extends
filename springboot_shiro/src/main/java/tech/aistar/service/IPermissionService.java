package tech.aistar.service;

import tech.aistar.entity.Permission;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
public interface IPermissionService {

    List<Permission> findByUserId(Integer uid);
}
