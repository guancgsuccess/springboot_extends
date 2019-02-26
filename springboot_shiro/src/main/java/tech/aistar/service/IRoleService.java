package tech.aistar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aistar.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2019/2/25 0025.
 */
public interface IRoleService {
    List<Role> findByUserId(Integer uid);
}
