package com.learn.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/13 15:43
 */
public class ESTest_doc_like {

    public static void main(String[] args) throws IOException {

        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"))
        );

        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //允许差一个字符
//        FuzzyQueryBuilder fuzziness = QueryBuilders.fuzzyQuery("name", "wangws").fuzziness(Fuzziness.TWO);
//        sourceBuilder.query(fuzziness);

//        高亮查询
//        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "zhangsan");
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<font color='red'>");
//        highlightBuilder.postTags("<font color='red'>");
//        highlightBuilder.field("name");
//        sourceBuilder.highlighter(highlightBuilder);
//        sourceBuilder.query(termsQueryBuilder);
//        request.source(sourceBuilder);
        //聚合操作
//        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
//        sourceBuilder.aggregation(aggregationBuilder);

        //分组查询
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        sourceBuilder.aggregation(aggregationBuilder);
        request.source(sourceBuilder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(response.toString());
        for (SearchHit hit : hits) {
            System.out.println("=========" + hit.getSourceAsString());
        }

        //关闭ez客户端
        esClient.close();

    }
}
