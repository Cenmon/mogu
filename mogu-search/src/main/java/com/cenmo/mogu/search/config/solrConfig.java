package com.cenmo.mogu.search.config;

import lombok.Data;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cenmo
 * @Date 2020-10-22 2020/10/22
 */
@Data
@Configuration
@ConfigurationProperties("mogu.search.solr")
public class solrConfig {
    /**
 * <bean id="httpSolrClientBuilder" class="org.apache.solr.client.solrj.impl.HttpSolrClient.Builder">
 * 		<constructor-arg name="baseSolrUrl" value="${SOLR.SERVER.URL}"></constructor-arg>
 * 	</bean>
 * 	<bean id="httpSolrClient" factory-bean="httpSolrClientBuilder" factory-method="build"></bean>
 */
    private String baseSolrUrl;

    @Bean("httpSolrClient")
    public HttpSolrClient httpSolrClientBuilder(){
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(baseSolrUrl).build();
        return httpSolrClient;
    }
}
