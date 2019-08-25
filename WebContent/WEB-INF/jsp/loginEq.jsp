<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css" />

	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery-1.11.2.min.js" ></script>
		<script type="text/javascript" src="js/bootstrap.js" ></script>
<style>
.login{
	margin:100px auto auto 35%;
	width:380px;
	height: 320px;
	text-align: center;
	background: white;
	font-family: "微软雅黑";
	font-size:15px;
	padding: 10px 20px;

	border: 1px #1B6D85 solid;
	border-radius: 10px;
    
}
.log{
	width: 230px;
	height: 40px;
	background-color: rgb(130 219 233);
	/*rgb(69 229 229);*/
	border-radius: 8px;
	font-family: "微软雅黑";
	font-size: 20px;
	color: white;
}
a{
	text-decoration: none;
}

/*.register{
	width: 50%;
	height: 30px;
	float: left;
	
	margin-bottom: 5px;
	
}
.reset{
	width: 50%;
	float: left;
	height: 30px;
	margin-bottom: 5px;
}
*/
</style>
<script>
$(function(){
	var msg="${msg}";

	if(msg=="您还没有登录请先登录"){
		alert(msg);
	};
	var mess="${mess}";
	if(mess=="用户名或密码错误，请重新输入！"){
		alert(mess);
	}
	var result="${result}";
	if(result=="重置密码成功！！"||result=="重置密码失败！！"){
		alert(result);
	}

	
	
	
	
	
	
	
	
	
	
	
	

})


function changeName(){
	    var name=$("#username").val();
	 	var sname=$(".sname");
 			$.ajax({
				url:"${pageContext.request.contextPath}/queryName",
				type:"post",
				data:"name="+name,
				success:function(res){
					if($.trim(res)=="true"){
						sname.html("√");
					   sname.css("color","rgb(69 229 229)");
					 
					}else{
						sname.html("用户不存在！！！");
					    sname.css("color","red");
						
					}
					
				}
				
				
			});
 
  	     
  	     
}














function check(){
	
	var name=$("#name").val();
	var pwd=$("#pwd").val();
	if(name==""||pwd==""){
		alert("用户名或者密码不为空！！！");
		return false;
	}	
	
	

}

</script>
	</head>
	<body>
		<!--<div id="background" style="position:absolute;z-index:-1;width:100%;height:100%;top:0px;left:0px;"><img src="img/1.jpg" width="100%" height="100%"/></div>
	-->	

		<div class="login">
			<!--  <div class="Eqlog"><div class="EgImg"><img src="img/log.jpg" width="100%" height="100%"></img></div></div>
			<div class="neirong">-->
			<h2>电力设备管理系统</h2>	
		    <p>——登录  LOGIN——</p>
			
			<form action="${pageContext.request.contextPath}/loginEq" id="form1" method="post"  onsubmit="return check()">
				
			<p>用户名：<input type="text" name="name" id="name" class="username" placeholder="请输入用户名"></p>
			<P>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="pwd" class="pwd" name="pwd" placeholder="请输入密码"></p>
			
		   <!--   <a href="${pageContext.request.contextPath}/register" id="register" class="register">没账号?</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
		   <a href="#myModal" data-toggle="modal"  id="forget" class="forget">忘记密码?</a>
		        <p><input type="submit" class="log" id="login" value="登录"></p> 
		    </form>
		   </div>
	
		
		
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <form action="${pageContext.request.contextPath}/forget" method="post">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                
            </div>
            <div class="modal-body">
            	<div class="cont" id="cont">
            		<p>用户名：<input type="text" placeholder="请输入用户名" id="username" name="username" onchange="changeName()"><span class="sname">*</span></p>
            		<p>重置密码后的密码为初始密码：123456</p>
            	</div>
            	
            	
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <input type="submit" class="btn btn-primary" style="background:rgb(130 219 233);border:1px solid white;" value="重置密码"/>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
    </form>
</div>		
		
</body>
</html>