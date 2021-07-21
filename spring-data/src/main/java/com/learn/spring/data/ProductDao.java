package com.learn.spring.data;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/15$ 11:01$
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
