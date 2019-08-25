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
		
		<link rel="stylesheet" href="css/feipei.css" />
		<script type="text/javascript" src="js/jquery-1.11.2.min.js" ></script>
		<script type="text/javascript" src="js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/home.css" />
		<script type="text/javascript">
		$(function(){
			
			$("#fenpei").click(function(){

				var syzid=$("#person").val();
				var eqid=$("#eqid").val();
				var args={
					"syzid":syzid,
					"eqid":eqid
				}
				var url="${pageContext.request.contextPath}/updateSyz"
			   $.post(url,args,function(data){
			    	if($.trim(data)=="true"){
						window.location.href="${pageContext.request.contextPath}/queryNotSyz";
						
					}else{
						alert("分配失败！");
						
					}
			   })			
			   
		    });
		$("#fpeq").click(function(){
			window.location.href="${pageContext.request.contextPath}/queryNotSyz";
		});
		$("#gleq").click(function(){
			window.location.href="${pageContext.request.contextPath}/queryEquipment";
		})	
	})
		
		
		
		
		
		
		
		
</script>
	</head>
	<body>
		<div class="head">
			<div class="title"><h3>电力设备管理系统</h3></div><div class="mess"><p>您好，<span id="lbluser">zcn</span>|&nbsp;<a href="#">退出</a></p></div>
		</div>
		<div class="content">
			<div class="menu">
	  <p><button type="button" id="gleq" class="btn btn-default" style="background:gainsboro;">
      <i class="glyphicon glyphicon-edit">管理设备</i>
   </button></p>
   
   <p><button type="button" id="fpeq" class="btn btn-default">
      <i class="glyphicon glyphicon-star">分配设备</i>
   </button></p>
   <p>
	<button type="button" class="btn btn-default">
      <i class="glyphicon glyphicon-wrench">维护设备</i>
   </button></p>
   <p>
	<button type="button" class="btn btn-default">
      <i class="glyphicon glyphicon-user">人员管理</i>
   </button></p>
  <p> <button type="button" class="btn btn-default">
      <i class="glyphicon glyphicon-ok-sign">角色权限</i>
   </button></p>		
			</div>
	<div class="section">
		<div class="shead"><input type="text" placeholder="搜索设备"/>&nbsp;<button><i class="glyphicon glyphicon-search"></i></button></div>
		<div class="mind">
			
		     <div class="feipei">
		     	待分配的人：<select id="person" name="person">
                          <c:forEach var="user" items="${userList}">
                          <option value="${user.openid}">${user.nickName}</option>
                          </c:forEach>
                          
                      </select>
		     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		     	待分配的设备：<select id="eqid" name="eqid">
		     		 <c:forEach var="e" items="${eqlist}">
                          <option value="${e.id}">${e.ename}</option>
                          </c:forEach>
		     	</select>
		     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		     	<input type="button" class="btn-default" id="fenpei" value="分配"/>
		     </div>
		     
		     <table class="table table-hover" style="width: 90%;">
		     	<caption>待分配的设备列表</caption>
				 <thead>
				<tr>
					
					<th>设备名称</th>
					<th>使用者</th>
					<th>技术人员</th>
					<th>联系方式</th>
					
				</tr>
				</thead>
				<tbody>
				<c:forEach var="eq1" items="${eqlist}">
				<tr>
					<td>${eq1.ename}</td>
					<td>${eq1.user.nickName }</td>
					<td>${eq1.manager.name}</td>
					<td>${eq1.manager.tel }</td>
					</tr>
					</c:forEach>
			
				</tbody>
				
			</table>
			</div>
		</div>
	</div>
			
		</div>
		<div class="foot">
			<p>版权&copy;韩山师范学院 计算机与信息工程学院 信息管理与信息系统 2015117077 周纯娜</p>
		</div>
		
	</body>
</html>
