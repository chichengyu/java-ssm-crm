package com.ssm.crm.dao;

import com.ssm.crm.pojo.Customer;
import com.ssm.crm.pojo.CustomerExample;
import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long custId);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Long custId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}