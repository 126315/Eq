package com.zcn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcn.dao.SbztDao;
import com.zcn.pojo.Sbzt;
import com.zcn.service.SbztService;
@Service
public class SbztServiceImpl implements SbztService {
	@Autowired
	private SbztDao sbztDao;
	@Override
	public List<Sbzt> queryAllSbzt() {
		// TODO Auto-generated method stub
		return sbztDao.queryAllSbzt();
	}

}
