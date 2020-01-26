package com.ruchita.elasticsearch_curd.bean;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookBean {

	private String id;
	private String author;
	private String bookName;
}
