package com.zbs.mybatisplus.config.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * description: CustomIdGenerator 自定义ID生成策略
 * date: 2020/10/20 13:56
 * author: zhangbs
 * version: 1.0
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {
    /**
     * 生成Id
     *
     * @param entity 实体
     * @return id
     */
    @Override
    public Number nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        //根据bizKey调用分布式ID生成
        Random random=new Random();
        long id = bizKey.hashCode() + random.nextInt(1000);
        //返回生成的id值即可.
        return id;
    }
}
