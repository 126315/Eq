<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    <% %>
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
        <link rel="stylesheet" href="css/insert.css" />
<script type="text/javascript">
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
	$("#ename1").blur(function(){
		var ename1=$("#ename1").val();
		var sename=$("#sename");
		if(ename1==""||ename1.length<=0){
			sename.html("设备名称不能为空");
			
		}else{
			sename.html("√");
			sename.css("color","rgb(130 219 233)");
		}
	});
	$("#save").click(function(){
		var ename1=$("#ename1").val();
		if(ename1==""||ename1.length<=0){
		  alert("设备名称不能为空！！！");
		  return false;
		}else{
			return true;
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
function insert(img1){
	
	var file =img1.files[0];
	if (!/image\/\w+/.test(file.type)) { // html中已经用accept='image/*'限制上传的是图片了，此处判断可省略
		alert("文件必须为图片！");
		
	    return false;
	}else{
		
	    return true;
	}
	

}

function insertFile(){
	var rgx= "(.docx|.doc|.xls|.xlsx|.pdf|.ppt|.txt)$"; 
	var re=new RegExp(rgx);
	var file_name=$("#introduction").val();

	var result =/\.[^\.]+/.exec(file_name);

	if(!re.test(result)){
		alert("上传的文件格式不正验，请重新选择!!");
		return false;
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
		<div class="mind">
			
       
            <div class="left">
            <form action="${pageContext.request.contextPath}/insertEquipment" enctype="multipart/form-data" method="post">
                <p>设备名称：<input type="text" name="ename1" class="ename1" id="ename1"/>&nbsp;<span id="sename" style="color:red">*</span></p>
            	<p>技术人员：<select id="jsrys" name="jsrys">
                          <c:forEach var="m" items="${list}">
                          <option value="${m.mid}">${m.name}</option>
                          </c:forEach>
                          </select></p>
            	<p>设备图片<input type="file" name="imgfile" class="imgfile" id="imgfile" onchange="insert(this)"/></p>
            	<p>说明文档<input type="file" name="introduction" class="introduction" id="introduction" onchange="insertFile()"/></p>
            	<p>
                
                <button type="submit" id="save" class="btn btn-primary" style="background:rgb(130 219 233);border:1px solid white;margin-left:20px;width:200px;">提交</button>
           </p>
             </form>
            	</div>
            	<div class="right" >
            	
                <div id="qrcode" class="md-qrcode-img md-center"></div>
               
                <button type="button" class="btn btn-default" onclick="downloadIamge()">下载</button>
                <button type="button" onclick="onGeQRCode()" class="btn btn-primary" style="background:rgb(130 219 233);border:1px solid white;" >生成二维码</button>
                
              
            	
            	
            	</div>
           
            
             
           

        
			
			
		</div>
	</div>
		</div>	
		
		<div class="foot">
			<p>版权&copy;韩山师范学院 计算机与信息工程学院 信息管理与信息系统 2015117077 周纯娜</p>
		</div>
<script type="text/javascript">
           $(function(){
            var mess="!${mess}";
            if(mess!="!")
            alert("${mess}");
            
           })
            var qrcode = new QRCode(document.getElementById("qrcode"), {
                width: 140,
                height: 140,
                colorDark: '#000000', //前景色
                colorLight: '#ffffff', //背景色
                correctLevel: QRCode.CorrectLevel.H //容错级别，可设置为：QRCode.CorrectLevel.L QRCode.CorrectLevel.M QRCode.CorrectLevel.Q QRCode.CorrectLevel.H
            });
            function makeCode(val) {
                qrcode.makeCode(val);
            }
           //生成二维码
            function onGeQRCode() {
                var url="${eid}";
                makeCode(url);
            }
            //下载二维码
            function downloadIamge() {
                var imgs = document.getElementById("qrcode").getElementsByTagName("img")
                var a = document.createElement('a')
                a.download = 'xds' + new Date().getTime() || '下载图片名称'
                a.href = imgs[0].src
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
            }
        </script>
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