package com.ssm.crm.dao;

import com.ssm.crm.pojo.Dict;
import com.ssm.crm.pojo.DictExample;
import java.util.List;

public interface DictMapper {
    int deleteByPrimaryKey(String dictId);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    Dict selectByPrimaryKey(String dictId);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}