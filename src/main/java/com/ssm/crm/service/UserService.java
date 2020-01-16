package com.ssm.crm.service;

import com.ssm.crm.pojo.User;

import java.util.List;

public interface UserService {

    List<User> login(String username);
}
