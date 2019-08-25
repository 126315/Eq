package com.zcn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zcn.pojo.Manager;
import com.zcn.pojo.Page;

public interface ManagerDao {
     public int insertManager(Manager manager);
     public Manager queryManager(@Param("name")String name,@Param("password")String password);     
     public Manager queryName(@Param("name")String name);
     public List<Manager> queryAlljsry();
     public List<Manager> queryAllManager(Page page);
     public List<Manager> getmanagerList(Page page);
     public Integer getTotalCount(Page page);//搜索总数量
     public Manager queryManagerOne(@Param("mid") int mid);
     public boolean updateManager(Manager manager);
     public int deleteManager(@Param("mid") int mid);
     public boolean InteviewPwd(@Param("name")String name);
     public boolean changePwd(@Param("password") String password,@Param("name")String name);
     public List<Manager> queryjsry(@Param("mid") int mid);
}
