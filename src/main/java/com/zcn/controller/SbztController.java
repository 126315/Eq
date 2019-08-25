package com.zcn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zcn.pojo.Sbzt;
import com.zcn.service.SbztService;

@Controller
public class SbztController {
  @Autowired
  private SbztService sbztService;
  @RequestMapping("queryAllSbzt")
  public String queryAllSbzt(HttpServletRequest request){
	List<Sbzt> sbztList=sbztService.queryAllSbzt();
	request.getSession().setAttribute("sbztList", sbztList);
	return "update";
	  
  }
  @RequestMapping(value="getSbzt",produces="application/json;charset=utf-8")
  @ResponseBody
  public String getSbzt(){
	  List<Sbzt> sbztList=sbztService.queryAllSbzt();
	  Gson gson=new Gson();
	  return gson.toJson(sbztList);
	  
  }
}
