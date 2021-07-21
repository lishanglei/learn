package com.learn.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/13 15:43
 */
public class ESTest_index_create {

    public static void main(String[] args) throws IOException {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200,"http"))
        );

        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        //响应状态
        boolean responseStatus = response.isAcknowledged();
        System.out.println("索引操作:"+responseStatus);


        //关闭ez客户端
        esClient.close();

    }
}
