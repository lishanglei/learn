package com.learn.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * 查询索引
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/13 15:43
 */
public class ESTest_index_search {

    public static void main(String[] args) throws IOException {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200,"http"))
        );

        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = esClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());

        //关闭ez客户端
        esClient.close();

    }
}
