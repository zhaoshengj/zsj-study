
package com.zsj.nacos.ES.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

/**
 * @author zsj
 * @date 2019-06-16  16:52
 */
@Configuration
public class ESConfig {

    private String host = "120.79.4.13";
    private int port = 8200;
    private String schema = "http";
    private int connectTimeOut = 10000;
    private int socketTimeOut = 30000;
    private int connectionRequestTimeOut = 50000;
    private int maxConnectNum = 100;
    private int maxConnectPerRoute = 100;
    private HttpHost httpHost;
    private boolean uniqueConnectTimeConfig = true;
    private boolean uniqueConnectNumConfig = true;
    private RestClientBuilder builder;
    private RestHighLevelClient client;

    /**
     * Bean name default  函数名字
     *
     * @return
     */

    @Bean(autowire = Autowire.BY_NAME ,name = "restHighLevelClient")
    public RestHighLevelClient client() {
        httpHost= new HttpHost(host, port, schema);
        builder = RestClient.builder(httpHost);
        if (uniqueConnectTimeConfig) {
            setConnectTimeOutConfig();
        }
        if (uniqueConnectNumConfig) {
            setMutiConnectConfig();
        }
        client = new RestHighLevelClient(builder);
        return client;
    }

    /**
     * 异步httpclient的连接延时配置
     */
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                requestConfigBuilder.setSocketTimeout(socketTimeOut);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return requestConfigBuilder;
            }
        });
    }

    /**
     * 异步httpclient的连接数配置
     */

    public void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.setMaxConnTotal(maxConnectNum);
                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpClientBuilder;
            }
        });
    }


     /**
     * 关闭连接
     */
    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

