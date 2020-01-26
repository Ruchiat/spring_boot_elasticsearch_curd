package com.ruchita.elasticsearch_curd.service.common;

public interface PropertyService {
	
	public String getAppProperty(String code);
	public String getMessage(String code);
	public String getMessageForLang(String code);
	
}
