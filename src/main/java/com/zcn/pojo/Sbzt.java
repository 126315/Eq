package com.zcn.pojo;

import java.util.Set;

public class Sbzt {
   private int sid;
   private String ztmc;
   private Set<Equipment> equipment;

public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
public String getZtmc() {
	return ztmc;
}
public void setZtmc(String ztmc) {
	this.ztmc = ztmc;
}
public Set<Equipment> getEquipment() {
	return equipment;
}
public void setEquipment(Set<Equipment> equipment) {
	this.equipment = equipment;
}
   
}
