package com.learn.mongodb.dao;

import com.learn.mongodb.po.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2021/1/18
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2021/1/18              lishanglei      v1.0.0           Created
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    //根据父id，查询子评论的分页列表  方法名称不能错   findBy固定, 后边拼接字段名
    Page<Comment> findByParentid(String parentid, Pageable pageable);


}
