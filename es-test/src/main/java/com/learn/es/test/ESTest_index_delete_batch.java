package com.learn.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/13 15:43
 */
public class ESTest_index_delete_batch {

    public static void main(String[] args) throws IOException {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200,"http"))
        );

        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse delete = esClient.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(delete.isAcknowledged());


        //关闭ez客户端
        esClient.close();

    }
}
