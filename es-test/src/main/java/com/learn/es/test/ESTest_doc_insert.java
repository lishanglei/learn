package com.learn.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/13 15:43
 */
public class ESTest_doc_insert {

    public static void main(String[] args) throws IOException {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200,"http"))
        );
        IndexRequest request =new IndexRequest();
        request.index("user").id("1001");

        User user =new User();
        user.setName("张三");
        user.setSex("男");
        user.setAge(18);

        ObjectMapper mapper =new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        //关闭ez客户端
        esClient.close();

    }
}
