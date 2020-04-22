package com.ruchita.elasticsearch_curd.service.book.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ruchita.elasticsearch_curd.bean.BookBean;
import com.ruchita.elasticsearch_curd.bean.ResponseBean;
import com.ruchita.elasticsearch_curd.constant.MessageConstant;
import com.ruchita.elasticsearch_curd.constant.ResponseStatus;
import com.ruchita.elasticsearch_curd.dao.book.BookDao;
import com.ruchita.elasticsearch_curd.service.book.BookService;
import com.ruchita.elasticsearch_curd.service.common.impl.BaseServiceImpl;
@Service
@Qualifier("bookServiceImpl")
public class BookServiceImpl extends BaseServiceImpl implements BookService {

	private static Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);

	@Autowired
	@Qualifier("bookDaoImpl")
	private BookDao bookDao;

	@Override
	public ResponseBean saveBook(BookBean bookBean) {
		try {
			bookDao.saveBook(bookBean);
			// TODO Auto-generated method stub
			return ResponseBean.builder().status(ResponseStatus.SUCCESS)
					.message(propertyService.getMessage(MessageConstant.RECORD_ADDED_SUCCESSFULLY)).response(bookBean)
					.build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(ResponseStatus.FAIL).message(e.getMessage()).response(bookBean)
					.build();
		}
	}

	@Override
	public ResponseBean searchBook(String id) {
		try {
			JSONObject searchCriteria1 = new JSONObject();
			searchCriteria1.put("id", id);
			List<BookBean> list = bookDao.searchBook(searchCriteria1);
			if (list != null) {
				BookBean bookBean = list.get(0);
				return ResponseBean.builder().status(ResponseStatus.SUCCESS).response(bookBean).build();
			}
			return ResponseBean.builder().status(ResponseStatus.FAIL).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(ResponseStatus.FAIL).message(e.getMessage()).build();
		}
	}

	@Override
	public ResponseBean updateBook(BookBean bookBean) {
		try {
			bookDao.updateBook(bookBean);
			// TODO Auto-generated method stub
			return ResponseBean.builder().status(ResponseStatus.SUCCESS)
					.message(propertyService.getMessage(MessageConstant.RECORD_ADDED_SUCCESSFULLY)).response(bookBean)
					.build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(ResponseStatus.FAIL).message(e.getMessage()).response(bookBean)
					.build();
		}
	}

}
