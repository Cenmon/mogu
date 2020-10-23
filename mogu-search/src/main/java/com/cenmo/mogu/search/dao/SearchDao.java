package com.cenmo.mogu.search.dao;

import com.cenmo.mogu.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;


public interface SearchDao {
	SearchResult search(SolrQuery query) throws Exception;
}
