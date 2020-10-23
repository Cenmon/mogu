package com.cenmo.mogu.search.service;


import com.cenmo.mogu.search.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
