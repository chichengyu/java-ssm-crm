package com.ssm.crm.service.impl;

import com.ssm.crm.dao.DictMapper;
import com.ssm.crm.pojo.Dict;
import com.ssm.crm.pojo.DictExample;
import com.ssm.crm.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Dict> getDictListByTypeCode(String typeCode) {
        DictExample example = new DictExample();
        DictExample.Criteria criteria = example.createCriteria();
        // 查询
        criteria.andDictTypeCodeEqualTo(typeCode);
        return dictMapper.selectByExample(example);
    }
}
