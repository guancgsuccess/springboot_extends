package tech.aistar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aistar.entity.User;
import tech.aistar.mapper.UserMapper;
import tech.aistar.service.IUserService;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username) {
        return userMapper.findByUserName(username);
    }
}
