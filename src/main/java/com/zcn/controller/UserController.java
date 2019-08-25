package com.zcn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.zcn.pojo.User;
import com.zcn.service.UserService;

@Controller
public class UserController {
  @Autowired
  private UserService userService;
  @RequestMapping(value="/insertUserInfo")
	@ResponseBody
	public String insertUser(String openid, String nickName, String avatarUrl, String province, String city ) throws IOException{
		//当放到服务器上时，需要把编码转换注释掉
		nickName= new String(nickName.getBytes("ISO-8859-1"),"UTF-8");
		province= new String(province.getBytes("ISO-8859-1"),"UTF-8");
		city= new String(city.getBytes("ISO-8859-1"),"UTF-8");
		
		/**
		 * 先查询数据库中是否存在此用户，若有则不插入，若没有，则把当前用户插入数据库
		 */
		User userone = userService.queryUserByOpenid(openid);

		if(userone == null){
			User user = new User();
			user.setAvatarUrl(avatarUrl);;
			user.setNickName(nickName);
			user.setOpenid(openid);
			user.setProvince(province);
			user.setCity(city);
			userService.insertUser(user);
			return "true";
		}else{
			return "true";
		}
	}
	
	/**
	 * 根据用户编号查询对应用户的信息
	 * @param openid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/queryUserByOpenid",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryUserByOpenid(String openid)throws IOException{
		User userone = userService.queryUserByOpenid(openid);
		Gson gson = new Gson();
		return gson.toJson(userone);
	}
//	@RequestMapping(value="queryAll")
//	public String queryAll(HttpServletRequest request){
//		List<User> uList=userService.queryAll();
//		request.getSession().setAttribute("uList",uList);
//		return "update";
//	}
    @RequestMapping(value="fenpei")
    public String queryNotEq(HttpServletRequest request,Model model){
    	List<User> userList=userService.queryNoteq();
        model.addAttribute("userList", userList);
        return "fenpei";
    }
}
