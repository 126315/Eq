package com.zcn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcn.dao.IdentifyDao;
import com.zcn.pojo.Identify;
import com.zcn.service.IdentifyService;
@Service
public class IdentifyServiceImpl implements IdentifyService {
    @Autowired
    private IdentifyDao identifyDao;
	@Override
	public List<Identify> queryIdentify() {
		// TODO Auto-generated method stub
		List<Identify> list=identifyDao.queryIdentify();
		
		return identifyDao.queryIdentify();
	}

}
