package com.zcn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcn.dao.ManagerDao;
import com.zcn.pojo.Manager;
import com.zcn.pojo.Page;
import com.zcn.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	public ManagerDao managerDao;
	@Override
	public int insertManager(Manager manager) {
		// TODO Auto-generated method stub
		return managerDao.insertManager(manager);
	}

	@Override
	public Manager queryManager(String name, String password) {
		// TODO Auto-generated method stub
		return managerDao.queryManager(name, password);
	}

	@Override
	public Manager queryName(String name) {
		// TODO Auto-generated method stub
		return managerDao.queryName(name);
	}

	@Override
	public List<Manager> queryAlljsry() {
		// TODO Auto-generated method stub
		return managerDao.queryAlljsry();
	}

	

	@Override
	public Manager queryManagerOne(int mid) {
		// TODO Auto-generated method stub
		return managerDao.queryManagerOne(mid);
	}

	@Override
	public boolean updateManager(Manager manager) {
		// TODO Auto-generated method stub
		return managerDao.updateManager(manager);
	}

	@Override
	public int deleteManager(int mid) {
		// TODO Auto-generated method stub
		return managerDao.deleteManager(mid);
	}

	@Override
	public boolean InteviewPwd(String name) {
		// TODO Auto-generated method stub
		return managerDao.InteviewPwd(name);
	}

	@Override
	public boolean changePwd(String password, String name) {
		// TODO Auto-generated method stub
		return managerDao.changePwd(password, name);
	}

	@Override
	public List<Manager> queryAllManager(Page page) {
		// TODO Auto-generated method stub
		return managerDao.queryAllManager(page);
	}

	@Override
	public List<Manager> getmanagerList(Page page) {
		// TODO Auto-generated method stub
		return managerDao.getmanagerList(page);
	}

	@Override
	public Integer getTotalCount(Page page) {
		// TODO Auto-generated method stub
		return managerDao.getTotalCount(page);
	}

	@Override
	public List<Manager> queryjsry(int mid) {
		// TODO Auto-generated method stub
		return managerDao.queryjsry(mid);
	}

}
