package com.learn.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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
public class ESTest_doc_insert_batch {

    public static void main(String[] args) throws IOException {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200,"http"))
        );
        BulkRequest request =new BulkRequest();
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON,"name","zhangsan","age",16,"sex","女"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name","lisi","age",17,"sex","男"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","wangwu","age",18,"sex","女"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name","zhaoliu","age",19,"sex","男"));
        request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON,"name","qigong","age",20,"sex","女"));
        request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON,"name","wangba","age",21,"sex","男"));
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());
        //关闭ez客户端
        esClient.close();

    }
}
