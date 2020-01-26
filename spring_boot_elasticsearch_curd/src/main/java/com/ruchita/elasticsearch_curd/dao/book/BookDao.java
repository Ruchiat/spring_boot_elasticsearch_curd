package com.ruchita.elasticsearch_curd.dao.book;

import java.util.List;

import org.json.JSONObject;

import com.ruchita.elasticsearch_curd.bean.BookBean;

public interface BookDao {

	BookBean saveBook(BookBean bookBean);

	List<BookBean> searchBook(JSONObject searchCriteria1);

	BookBean updateBook(BookBean bookBean);

	

}
