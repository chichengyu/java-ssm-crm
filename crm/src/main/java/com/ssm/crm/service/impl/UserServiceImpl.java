package com.ssm.crm.service.impl;

import com.ssm.crm.dao.UserMapper;
import com.ssm.crm.pojo.User;
import com.ssm.crm.pojo.UserExample;
import com.ssm.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> login(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(username);
        criteria.andUserStateEqualTo(String.valueOf(1));
        List<User> users = userMapper.selectByExample(example);
        return users;
    }
}
