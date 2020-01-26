package com.ruchita.elasticsearch_curd.dao.book.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruchita.elasticsearch_curd.bean.BookBean;
import com.ruchita.elasticsearch_curd.dao.book.BookDao;

@Repository
@Qualifier("bookDaoImpl")
public class BookDaoImpl implements BookDao {

	private final String INDEX = "book";
	private final String TYPE = "bookdetails";

	private RestHighLevelClient restHighLevelClient;

	private ObjectMapper objectMapper;

	public BookDaoImpl(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighLevelClient = restHighLevelClient;
	}

	@Override
	public BookBean saveBook(BookBean bookBean) {
		bookBean.setId(UUID.randomUUID().toString());
		Map<String, Object> dataMap = objectMapper.convertValue(bookBean, Map.class);
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, bookBean.getId()).source(dataMap);
		try {
			IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (ElasticsearchException e) {
			e.getDetailedMessage();
		} catch (java.io.IOException ex) {
			ex.getLocalizedMessage();
		}
		return bookBean;
	}

	@Override
	public List<BookBean> searchBook(JSONObject searchCriteria1) {
		List<BookBean> bookBeans = new ArrayList<>();

		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

			if (searchCriteria1 != null && searchCriteria1.length() > 0) {

				searchCriteria1.keys().forEachRemaining(key -> {
					Object value = searchCriteria1.get(key);
					searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(key, value));
				});

			} else {
				searchSourceBuilder.query(QueryBuilders.matchAllQuery());
			}

			searchSourceBuilder.size(5000);
			SearchRequest searchRequest = new SearchRequest(INDEX);
			searchRequest.source(searchSourceBuilder);

			final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(10L));
			searchRequest.scroll(scroll);
			SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

			SearchHit[] results = response.getHits().getHits();
			for (SearchHit hit : results) {
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				BookBean searchedUser = objectMapper.convertValue(sourceAsMap, BookBean.class);
				bookBeans.add(searchedUser);
			}

			return bookBeans;

		} catch (Exception e) {
			e.printStackTrace();
			return bookBeans;
		}
	}

	@Override
	public BookBean updateBook(BookBean bookBean) {
		String id = bookBean.getId();
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true); // Fetch Object after its
																							// update
		Map<String, Object> error = new HashMap<>();
		try {
			String bookJson = objectMapper.writeValueAsString(bookBean);
			updateRequest.doc(bookJson, XContentType.JSON);
			UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
			Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
			BookBean userBeans = objectMapper.convertValue(sourceAsMap, BookBean.class);
			return userBeans;
		} catch (JsonProcessingException e) {
			e.getMessage();
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
		return bookBean;
	}
}
