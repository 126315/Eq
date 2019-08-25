package com.zcn.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zcn.pojo.Manager;

public class LoginInterceptor implements HandlerInterceptor{
   
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		HttpSession session=request.getSession();
		Manager manager=(Manager)session.getAttribute("manager");
		String url=request.getRequestURI();
		
		if(url.indexOf("/forget")>=0||url.indexOf("/loginEq")>=0||manager!=null
				||url.indexOf("upload")>=0||url.indexOf("inserUserInfo")>=0||url.indexOf("queryUserByOpenid")>=0
				||url.indexOf("getEq")>=0||url.indexOf("queryMyeq")>=0||url.indexOf("queryMyjxd")>=0||url.indexOf("getSbzt")>=0	
				||url.indexOf("tjjxd")>=0||url.indexOf("/queryName")>=0){
			return true;
		}
		
		
		else{
			// 拦截后进入登录页面
		
			request.setAttribute("msg", "您还没有登录请先登录");
			request.getRequestDispatcher("/WEB-INF/jsp/loginEq.jsp").forward(request, response);
			return false;
		}

	}

}
