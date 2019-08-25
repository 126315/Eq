package com.zcn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;








import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.zcn.pojo.Equipment;
import com.zcn.pojo.Manager;
import com.zcn.pojo.Page;
import com.zcn.pojo.User;
import com.zcn.service.EquipmentService;
import com.zcn.service.ManagerService;

@Controller
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
   @RequestMapping(value="insert")
   public String insert(){
	   return "insert";
   }
	//添加设备
	@RequestMapping(value="/insertEquipment")
	public String insertEquipment(HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//系统生成设备编号
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String paymentID =String.valueOf(now)+String.valueOf(r1)+String.valueOf(r2);
          Equipment eq=new Equipment();
		   eq.setId(paymentID);
		   String ename = null; 
		   String jsry=null;
		   boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		   String imgFile=null;	//构造上传的路径
		   String introduction=null;
		   String uploadfile=request.getSession().getServletContext().getRealPath("upload/");
		  System.out.println(uploadfile);
		 if(isMultipart){
			/*
			 * ServletFileUpload对象的创建，需要依赖于FileItemFactory接口，
			 * 通过接口名可知FileItemFactory是一个工厂，此接口的实现类为：DiskFileItemFactory
			 */
			FileItemFactory factory=new DiskFileItemFactory();
			//通过FileItemFactory 对象构造ServletFileUpload对象
			//此对象主要用于解析form表单中的每一个字段的数据
			ServletFileUpload upload=new ServletFileUpload(factory);
             //解析form表单的每一个字段元素
				List<FileItem> items=null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Iterator<FileItem> item=items.iterator();
				System.out.println(item);
				while(item.hasNext()){
					FileItem it=item.next();
					//获取表单字段的name属性值
					String fileName=it.getFieldName();
					//判断是否是普通的表单字段
					if(it.isFormField()){
					//获取表单字段的name属性值
					
					if(fileName.equals("ename1")){
						 ename=it.getString("UTF-8");
								
					}else if(fileName.equals("jsrys")){
						jsry=it.getString("UTF-8");
					}
					}
					else{
						String file=it.getName();
				     if(file!=null&&!file.equals("")&&(file.endsWith("jpg")||file.endsWith("png"))&&fileName.equals("imgfile")){
							File imgfile=new File(uploadfile,file);
							imgFile=file;
							try {
								it.write(imgfile);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.print("图片上传成功");
						}else{
							File fujian=new File(uploadfile,file);
							introduction=file;
							try {
								it.write(fujian);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.print("文件上传成功");
						}
							
					}
					
				}
			}  
			eq.setEname(ename);
			eq.setJsryid(Integer.parseInt(jsry));
			eq.setEquipmentImage(imgFile);
            eq.setIntroduction(introduction);
            int result=equipmentService.insertEquipment(eq);
            if(result!=0){
            	System.out.print(result);
            	attr.addFlashAttribute("eid", paymentID);
            	attr.addFlashAttribute("mess", "添加成功");
            }
			return "redirect:insert";
	
   }
	
	
	
	
	
	@RequestMapping(value="queryEquipment")
	
	public String queryEquipment(Page page,RedirectAttributes redirectAttributes,Model model,HttpServletRequest request){
		 //组装page对象,传入方法中查询列表 回显数据
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
        	queryCondition = page.getqueryCondition();//查询条件
        }
        
        List<Equipment> eqList=geteqList(page);
        
        
        p.setStartRow(startRow);
        
        Integer totalCounts = equipmentService.searchTotalCount(page);//总条数
		
		 int totalPages=(totalCounts%pageSize==0)?(totalCounts/pageSize):(totalCounts/pageSize+1);//总页数=总条数/页大小+1

	        p.setTotalPageCount(totalPages);//总页数

	        page.setTotalCount(totalCounts);//总行数

	        redirectAttributes.addFlashAttribute("eqList", eqList);

	        redirectAttributes.addFlashAttribute("page", page);
		return "redirect:home";
	}
	 private List<Equipment> geteqList(Page page) {
	        
	        List<Equipment> eqList =null;
	        
	        if (page.getqueryCondition()==null) {
	            
	            eqList = equipmentService.queryEquipment(page);
	            
	            return eqList;
	            
	        }
	        
	         eqList = equipmentService.geteqList(page);
	        
	        
	        return eqList;
	        
	    }
	
	
	
	
	
	
	
	
	
	/**
     * 根据当前页获取开始行
     * @param currentPage
     * @param pageSize
     * @return
     */
    
    public int getStartRowBycurrentPage(int currentPage,int pageSize){
        
        int startRow=0;
        
        if (currentPage==1) {
            
            return startRow=0;
        }
        
        startRow=(currentPage-1)*pageSize;
        
        return startRow;
        
    }


	@RequestMapping(value="queryOne")
	public String queryOne(HttpServletRequest request){
		String id=request.getParameter("id");
		Equipment eq=equipmentService.queryOne(id);
		request.getSession().setAttribute("eqOne", eq);
		return "update";
	}
   @RequestMapping(value="updateEquipment")
  public String updateEquipment(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   response.setContentType("text/html");
		Equipment eq=new Equipment();
        
		//这种方法主要通过if (item.isFormField())这个条件判别文件还是非文件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = null;
		String id=null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} // 解析request请求
		Iterator iter = items.iterator();// 遍历表单中提交过来的内容
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) { // 如果是表单域 ，就是非文件上传元素
				String value = item.getString("UTF-8"); // 获取value属性的值，这里需要指明UTF-8格式，否则出现中文乱码问题
				if (item.getFieldName().equals("eid")) {// 对应form中属性的名字
					eq.setId(value);
					System.out.print(value);
				} else if (item.getFieldName().equals("eqname")) {
					eq.setEname(value);
				}
//				else if (item.getFieldName().equals("eqsyz")) {
//					eq.setTdzid(value);
//					if(value!="0"){
//						eq.setSbzt(3);
//					}else{
//						eq.setSbzt(1);
//					}
//				}
				else if (item.getFieldName().equals("eqjsry")) {
					eq.setJsryid(Integer.parseInt(value));
				}
			}else{
				String filename = item.getName(); // 文件的名字

				
				
				if(filename!=null&&!filename.equals("")){
					String path = request.getServletContext().getRealPath("upload/"); //target找到img位置
					if(filename.endsWith("jpg")||filename.endsWith("png")&&item.getFieldName().equals("upfile")){
					File imgfile=new File(path,filename);
					System.out.print(imgfile);
					eq.setEquipmentImage(filename);
					try {
						item.write(imgfile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print("图片上传成功");
				   }else if(!filename.endsWith("jpg")&&!filename.endsWith("png")&&item.getFieldName().equals("eqintroduction")){
					File fujian=new File(path,filename);
					eq.setIntroduction(filename);
					try {
						item.write(fujian);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print("文件上传成功");
				   }
				}
					
			}
		

		}
		int Result=equipmentService.updateEquipment(eq);
		if(Result!=0){
			return "redirect:queryEquipment";
		}else{
			return "No";
		}
   
   }
   //下载文件
   @RequestMapping(value="/downloadFile")
   public void download(@RequestParam(value="introduction")String fileName,
           HttpServletRequest request,
           HttpServletResponse response,Model model) throws IOException {
	   request.setCharacterEncoding("UTF-8");
	   if(fileName!=null&&!fileName.equals("")){
			//String fileName=request.getParameter("fileName");
			//设置下载的消息头,二进制文件（octet-stream）操作
			response.addHeader("Content-Type","application/octet-stream");//msword以文件方式打开
			//设置服务器处理方式
			response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
			
			//获取服务器上被下载的文件的输入流
		InputStream input=request.getSession().getServletContext().getResourceAsStream("/upload/"+fileName);
		OutputStream out=response.getOutputStream();
		byte[] buffer=new byte[1024];
		int len;
		while((len=input.read(buffer))!=-1){
			out.write(buffer,0,len);
		}
		
		input.close();
		out.flush();
		out.close();
		}
   }
   @RequestMapping(value="deleteEquipment")
   public String deleteEquipment(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   String id=request.getParameter("id");
	   System.out.print(id);
	   int result=0;
	   
	   if(id!=null){
		   result=equipmentService.deleteEquipment(id);
	       System.out.print(result);
	   }
	   
	   
	   return "redirect:queryEquipment";
   }
   @RequestMapping(value="deteleEquipments")
   public void deteleEquipments(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   String eqMess=request.getParameter("eqMess");
	   String[] ids=eqMess.split(",");
	   PrintWriter out=response.getWriter();
	   int result=0;
	   for(int i=0;i<ids.length;i++){
		   result=equipmentService.deleteEquipment(ids[i]);
	       
		  
	   }
	   System.out.print(result);
	   if(result!=0){
    	   out.print("true");
       }else{
    	   out.print("false");
       }
	   out.flush();
	   out.close();
   }
   @RequestMapping(value="queryNotSyz")
  public String queryNotSyz(RedirectAttributes redirectAttributes){
	  List<Equipment> eqlist=equipmentService.queryNotUser();
	  redirectAttributes.addFlashAttribute("eqlist", eqlist);
	  return "redirect:fenpei";
	  
  }
   @RequestMapping(value="updateSyz")
   public void updateSyz(HttpServletRequest request,HttpServletResponse response) throws IOException{
	  String id= request.getParameter("eqid");
	  String syzid=request.getParameter("syzid");
	  boolean flag=equipmentService.updateSyz(syzid, id);
	  PrintWriter out=response.getWriter();
	  if(flag==true){
		  out.print("true");
	  }else{
	   out.print("false");
	  
	  }
	  out.flush();
	  out.close();
   }
	@RequestMapping(value="getEq",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getEq(String id,HttpServletRequest request)throws IOException{
		id= new String(id.getBytes("ISO-8859-1"),"UTF-8");
		Equipment eq=equipmentService.queryOne(id);
		Gson gson = new Gson();
		return gson.toJson(eq);
	}
	@RequestMapping(value="queryMyeq",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryMyeq(String openid)throws IOException{
		openid= new String(openid.getBytes("ISO-8859-1"),"UTF-8");
		Equipment eq=equipmentService.queryMyeq(openid);
		Gson gson = new Gson();
		return gson.toJson(eq);
	}
	@RequestMapping(value="/tjjxd",produces="application/json;charset=utf-8")
	@ResponseBody
	public String tjjxd(String eid,String ename,String eqzt,String tjzid,String tdzxm,String lxfs){
		Equipment eq=new Equipment();
		eq.setEname(ename);
		eq.setId(eid);
		eq.setTdzid(tjzid);
		eq.setSbzt(Integer.parseInt(eqzt));
		if(eqzt.equals("3")){
			tdzxm="";
			lxfs="";
		}
		System.out.println(tdzxm);
		eq.setTdzxm(tdzxm);
		eq.setLxfs(lxfs);
		Gson gson = new Gson();
		int result=equipmentService.tjjxd(eq);
		if(result!=0){
			
			return gson.toJson("true");
		}else{
			return gson.toJson("false");
		}
		
	}
	@RequestMapping(value="/queryMyjxd",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryMyjxd(String tdzid){
		List<Equipment> myjxd=equipmentService.queryMyjxd(tdzid);
		Gson gson = new Gson();
		if(myjxd.size()>0){
		
		return gson.toJson(myjxd);
		}else{
			return gson.toJson("false");
		}
	}
	@RequestMapping(value="querydjxeq")
	
	public String querydjxeq(HttpServletRequest request,Model model){
		String name=request.getParameter("username");
		List<Equipment> jxList=equipmentService.querydjxeq(name);
		if(jxList.size()>0){
		  model.addAttribute("jxList", jxList);
		  model.addAttribute("mess", 1);
		}else{
			model.addAttribute("mess", 0);
		}
		return "jxd";
		
	}
	//修改检修时间
	@RequestMapping(value="/updatejxsj",produces="application/json;charset=utf-8")
    public void updatejxsj(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String jxsj=request.getParameter("jxsj");
		String id=request.getParameter("eid");
		PrintWriter out=response.getWriter();
		boolean result=equipmentService.updatejxsj(jxsj, id);
		if(result==true){
			out.print("true");
		}else{
			out.print("false");
		}
	}
	
}
