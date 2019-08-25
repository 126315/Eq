<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/up.css" />
		<script type="text/javascript" src="js/jquery-1.11.2.min.js" ></script>
		<script type="text/javascript" src="js/bootstrap.js" ></script>
		<link rel="stylesheet" href="css/home.css" />
<script type="text/javascript">
$(function(){
	 $("#eqImg").attr("src","upload/${eqOne.equipmentImage}");

	$("#changePwd").click(function(){
		var Newpwd=$("#Newpwd").val();
		var name=$("#name").val();
		var url="${pageContext.request.contextPath}/changePwd";
		var args={
			"NewPwd":Newpwd,
			"name":name
		}
		 $.post(url,args,function(data){
			    
		    	if($.trim(data)=="true"){
					alert("修改成功！！！")
				}else{
					alert("修改失败！！！");
					
				}
		   });
	});
   
   
	$("#upImg").click(function(){
		$("#upfile").click();
		
	})

    var syzid="${eqOne.user.openid}";
	$("#eqsyz option[value = '"+syzid+"']").attr("selected",true);
	var jsryid="${eqOne.manager.mid}";
	$("#eqjsry option[value = '"+jsryid+"']").attr("selected",true);
	var sbzt="${eqOne.sbzt1.sid}";
	$("#eqzt option[value = '"+sbzt+"']").attr("selected",true);
  $("#upWord").click(function(){
	  $("#eqintroduction").click();
	  
  });
  
  
  
  $("#down").click(function(){
	  var eqFile="${eqOne.introduction}";
	  
	  if("${eqOne.introduction}"==null||"${eqOne.introduction}"==""||"${eqOne.introduction}".length==0){
		  alert("找不到文件！！！");
		  return false;
	  }
	  else{
		  
      window.location.href="${pageContext.request.contextPath}/downloadFile?introduction=${eqOne.introduction}";
	  }
  });
  $("#gleq").click(function(){
		window.location.href="${pageContext.request.contextPath}/queryEquipment";
	});
	
	$("#wheq").click(function(){
		var identify="${manager.role.identifymc}";
		if(identify=="检修人员"){
			window.location.href="${pageContext.request.contextPath}/querydjxeq?username="+${manager.name};
			return true;
		}else{
			alert("您没有权限访问！！！");
			return false;
		}
	});
	$("#rygl").click(function(){
		var identify="${manager.role.identifymc}";
		if(identify=="系统管理员"){
		  window.location.href="${pageContext.request.contextPath}/manager";
		  return true;
		}else{
			alert("您没有权限访问！！！");
			return false;
		}
	});
  
  
  
})
function upFile(img1){
	var imgObjPreview = document.getElementById("eqImg");
	var file =img1.files[0];
	if (!/image\/\w+/.test(file.type)) { // html中已经用accept='image/*'限制上传的是图片了，此处判断可省略
		alert("文件必须为图片！");
		imgObjPreview.src="upload/${eqOne.equipmentImage}";
	    return false;
	}else{
		imgObjPreview.src = window.URL.createObjectURL(file);
	    return true;
	}

}

function up(){

	var rgx= "(.docx|.doc|.xls|.xlsx|.pdf|.ppt|.txt)$"; 
	var re=new RegExp(rgx);
	var file_name=$("#eqintroduction").val();

	var result =/\.[^\.]+/.exec(file_name);
	if(!re.test(result)){
		alert("上传的文件格式不正验，请重新选择!!");
	}

}

function out(){
	var mess=confirm("你确定要退出系统？？？");
	if(mess==true)
		window.location.href="${pageContext.request.contextPath}/logout"
	
	
}

</script>
	</head>
	<body>
		<div class="head">
			<div class="title"><h3>电力设备管理系统</h3></div><div class="mess"><p>您好，<span id="lbluser">${manager.name}</span>|&nbsp;身份：${manager.role.identifymc}|&nbsp;<a href="#myModal" data-toggle="modal" style="color:white">修改密码</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="out()">退出</a></p></div>
		</div>
		<div class="content">
			<div class="menu">
	
   <p><button type="button" id="gleq" class="btn btn-default" style="background:#707070;">
      <i class="glyphicon glyphicon-edit">管理设备</i>
   </button></p>
   
   <p>
	<button type="button" class="btn btn-default" id="wheq">
      <i class="glyphicon glyphicon-wrench">维护设备</i>
   </button></p>
    <p>
	<button type="button" id="rygl" class="btn btn-default">
      <i class="glyphicon glyphicon-user">人员管理</i>
   </button></p>		
			</div>
	<div class="section">
		<div class="shead"></div>
		<form action="${pageContext.request.contextPath}/updateEquipment" enctype="multipart/form-data" method="post">
		<div class="mind">
		
			<div class="up">
			
			<div class="upimg">
			
			<div class="localImag" style="width:100px;height:100px;">
			<img id="eqImg"  width="100px" height="100px"/>
			</div>
			
			<input type="file" id="upfile" onchange="upFile(this)"  name="upfile" style="display:none">
				<button type="button" class="btn btn-default" id="upImg">修改图片</button>			
			</div>
			
			
			<div class="upright">
			<input type="text" name="eid" value="${eqOne.id}" style="display:none">
			<p>设备名称：<input type="text" class="eqname" id="eqname" name="eqname" value="${eqOne.ename}"/></p>
		 <!-- <p>使用人员：<select id="eqsyz" name="eqsyz"  style="width:156px;height:28px;">
                          <c:forEach var="u" items="${uList}">
                          <option value="${u.openid}">${u.nickName}</option>
                          </c:forEach>
                          
                      </select></p>
            -->
			<p>技术人员：<select id="eqjsry" name="eqjsry" style="width:156px;height:28px;">
                          <c:forEach var="manager" items="${list}">
                          <option value="${manager.mid}">${manager.name}</option>
                          </c:forEach>
                          
                        </select></p>
			<p>联系方式：<input type="text" class="phone" id="phone" value="${eqOne.manager.tel}" disabled="disabled"></p>
	
			 <!-- <p>设备状态：<input type="text" class="sbzt" id="sbzt" value="${eqOne.sbzt1.ztmc}" disabled="disabled"></p>-->
			 <p>
			 	说明文档：<input type="file" id="eqintroduction" name="eqintroduction" onchange="up()" style="display: none;"/><i class="glyphicon glyphicon-file"></i>
			 	<input type="button" id="down" value="查看">&nbsp;<input type="button" id="upWord" value="上传">
			 </p>
			
			 <p><input type="submit" class="btn-primary" id="save"  style="width:170px; height:30px;margin-left:30px; background:rgb(130 219 233); border: 1px white solid; border-radius: 5px;" value="保存"/></p>
			             
			</div>
			
			</div>
			
		</div>
		</form>
	</div>
			
		</div>
		
		<div class="foot">
			<p>版权&copy;韩山师范学院 计算机与信息工程学院 信息管理与信息系统 2015117077 周纯娜</p>
		</div>
		<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					修改密码
				</h4>
			</div>
			<div class="modal-body">
				<p>您的用户名：<input type="text" id="name" value="${manager.name}" disabled="disabled"/></p>
				<p>修改密码为：<input type="text" placeholder="请输入密码" id="Newpwd"><span id="spwd">不小于6个字符，可以是字母，数字，符号等</span><p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" style="background:rgb(130 219 233);border:1px solid white;" id="changePwd">
					提交更改
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
	</body>
</html>
