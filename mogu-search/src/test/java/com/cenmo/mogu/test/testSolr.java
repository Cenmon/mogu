package com.cenmo.mogu.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Cenmo
 * @Date 2020-10-23 2020/10/23
 */
public class testSolr {

    @Test
    public void testSolr() throws IOException, SolrServerException {
        // 创建HttpSolrClient
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder("http://192.168.117.129:8080/solr8/collection1").build();

        // 设置查询条件
        SolrQuery query = new SolrQuery("绮镜");
        // 设置关键查询域
        query.set("df", "item_keywords");

//        //设置分页
//        query.setStart( (page-1) * rows );
//        query.setRows(rows);

        //设置高亮显示
//        query.setHighlight(true);
//        query.addHighlightField("item_title");
//        query.setHighlightSimplePre("<em style=\"color:red\">");
//        query.setHighlightSimplePost("</em>");

        QueryResponse response = httpSolrClient.query(query);

        SolrDocumentList results = response.getResults();

        System.out.println(results);

        httpSolrClient.close();
    }

}
