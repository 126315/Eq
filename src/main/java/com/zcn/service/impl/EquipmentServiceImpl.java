package com.zcn.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcn.dao.EquipmentDao;
import com.zcn.pojo.Equipment;
import com.zcn.pojo.Page;
import com.zcn.service.EquipmentService;
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentDao equipmentDao;
	@Override
	public int insertEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.insertEquipment(equipment);
	}

	@Override
	public int deleteEquipment(String id) {
		// TODO Auto-generated method stub
		return equipmentDao.deleteEquipment(id);
	}

	@Override
	public int updateEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.updateEquipment(equipment);
	}

	@Override
	public List<Equipment> queryEquipment(Page page) {
		// TODO Auto-generated method stub
		return equipmentDao.queryEquipment(page);
	}

	@Override
	public Equipment queryOne(String id) {
		// TODO Auto-generated method stub
		return equipmentDao.queryOne(id);
	}

	@Override
	public List<Equipment> queryNotUser() {
		// TODO Auto-generated method stub
		return equipmentDao.queryNotUser();
	}

	@Override
	public boolean updateSyz(String syzid,String id) {
		// TODO Auto-generated method stub
		return equipmentDao.updateSyz(syzid,id);
	}

	@Override
	public Equipment queryMyeq(String syzid) {
		// TODO Auto-generated method stub
		return equipmentDao.queryMyeq(syzid);
	}

	@Override
	public int tjjxd(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.tjjxd(equipment);
	}

	@Override
	public List<Equipment> queryMyjxd(String tdzid) {
		// TODO Auto-generated method stub
		return equipmentDao.queryMyjxd(tdzid);
	}

	@Override
	public List<Equipment> querydjxeq(String name) {
		// TODO Auto-generated method stub
		return equipmentDao.querydjxeq(name);
	}

	@Override
	public boolean updatejxsj(String jxsj, String id) {
		// TODO Auto-generated method stub
		return equipmentDao.updatejxsj(jxsj, id);
	}

	@Override
	public Integer searchTotalCount(Page page) {
		// TODO Auto-generated method stub
		return equipmentDao.searchTotalCount(page);
	}

	@Override
	public List<Equipment> geteqList(Page page) {
		// TODO Auto-generated method stub
		return equipmentDao.geteqList(page);
	}



}
