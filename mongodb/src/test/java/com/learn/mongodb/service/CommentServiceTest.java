package com.learn.mongodb.service;

import com.learn.mongodb.MongodbApplication;
import com.learn.mongodb.po.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2021/1/18
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2021/1/18              lishanglei      v1.0.0           Created
 */
//SpringBoot的Junit集成测试
@RunWith(SpringRunner.class)
// SpringBoot的测试环境初始化，参数：启动类
@SpringBootTest(classes = MongodbApplication.class)
public class CommentServiceTest {

//注入Service

    @Autowired
    private CommentService commentService;

    /**
     * \* 保存一个评论
     */

    @Test

    public void testSaveComment() {

        Comment comment = new Comment();

        comment.setArticleid("100000");

        comment.setContent("测试添加的数据");

        comment.setCreatedatetime(LocalDateTime.now());

        comment.setUserid("1003");

        comment.setNickname("凯撒大帝");

        comment.setState("1");

        comment.setLikenum(0);

        comment.setReplynum(0);

        commentService.saveComment(comment);

    }

    /**
     * \* 查询所有数据
     */

    @Test

    public void testFindAll() {

        List<Comment> list = commentService.findCommentList();

        System.out.println(list);

    }

    /**
     * \* 测试根据id查询
     */

    @Test

    public void testFindCommentById() {

        Comment comment = commentService.findCommentById("5d6a27b81b8d374798cf0b41");

        System.out.println(comment);

    }

    /**
     * 测试根据父id查询子评论的分页列表
     */
    @Test
    public void testFindCommentListPageByParentid() {
        Page<Comment> pageResponse = commentService.findCommentListPageByParentid("3", 1, 2);
        System.out.println("----总记录数：" + pageResponse.getTotalElements());
        System.out.println("----当前页数据：" + pageResponse.getContent());
    }
}
