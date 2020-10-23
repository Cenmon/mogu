package com.cenmo.mogu.portal.service;


import com.cenmo.mogu.portal.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString, Integer page);
}
