package com.ruchita.elasticsearch_curd.bean;


import com.ruchita.elasticsearch_curd.constant.ResponseStatus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseBean {
	
	private ResponseStatus status;
	private String message;
	private Object response;
	
	
}
