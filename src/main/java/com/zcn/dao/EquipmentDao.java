
package com.zcn.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zcn.pojo.Equipment;
import com.zcn.pojo.Page;

public interface EquipmentDao {
     public int insertEquipment(Equipment equipment);
     public int deleteEquipment(@Param("id")String id);
     public int updateEquipment(Equipment equipment);
     public List<Equipment> queryEquipment(Page page);
     public List<Equipment> geteqList(Page page);
     public Integer searchTotalCount(Page page);//搜索总数量
     public Equipment queryOne(@Param("id") String id);
     public List<Equipment> queryNotUser();
     public boolean updateSyz(@Param("syzid") String syzid,@Param("id") String id);
     public Equipment queryMyeq(@Param("syzid") String syzid);
     public int tjjxd(Equipment equipment);
     public List<Equipment> queryMyjxd(@Param("tdzid")String tdzid);
     public List<Equipment> querydjxeq(@Param("name") String name);
     public boolean updatejxsj(@Param("jxsj") String jxsj,@Param("id") String id);
}
