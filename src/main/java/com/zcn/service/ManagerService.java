package com.zcn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zcn.pojo.Manager;
import com.zcn.pojo.Page;

public interface ManagerService {
	 public int insertManager(Manager manager);
     public Manager queryManager(String name,String password);     
     public Manager queryName(String name);
     public List<Manager> queryAlljsry();
     public List<Manager> queryAllManager(Page page);
     public List<Manager> getmanagerList(Page page);
     public Integer getTotalCount(Page page);//搜索总数量
     public Manager queryManagerOne(int mid);
     public boolean updateManager(Manager manager);
     public int deleteManager(int mid);
     public boolean InteviewPwd(String name);
     public boolean changePwd(String password,String name);
     public List<Manager> queryjsry(int mid);
}
