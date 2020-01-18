package com.ssm.crm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.crm.pojo.Customer;
import com.ssm.crm.vo.CustomerSearchVo;

public interface CustomerService {

    PageInfo<Customer> getCustomerList(CustomerSearchVo customerVo, int page, int size);

    Customer getCustomerById(long id);

    void updateCustomerById(Customer customer);

    void delete(long id);
}
