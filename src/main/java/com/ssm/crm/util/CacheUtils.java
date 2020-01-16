package com.ssm.crm.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ssm.crm.dao.DictMapper;
import com.ssm.crm.pojo.Dict;
import com.ssm.crm.pojo.DictExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/** guava 缓存
 * 字典数据缓存
 */
@Component
public class CacheUtils {
    @Autowired
    private DictMapper dictMapper;
    //key：typeCode
    //value：Map<key:id, value:item_name>
    private LoadingCache<String, Map> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.DAYS)
            .maximumSize(100)
            .build(new CacheLoader<String, Map>() {
                @Override
                public Map load(String typeCode) throws Exception {
                    //通过typeCode加载字典数据
                    DictExample example = new DictExample();
                    DictExample.Criteria criteria = example.createCriteria();
                    //根据typeCode查询字典列表
                    criteria.andDictTypeCodeEqualTo(typeCode);
                    //执行查询
                    List<Dict> dictList = dictMapper.selectByExample(example);
                    //把list转换成map对象
                    /*Map<String, String> map = dictList.stream()
                            .collect(Collectors.toMap(dict -> dict.getDictId(), dict -> dict.getDictItemName()));*/
                    Map<String, String> map = dictList.stream()
                            .collect(Collectors.toMap(Dict::getDictId, Dict::getDictItemName));
                    return map;
                }
            });
    public String getTypeName(String typeCode, String dictId) {
        try {
            return (String) cache.get(typeCode).get(dictId);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void clearCache(String key) {
        cache.invalidate(key);
    }
}
