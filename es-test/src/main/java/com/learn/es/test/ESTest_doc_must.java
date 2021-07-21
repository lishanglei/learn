package com.learn.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/13 15:43
 */
public class ESTest_doc_must {

    public static void main(String[] args) throws IOException {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
        );

        SearchRequest request =new SearchRequest();
        request.indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //boolQueryBuilder.must(QueryBuilders.matchQuery("age",20));
        //boolQueryBuilder.must(QueryBuilders.matchQuery("sex","女"));
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex","女"));
        //boolQueryBuilder.should(QueryBuilders.matchQuery("sex","女"));
        //boolQueryBuilder.should(boolQueryBuilder);
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(boolQueryBuilder);

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gte(17);
        sourceBuilder.query(rangeQuery);


//        //(当前页码-1)*每页显示数据条数
//        sourceBuilder.from(0).size(3);
//        String[] excludes ={};
//        String[] includes ={"name"};
//
//        sourceBuilder.fetchSource(includes,excludes);
//        sourceBuilder.sort("age", SortOrder.DESC);
        request.source(sourceBuilder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println("========="+hit.getSourceAsString());
        }

        //关闭ez客户端
        esClient.close();

    }
}
