package com.learn.es.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/28
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/28              lishanglei      v1.0.0           Created
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    //标题
    private String title;

    //分类
    private String category;

    //品牌
    private String brand;

    //价格
    private Double price;

    //图片地址
    private String images;

}
