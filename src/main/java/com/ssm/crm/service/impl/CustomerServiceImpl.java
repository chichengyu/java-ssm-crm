package com.ssm.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.crm.dao.CustomerMapper;
import com.ssm.crm.pojo.Customer;
import com.ssm.crm.pojo.CustomerExample;
import com.ssm.crm.service.CustomerService;
import com.ssm.crm.vo.CustomerSearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public PageInfo<Customer> getCustomerList(CustomerSearchVo customerVo, int page, int size) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        // 判断用户名
        if (StringUtils.isNotBlank(customerVo.getCustomerName())){
            criteria.andCustNameLike("%" + customerVo.getCustomerName() + "%");
        }
        // 判断客户来源是否有值
        if (StringUtils.isNotBlank(customerVo.getCustSource())){
            criteria.andCustSourceEqualTo(customerVo.getCustSource());
        }
        // 判断客户所属行业
        if (StringUtils.isNotBlank(customerVo.getCustIndustory())){
            criteria.andCustIndustryEqualTo(customerVo.getCustIndustory());
        }
        // 判判断客户级别
        if (StringUtils.isNotBlank(customerVo.getCustLevel())){
            criteria.andCustLevelEqualTo(customerVo.getCustLevel());
        }
        PageHelper.startPage(page,size);
        List<Customer> customers = customerMapper.selectByExample(example);
        PageInfo<Customer> customerPageInfo = new PageInfo<>(customers);
        return customerPageInfo;
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateCustomerById(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void delete(long id) {
        customerMapper.deleteByPrimaryKey(id);
    }
}
