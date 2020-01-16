package com.ssm.crm.service;

import com.ssm.crm.pojo.Dict;

import java.util.List;

public interface DictService {
    List<Dict> getDictListByTypeCode(String typeCode);
}
