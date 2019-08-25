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
		<script type="text/javascript" src="js/jquery-1.11.2.min.js" ></script>
		<script type="text/javascript" src="js/bootstrap.js" ></script>
		<script type="text/javascript" src="js/jquery.min.js" ></script>
	    <script type="text/javascript" src="js/qrcode.js" ></script>
		<link rel="stylesheet" href="css/home.css" />
<script>

   $(function(){
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
			   })
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
		var sfid="${manager1.role.id}";
		$("#identify option[value = '"+sfid+"']").attr("selected",true);
		
		
		$("#identify").blur(function(){
		    var mid="${manager1.mid}";
		 	var sname=$("#sidentify");
	 			$.ajax({
					url:"${pageContext.request.contextPath}/queryjsry",//访问地址，用于web.xml的配置
					type:"post",
					data:"mid="+mid,
					success:function(result){
						
						if($.trim(result)=="true"){
							sname.html("该员工有负责检修的设备，修改失败！！！");
						   sname.css("color","red");
						 
						}else{
							sname.html("");
						    sname.css("color","rgb(69 229 229)");
							
						}
						
					}
					
					
				});
	 
	 		
	  	     
	  	     
	 		
		});
		
		
		
		
		
		
		
		
		
		
		
   })	
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
	
   <p><button type="button" id="gleq" class="btn btn-default" style="background:gainsboro;">
      <i class="glyphicon glyphicon-edit">管理设备</i>
   </button></p>
   
   <p>
	<button type="button" class="btn btn-default" id="wheq">
      <i class="glyphicon glyphicon-wrench">维护设备</i>
   </button></p>
    <p>
	<button type="button" id="rygl" class="btn btn-default" style="background:#707070;">
      <i class="glyphicon glyphicon-user">人员管理</i>
   </button></p>		
			</div>
	<div class="section">
		<div class="shead"></div>
		<div class="mind">
		<div class="up">
	   <form action="${pageContext.request.contextPath}/updateOne?mid=${manager1.mid}" id="font1" method="post">
       
		<p>用户名：<input type="text" id="name" class="name" name="name"  value="${manager1.name}"></p>
<!-- 		<p>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="pwd" class="pwd" name="pwd" placeholder="不少于6个字符" value="${manager.password}"></p>
		<p>确认密码：<input type="password" id="pwdvid" class="pwdvid" name="pwdvid" placeholder="再次输入密码"></p>
		
		 -->
		<p>联系方式：<input type="text" id="tel" class="tel" name="tel" value="${manager1.tel}" ></p>
		<p>电子邮箱：<input type="text" id="email" class="email" name="email" value="${manager1.email}"></p>
		<p>身&nbsp;&nbsp;&nbsp;份：<select id="identify" name="identify" style="width:146px; height:28px;">
			        <c:forEach var="i" items="${listIdentify}">
                     <option value="${i.id}">${i.identifymc}</option>
                     </c:forEach>
		         </select></p>
		<p><span id="sidentify"></span></p>
<!-- 		<div class="mess">
			
			<p><span id="sname">不能为空</span></p>
			
			<p><span id="spwd">不小于6个字符，可以是字母，数字，符号等</span></p> 
		
			<p><span id="spwdvid">密码要保存一致</span></p> 
	     
			<p><span id="stel">11位数字</span></p> 
			<p><span id="semail">格式如：123@qq.com</span></p> 
			<p>请选择身份</p> 
		</div>
		-->	
		   <p><input type="submit" class="btn btn-primary" style="background:rgb(130 219 233);border:1px solid white; width:200px;" id="save" value="保存"/><p>
	 
		  </form>
		  </div>
		  	
			
			
		</div>
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