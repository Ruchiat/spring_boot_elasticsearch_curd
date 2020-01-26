package com.ruchita.elasticsearch_curd.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ruchita.elasticsearch_curd.service.common.BaseService;
import com.ruchita.elasticsearch_curd.service.common.PropertyService;

@Service
@Qualifier("baseServiceImpl")
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	@Qualifier("propertyServiceImpl")
	protected PropertyService propertyService;	

}