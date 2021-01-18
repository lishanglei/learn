package com.learn.mongodb.service;

import com.learn.mongodb.dao.CommentRepository;
import com.learn.mongodb.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
@Service
public class CommentService {
    //注入dao

    @Autowired
    private CommentRepository commentRepository;

    /**
     * \* 保存一个评论
     * <p>
     * \* @param comment
     */
    public void saveComment(Comment comment) {


        //如果需要自定义主键，可以在这里指定主键；如果不指定主键，MongoDB会自动生成主键
        //设置一些默认初始值。。。
        //调用dao
        commentRepository.save(comment);

    }

    /**
     * \* 更新评论
     * <p>
     * \* @param comment
     */

    public void updateComment(Comment comment) {

        //调用dao
        commentRepository.save(comment);

    }

    /**
     * \* 根据id删除评论
     * <p>
     * \* @param id
     */

    public void deleteCommentById(String id) {

        //调用dao
        commentRepository.deleteById(id);

    }

    /**
     * \* 查询所有评论
     * <p>
     * \* @return
     */

    public List<Comment> findCommentList() {

        //调用dao
        return commentRepository.findAll();

    }

    /**
     * \* 根据id查询评论
     * <p>
     * \* @param id
     * <p>
     * \* @return
     */

    public Comment findCommentById(String id) {

        //调用dao

        return commentRepository.findById(id).get();

    }

    /**
     * \* 根据父id查询分页列表
     * <p>
     * \* @param parentid
     * <p>
     * \* @param page
     * <p>
     * \* @param size
     * <p>
     * \* @return
     */
    public Page<Comment> findCommentListPageByParentid(String parentid, int page, int size) {

        return commentRepository.findByParentid(parentid, PageRequest.of(page - 1, size));

    }
}
