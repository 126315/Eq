package com.zcn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Printer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.zcn.pojo.Equipment;
import com.zcn.pojo.Identify;
import com.zcn.pojo.Manager;
import com.zcn.pojo.Page;
import com.zcn.pojo.User;
import com.zcn.service.IdentifyService;
import com.zcn.service.ManagerService;

@Controller

public class ManagerController {
	@Autowired
	private ManagerService managerService;
   
	@RequestMapping(value="/loginEq",method=RequestMethod.POST)
    public String queryManager(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String name=request.getParameter("name");
		String password=request.getParameter("pwd");
//		String[] identifys=request.getParameterValues("identify");
//		int identify=0;
//		for(int i = 0; identifys!=null&&i < identifys.length; i++){
//			identify=Integer.parseInt(identifys[i]);
//			
//		}
//		System.out.println(identify);
		Manager manager=managerService.queryManager(name, password);
		if(manager!=null){
			request.getSession().setAttribute("manager", manager);
			return "redirect:queryEquipment";
		}
		model.addAttribute("mess","用户名或密码错误，请重新输入！");
		return "loginEq";

		
		
	}
	//判断该用户名是否存在
	@RequestMapping(value="/queryName")
	@ResponseBody
	public void queryName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name=request.getParameter("name");
		Manager manager=managerService.queryName(name);
		PrintWriter out=response.getWriter();
		
		if(manager!=null){
			out.print("true");
		}else{
			out.print("false");
		}
		out.close();
	}
	//添加员工信息
	@RequestMapping(value="/insertManager")
	public String insertManager(HttpServletRequest request,RedirectAttributes attr) throws IOException{
		String name=request.getParameter("name");
		String password=request.getParameter("pwd");
		String tel=request.getParameter("tel");
		String[] identify = request.getParameterValues("identify");//页面控件是select
		String email=request.getParameter("email");
	    Manager manager =managerService.queryName(name); 
	   
	    if(manager==null){
	    	Manager ma=new Manager();
	    	 for (int i = 0; identify!=null&&i < identify.length; i++) {
	    		 ma.setIdentify(Integer.parseInt(identify[i]));
	         }
	    	 ma.setName(name);
	    	 ma.setPassword(password);
	    	 ma.setTel(tel);
	    	 ma.setEmail(email);
	    	 managerService.insertManager(ma);
	    	 attr.addFlashAttribute("result", "添加成功");
	    	 
	     }else{
	    	 attr.addFlashAttribute("result","添加失败");
	    	
	    }
	    return "redirect:register";
        
	}
    @RequestMapping(value="home")
   public String queryAlljsry(HttpServletRequest request){
    	List<Manager> list=managerService.queryAlljsry();
    	request.getSession().setAttribute("list", list);
    	return "home";
    	
    }
    //初始化系统管理页面
   @RequestMapping(value="manager")
   public String queryAllManager(Page page,HttpServletRequest request,Model model){
	   Page p =page;
       int pageSize=7; //设置每页大小
       p.setPageSize(pageSize);
       System.out.println(p.getCurrentPage());
       int curPage=p.getCurrentPage();
       
       if (curPage==0) {
           curPage=1;
           p.setCurrentPage(curPage);
       }
       int startRow =page.getStartRow();
       
       if (!(p.getCurrentPage()==0)) {
            startRow = getStartRowBycurrentPage(curPage, pageSize);
       }
       p.setStartRow(startRow);
       String queryCondition=null;
       if(page.getqueryCondition()!=null){
       	queryCondition = page.getqueryCondition();//查询员工条件
       }
       List<Manager> MList=getmanagerList(page);
       p.setStartRow(startRow);
       Integer totalCounts = managerService.getTotalCount(page);//总条数
	   int totalPages=(totalCounts%pageSize==0)?(totalCounts/pageSize):(totalCounts/pageSize+1);//总页数=总条数/页大小+1
       p.setTotalPageCount(totalPages);//总页数
       page.setTotalCount(totalCounts);//总行数
	   model.addAttribute("page", page);
	   model.addAttribute("Mlist", MList);
	   return "manager";
   }
   private List<Manager> getmanagerList(Page page) {
       
       List<Manager> MList =null;
       
       if (page.getqueryCondition()==null) {
           
    	   MList=managerService.queryAllManager(page);
           
           return MList;
           
       }
       MList = managerService.getmanagerList(page);
       
       
       return MList;
       
   }

   public int getStartRowBycurrentPage(int currentPage,int pageSize){
       
       int startRow=0;
       
       if (currentPage==1) {
           
           return startRow=0;
       }
       
       startRow=(currentPage-1)*pageSize;
       
       return startRow;
       
   }

   @RequestMapping(value="queryManagerOne")
   public String queryManagerOne(HttpServletRequest request,RedirectAttributes attr){
	   String mid=request.getParameter("mid");
	   Manager manager=managerService.queryManagerOne(Integer.parseInt(mid));
       attr.addFlashAttribute("manager1", manager);
	   return "redirect:querySf";
   }
   //查询员工是否有负责的检修设备
   @RequestMapping(value="/queryjsry")
   public void queryjsry(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   String mid=request.getParameter("mid");
	   List<Manager> mList=managerService.queryjsry(Integer.parseInt(mid));
	   PrintWriter out=response.getWriter();
	   if(mList.size()>0){
		   out.print("true");
	   }else{
		   out.print("false"); 
	   }
   }
   //修改员工信息
   @RequestMapping(value="/updateOne")
   public String updateManager(HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr) throws IOException{
	   String name=request.getParameter("name");
	   String mid=request.getParameter("mid");
	   String tel=request.getParameter("tel");
	   String[] identify = request.getParameterValues("identify");
	   String email=request.getParameter("email");
	    Manager ma=new Manager();
	    ma.setMid(Integer.parseInt(mid));
	    ma.setName(name);
	    ma.setTel(tel);
	    ma.setEmail(email);
	    List<Manager> mList=managerService.queryjsry(Integer.parseInt(mid));
	    if(mList.size()>0){
			  attr.addFlashAttribute("msg", "修改身份失败，因为该员工有负责检修的设备！");
		   }else{
	       for (int i = 0; identify!=null&&i < identify.length; i++) {
   		    ma.setIdentify(Integer.parseInt(identify[i]));
           }
		   }
	    boolean result=managerService.updateManager(ma);
	    //修改完写入数据后，转发到系统管理页面
	    return "redirect:manager";
   }
   //删除单个员工信息
   @RequestMapping(value="deleteManager")
   public String deleteManager(HttpServletRequest request,RedirectAttributes attr){
	   String mid=request.getParameter("mid");
	   System.out.print(mid);
	   int result=0;
	   
	   if(mid!=null){
		   List<Manager> mList=managerService.queryjsry(Integer.parseInt(mid));
		  
		   if(mList.size()>0){
			   System.out.print(mList.size());
		      attr.addFlashAttribute("result","删除失败,该员工有管理电力设备!");
		   }else{
			   result=managerService.deleteManager(Integer.parseInt(mid));
		       System.out.print(result);
		   }
	   }
	   return "redirect:manager";
   }
   //批量删除员工信息
   @RequestMapping(value="deleteManagers")
   public void deleteManagers(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   String ygMess=request.getParameter("ygMess");
	   String[] mids=ygMess.split(",");
	   PrintWriter out=response.getWriter();
	   int result=0;
	   for(int i=0;i<mids.length;i++){
		   List<Manager> mList=managerService.queryjsry(Integer.parseInt(mids[i]));
		   if(mList==null||mList.size()==0){
		   result=managerService.deleteManager(Integer.parseInt(mids[i]));
		   }
	       
	   }

	   if(result!=0){
    	   out.print("true");
       }else{
    	   out.print("false");
       }
	   out.flush();
	   out.close();
	   
   }
 //访问登录页面
	@RequestMapping(value="/loginEq",method=RequestMethod.GET)
	
	public String login(Model model){
		
		return "loginEq";
	}
   //退出系统
	@RequestMapping(value="/logout")
   public String Outlogon(HttpSession session){
	   session.invalidate();
	   return "redirect:loginEq";
   }
	@RequestMapping(value="/forget",method=RequestMethod.POST)
   public String InteviewPwd(HttpServletRequest request,Model model) throws IOException{
	   String name=request.getParameter("username");
	   boolean result=managerService.InteviewPwd(name);
	  
	   if(result==true){
		   model.addAttribute("result", "重置密码成功！！");
		   return "loginEq";
	   }else{
		   model.addAttribute("result", "重置密码失败！！");
		   return "loginEq";
	   }
			
	   
   }
	@RequestMapping(value="changePwd")
	public void changePwd(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String newPwd=request.getParameter("NewPwd");
		String name=request.getParameter("name");
		boolean result=managerService.changePwd(newPwd, name);
		PrintWriter out=response.getWriter();
	
		if(result==true){
			out.print("true");
		}else{
			out.print("false");
		}
	}
}
