package com.ruchita.elasticsearch_curd.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ruchita.elasticsearch_curd.service.common.PropertyService;

@Service
@Qualifier("propertyServiceImpl")
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	private Environment env;
	
	@Override
	public String getAppProperty(String code) {
		return env.getProperty(code);
	}

	@Override
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, null) ;
	}
	
	@Override
	public String getMessageForLang(String code) {
		try {
			return null; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
