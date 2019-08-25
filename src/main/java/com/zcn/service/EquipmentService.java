package com.zcn.service;



import java.util.Date;
import java.util.List;











import org.apache.ibatis.annotations.Param;

import com.zcn.pojo.Equipment;
import com.zcn.pojo.Page;

public interface EquipmentService {
	public int insertEquipment(Equipment equipment);
    public int deleteEquipment(String id);
    public int updateEquipment(Equipment equipment);
    public Equipment queryOne(String id);
    public List<Equipment> queryNotUser();
    public boolean updateSyz(String syzid,String id);
    public Equipment queryMyeq(String syzid);
    public int tjjxd(Equipment equipment);
    public List<Equipment> queryMyjxd(String tdzid);
    public List<Equipment> querydjxeq(String name);
    public boolean updatejxsj(String jxsj,String id);
    public List<Equipment> queryEquipment(Page page);
    public Integer searchTotalCount(Page page);//搜索总数量
    public List<Equipment> geteqList(Page page);
}
