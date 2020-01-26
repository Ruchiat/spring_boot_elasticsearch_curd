package com.ruchita.elasticsearch_curd.service.book;

import com.ruchita.elasticsearch_curd.bean.BookBean;
import com.ruchita.elasticsearch_curd.bean.ResponseBean;
import com.ruchita.elasticsearch_curd.service.common.BaseService;


public interface BookService  extends BaseService  {

	ResponseBean saveBook(BookBean bookBean);

	ResponseBean updateBook(BookBean bookBean);

	ResponseBean searchBook(String id);


}
