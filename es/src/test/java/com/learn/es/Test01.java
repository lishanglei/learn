package com.learn.es;

import com.alibaba.fastjson.JSON;
import com.learn.es.pojo.Product;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/28
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/28              lishanglei      v1.0.0           Created
 */
public class Test01 {

    private RestHighLevelClient client;

    private RequestOptions requestOptions = RequestOptions.DEFAULT;

    /**
     * 初始化连接
     */
    @Before
    public void init() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.5.62", 9200, "http")
                )
        );
    }

    /**
     * 关闭连接
     */
    @After
    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 新增Doc
     */
    @Test
    public void addDoc() {

        //构造请求数据
        Product product = new Product(2L, "大米手机", "手机", "小米", 2699.00, "http://images.leyou.com/123132.jpg");
        //转换为json格式数据
        String json = JSON.toJSONString(product);
        //准备请求对象
        IndexRequest indexRequest = new IndexRequest("heima", "product", "1");
        //将请求体封装到请求对象
        indexRequest.source(json, XContentType.JSON);
        //发送请i去
        try {
            IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println("响应结果: " + JSON.toJSONString(index));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询doc
     */
    @Test
    public void getDoc() {

        //声明id参数
        String id = "1";
        //构建请求对象
        GetRequest getRequest = new GetRequest("heima", "product", id);
        //发送请求
        try {
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println("响应结果: " + JSON.toJSONString(getResponse));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据id修改doc
     */
    @Test
    public void updateDoc() throws IOException {

        //构建修改请求对象
        UpdateRequest updateRequest = new UpdateRequest("heima", "product", "1");
        //构建修改数据
        Product product = new Product(1L, "超米手机", "手机", "小米", 2899.00, "http://images.leyou.com/123132.jpg");
        String json = JSON.toJSONString(product);
        //封装请求体至请求对象
        updateRequest.doc(json, XContentType.JSON);
        //发送请求
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println("响应结果:" + JSON.toJSONString(response));
    }

    /**
     * 根据id删除doc
     */
    @Test
    public void deleteDoc() throws IOException {

        //构建删除对象
        DeleteRequest deleteRequest = new DeleteRequest("heima", "product", "3");
        //调用方法进行数据通信
        DeleteResponse delete = client.delete(deleteRequest, requestOptions);
        System.out.println("响应结果: " + delete);
    }

    /**
     * 批量新增doc
     */
    @Test
    public void bulkAddDoc() throws IOException {

        //构建批量新增对象
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 3; i < 9; i++) {
            //构造请求数据
            Product product = new Product(new Long(i), "大米手机" + i, "手机", "小米", 2699.00 + i, "http://images.leyou.com/123132.jpg");
            //转换为json格式数据
            String json = JSON.toJSONString(product);
            //准备请求对象
            IndexRequest indexRequest = new IndexRequest("heima", "product", String.valueOf(i));
            indexRequest.source(json, XContentType.JSON);
            //添加数据
            bulkRequest.add(indexRequest);
        }
        //发送数据
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("响应结果:" + bulk);
    }

    /**
     * match查询doc
     */
    @Test
    public void matchDoc() throws IOException {

        //1.构建SearchRequest请求对象,指定索引库
        SearchRequest searchRequest = new SearchRequest("heima");
        //2.构建SearchSourceBuilder查询对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //3.构建QueryBuilder对象指定查询方式和查询条件
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "大米");
        //4.将queryBuilder对象设置到SearchSourceBuilder中
        searchSourceBuilder.query(queryBuilder);
        //5.将SearchSourceBuilder查询对象封装到请求对象SearchRequest中
        searchRequest.source(searchSourceBuilder);
        //6.调用方法进行数据通信
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //7.输出结果
        System.out.println("响应结果: " + response);
        SearchHit[] hits = response.getHits().getHits();
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            System.out.println("查询结果:" + sourceAsString);
        }
    }

    /**
     * match All查询
     */
    @Test
    public void matchAllDoc() throws IOException {

        //1.构建SearchRequest请求对象,指定索引库
        SearchRequest searchRequest = new SearchRequest("heima");
        //2.构建SearchSourceBuilder查询对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //3.构建QueryBuilder对象指定查询方式和查询条件
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        //4.将queryBuilder对象设置到SearchSourceBuilder中
        searchSourceBuilder.query(queryBuilder);
        //5.将SearchSourceBuilder查询对象封装到请求对象SearchRequest中
        searchRequest.source(searchSourceBuilder);
        //6.调用方法进行数据通信
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //7.输出结果
        System.out.println("响应结果: " + response);
        SearchHit[] hits = response.getHits().getHits();
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            System.out.println("查询结果:" + sourceAsString);
        }
    }


    /**
     * source过滤(作业)
     */
    @Test
    public void sourceSearch() throws IOException {

        //1.构建SearchRequest请求对象,指定索引库
        SearchRequest searchRequest = new SearchRequest("heima");
        //2.构建SearchSourceBuilder查询对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //3.构建QueryBuilder对象指定查询方式和查询条件
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        //4.将queryBuilder对象设置到SearchSourceBuilder中
        sourceBuilder.query(queryBuilder);
        //使用fetchSource实现过滤
        sourceBuilder.fetchSource(new String[]{"id", "title", "price"}, null);
        //5.将SearchSourceBuilder查询对象封装到请求对象SearchRequest中
        searchRequest.source(sourceBuilder);
        //6.调用方法进行数据通信
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //7.输出结果
        System.out.println("响应结果: " + response);
        SearchHit[] hits = response.getHits().getHits();
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            System.out.println("查询结果:" + sourceAsString);
        }
    }


    /**
     * 排序
     */
    @Test
    public void orderDoc() throws IOException {

        //1.构建SearchRequest请求对象,指定索引库
        SearchRequest searchRequest = new SearchRequest("heima");
        //2.构建SearchSourceBuilder查询对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //3.构建QueryBuilder对象指定查询方式和查询条件
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        //4.将queryBuilder对象设置到SearchSourceBuilder中
        sourceBuilder.query(queryBuilder);

        /**
         * 通过sort方法指定排序规则
         * args1: 排序字段
         * args2: 升序还是降序,默认升序
         *
         * 默认不能使用text字段排序( 设置"fielddata":true)
         */
        sourceBuilder.sort("id", SortOrder.DESC);
        sourceBuilder.sort("price");
        //5.将SearchSourceBuilder查询对象封装到请求对象SearchRequest中
        searchRequest.source(sourceBuilder);
        //6.调用方法进行数据通信
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //7.输出结果
        System.out.println("响应结果: " + response);
        SearchHit[] hits = response.getHits().getHits();
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            System.out.println("查询结果:" + sourceAsString);
        }
    }

    /**
     * 分页查询
     *
     * @throws IOException
     */
    @Test
    public void limitDoc() throws IOException {

        //1.构建SearchRequest请求对象,指定索引库
        SearchRequest searchRequest = new SearchRequest("heima");
        //2.构建SearchSourceBuilder查询对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //3.构建QueryBuilder对象指定查询方式和查询条件
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        //4.将queryBuilder对象设置到SearchSourceBuilder中
        sourceBuilder.query(queryBuilder);

        /**
         * 通过sort方法指定排序规则
         * args1: 排序字段
         * args2: 升序还是降序,默认升序
         *
         * 默认不能使用text字段排序( 设置"fielddata":true)
         */
        //sourceBuilder.sort("id", SortOrder.DESC);
        sourceBuilder.sort("price");
        /**
         * 分页  :查询第一页,每页两条
         * int start =(pageNum-1)*pageSize
         */

        sourceBuilder.from(1);
        sourceBuilder.size(2);  //分两页
        //5.将SearchSourceBuilder查询对象封装到请求对象SearchRequest中
        searchRequest.source(sourceBuilder);
        //6.调用方法进行数据通信
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //7.输出结果
        System.out.println("响应结果: " + response);
        SearchHit[] hits = response.getHits().getHits();
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            System.out.println("查询结果:" + sourceAsString);
        }
    }


    /**
     * 高亮查询
     *
     * @throws IOException
     */
    @Test
    public void highLightDoc() throws IOException {

        //1.构建SearchRequest请求对象,指定索引库
        SearchRequest searchRequest = new SearchRequest("heima");
        //2.构建SearchSourceBuilder查询对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //3.构建QueryBuilder对象指定查询方式和查询条件  高亮使用matchquery,不支持matchquery
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
        //4.将queryBuilder对象设置到SearchSourceBuilder中
        sourceBuilder.query(queryBuilder);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("title");

        sourceBuilder.highlighter(highlightBuilder);
        /**
         * 通过sort方法指定排序规则
         * args1: 排序字段
         * args2: 升序还是降序,默认升序
         *
         * 默认不能使用text字段排序( 设置"fielddata":true)
         */
        //sourceBuilder.sort("id", SortOrder.DESC);
        sourceBuilder.sort("price");
        /**
         * 分页  :查询第一页,每页两条
         * int start =(pageNum-1)*pageSize
         */

        //sourceBuilder.from(1);
        //sourceBuilder.size(2);  //分两页
        //5.将SearchSourceBuilder查询对象封装到请求对象SearchRequest中
        searchRequest.source(sourceBuilder);
        //6.调用方法进行数据通信
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        //7.输出结果
        //System.out.println("响应结果: " + response);
        SearchHit[] hits = response.getHits().getHits();
        for (int i = 0; i < hits.length; i++) {
            String sourceAsString = hits[i].getSourceAsString();
            //System.out.println("查询结果:" + sourceAsString);

            Map<String, HighlightField> highlightFields = hits[i].getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Text[] texts = title.getFragments();
            for (Text fragment : texts) {
                System.out.println("title:" + fragment);
            }
        }
    }
}
