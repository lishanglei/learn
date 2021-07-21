package com.learn.spring.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/15 11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESIndexTest {

    //注入 ElasticsearchRestTemplate
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    //创建索引并增加映射配置
    @Test
    public void createIndex(){
        //创建索引，系统初始化会自动创建索引
        System.out.println("创建索引");
        elasticsearchRestTemplate.createIndex(Product.class);
    }
    @Test
    public void deleteIndex(){
        //删除索引，系统初始化会自动创建索引
        boolean b = elasticsearchRestTemplate.deleteIndex(Product.class);
        System.out.println("删除索引 = " + b);
    }
}
