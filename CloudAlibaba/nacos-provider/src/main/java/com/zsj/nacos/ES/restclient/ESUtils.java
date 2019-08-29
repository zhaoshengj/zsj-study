package com.zsj.nacos.ES.restclient;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zsj
 * @date 2019-06-19  14:55
 */
//@Component
public class ESUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ESUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    private static RestHighLevelClient client;

    @Resource(name = "restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    /**
     * @PostContruct是spring框架的注解
     * spring容器初始化的时候执行该方法
     */
    @PostConstruct
    public void init() {
        client = this.restHighLevelClient;
    }

    /**
     *
     *  index 增删改查   indeces
     *
     */
    public static void createIndex(String index){
        CreateIndexRequest request = new CreateIndexRequest(index);
        /**
         *
         * 分片 备份设置
         */
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
        );

        /**
         * 定义字段标识
         */
        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");

        //定义IK 分词  ik_max_word   ik_smart
        message.put("analyzer","ik_smart");
        Map<String, Object> properties = new HashMap<>();
        properties.put("message", message);
        Map<String, Object> map = new HashMap<>();
        map.put("properties", properties);
        request.mapping(map);
        try {
            CreateIndexResponse responce = client.indices().create(request, RequestOptions.DEFAULT);
            System.out.println(JSON.toJSONString(responce));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void deleteIndex(String index){
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try {
            AcknowledgedResponse responce = client.indices().delete(request, RequestOptions.DEFAULT);
            System.out.println(JSON.toJSONString(responce));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void getIndex(String index){
        try {
            GetIndexRequest request = new GetIndexRequest(index);
            request.local(false);
            request.humanReadable(true);
            request.includeDefaults(false);
            GetIndexResponse responce = client.indices().get(request, RequestOptions.DEFAULT);
            System.out.println(responce.getIndices());
            System.out.println(responce.getMappings());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static boolean existsIndex(String index){
        try {
            GetIndexRequest request = new GetIndexRequest(index);
            request.local(false);
            request.humanReadable(true);
            request.includeDefaults(false);
            boolean responce = client.indices().exists(request, RequestOptions.DEFAULT);
            System.out.println(responce);
            return responce;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 索引别名
     * @param index
     */
    public static void aliaseIndex(String index, String aliase, IndicesAliasesRequest.AliasActions.Type type){
        try {
            IndicesAliasesRequest request = new IndicesAliasesRequest();
            IndicesAliasesRequest.AliasActions aliasAction =
                    new IndicesAliasesRequest.AliasActions(type)
                            .index(index)
                            .alias(aliase);
            request.addAliasAction(aliasAction);
            AcknowledgedResponse responce = client.indices().updateAliases(request, RequestOptions.DEFAULT);
            System.out.println(responce.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void search(String index,String message){
        try {
            SearchRequest searchRequest = new SearchRequest(index);

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.termQuery("message", message));
            //查询 个数
            sourceBuilder.from(0);
            sourceBuilder.size(5);
            //查询时间
            sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

            //条件 二
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("message", message);
            matchQueryBuilder.fuzziness(Fuzziness.AUTO);
            matchQueryBuilder.prefixLength(3);
            matchQueryBuilder.maxExpansions(10);
            //条件三
            QueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("message", message)
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(3)
                    .maxExpansions(10);
            //sourceBuilder.query(matchQueryBuilder);

            //排序
            //sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); //默认
            sourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.ASC)); //字段排序

            searchRequest.source(sourceBuilder);



            SearchResponse responce = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = responce.getHits().getHits();
            System.out.println(JSON.toJSONString(hits));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    /**
     * 文档的 增 删 改 查  document
     * @param index
     * @param jsonString
     */
    public static void createDocument(String index,String id,String jsonString){
        IndexRequest request = new IndexRequest(index);
        //设置ID
        request.id(id);
        request.source(jsonString, XContentType.JSON);
        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getDocument(String index,String id){
        GetRequest request = new GetRequest(index,id);
        request.fetchSourceContext();
        request.storedFields();
        //
        try {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void existsDocument(String index,String id){
        GetRequest request = new GetRequest(index,id);
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        //
        try {
            Boolean exists = client.exists(request, RequestOptions.DEFAULT);
            System.out.println(exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDocument(String index,String id){
        DeleteRequest request = new DeleteRequest(index,id);
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateDocument(String index,String id,String jsonString){
        UpdateRequest request = new UpdateRequest(index,id);
        request.doc(jsonString,XContentType.JSON);
        try {
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
