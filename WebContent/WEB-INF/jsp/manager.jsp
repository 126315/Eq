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
<script type="text/javascript">
$(function(){
	var result="${result}";
	if(result=="删除失败,该员工有管理电力设备!"){
		alert(result);
	}
	
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
	$("#gleq").click(function(){
		window.location.href="${pageContext.request.contextPath}/queryEquipment";
	});
	$("#all").click(function(){
		   
		   if(this.checked==true){
			  
			   $("input[name='checkId']").attr('checked',true);
		   }else{
			   $("input[name='checkId']").attr('checked',false);
		   }
	   });
$("input[name='checkId']").click(function(){
if(all.checked==true){
	 if(this.checked==false){
		 $("#all").attr('checked',false);
	 }
	}
});
})
function del(){
	var ygMess='';
	   var dd='';
	   var checks=$(".checkId");
	   for(var i=0;i<checks.length;i++){
		   if(checks[i].checked==true){
			   dd=checks[i].value;
			   ygMess=dd+','+ygMess;
		   }
	   }
	   
	   var args={"ygMess":ygMess};
	   var url="${pageContext.request.contextPath}/deleteManagers";
	   $.post(url,args,function(data){
		    
	    	if($.trim(data)=="true"){
				window.location.href="${pageContext.request.contextPath}/manager";
				
			}else{
				alert("删除失败,该员工有管理电力设备!");
				
			}
	   })
	  


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
	
   <p><button type="button" id="gleq" class="btn btn-default">
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
		<form action="${pageContext.request.contextPath}/manager" method="post">
		<div class="shead"><input type="text" name="queryCondition"
       value="${page.queryCondition}" id="condition" placeholder="搜索人员"/>&nbsp;<button type="submit"><i class="glyphicon glyphicon-search"></i></button></div>
       </form>
		<div class="mind">
		<div class="add">
		<a id="addManager" href="${pageContext.request.contextPath}/register">添加员工</a>|
		<a href="javascript:void(0);" onclick="del()">批量删除</a></div>
				<table class="table table-hover" style="width: 90%;">
				 <thead>
				<tr>
					<th><input type="checkbox" id="all" name="all" class="all"/></th>
					<th>账号</th>
					<th>密码</th>
					<th>身份</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="m" items="${Mlist}">
				<tr>
					<td><input type="checkbox" name="checkId" class="checkId" value="${m.mid}"/></td>
					<td>${m.name}</td>
					<td>${m.password}</td>
					<td>${m.role.identifymc}</td>
					<td>${m.tel}</td>
					<td>${m.email}</td>
					<td><a href="${pageContext.request.contextPath}/queryManagerOne?mid=${m.mid}">修改</a>|
					<a href="${pageContext.request.contextPath}/deleteManager?mid=${m.mid}">删除</a></td>
				</tr>
				</c:forEach>
			   </tbody>
			   </table>
			   <br /> 

   <div class="pageSelect">
   <label>第${page.currentPage}/${page.totalPageCount}页 共${page.totalCount}条</label> 
   <a href="${pageContext.request.contextPath}/manager?currentPage=0">首页</a> 
   <a href="${pageContext.request.contextPath}/manager?currentPage=${page.currentPage-1}" onclick="return checkFirst()">上一页</a> 
   <a href="${pageContext.request.contextPath}/manager?currentPage=${page.currentPage+1}" onclick="return checkNext()">下一页</a> 
   <a href="${pageContext.request.contextPath}/manager?currentPage=${page.totalPageCount}">尾页</a> 跳转到:
   <input type="text" style="width:30px" id="turnPage" />页 
   <input type="button" onclick="startTurn()" value="跳转" /> 
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
<script type="text/javascript">
    
    function checkFirst(){
         if(${page.currentPage>1}){
         
           return true;
         
         }
         alert("已到页首,无法加载更多");
        
       return false;
    }
    
    function checkNext(){
    
    if(${page.currentPage<page.totalPageCount}){
    
      return true;
    
    }
    alert("已到页尾，无法加载更多页");
    return false;
    
    }
    
    
    function startTurn(){
    
    var turnPage=document.getElementById("turnPage").value;
    
    if(turnPage>${page.totalPageCount}){
    
      alert("对不起已超过最大页数");
     
      return false;
    
    }
    
    var shref="${pageContext.request.contextPath}/manager?currentPage="+turnPage;
    
    window.location.href=shref;
}
</script>