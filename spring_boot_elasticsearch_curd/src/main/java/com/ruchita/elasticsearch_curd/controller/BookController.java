package com.ruchita.elasticsearch_curd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruchita.elasticsearch_curd.bean.BookBean;
import com.ruchita.elasticsearch_curd.bean.ResponseBean;
import com.ruchita.elasticsearch_curd.service.book.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	//@Qualifier("bookServiceImpl")
	private BookService bookService;
	
	@PostMapping("/saveBook")
    public ResponseBean saveBook(@RequestBody BookBean bookBean){
    	return bookService.saveBook(bookBean);
    }
	
	@PostMapping("/updateBook")
    public ResponseBean updateBook(@RequestBody BookBean bookBean){
		return bookService.updateBook(bookBean);    	
    }

	@PostMapping("/searchBook")
    public ResponseBean searchBook(@RequestBody String id){
    	return bookService.searchBook(id);
    }
}
