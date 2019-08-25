package com.zcn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.zcn.pojo.Identify;
import com.zcn.service.IdentifyService;
@Controller
public class IdentifyController {
	@Autowired
	private IdentifyService identifyService;
	@RequestMapping(value="/register")
	public String queryIdentify(Model model){
		List<Identify> listIdentify=identifyService.queryIdentify();
		model.addAttribute("listIdentify", listIdentify);
		return "register";
	}
	
			@RequestMapping(value="/querySf")
			public String querySf(Model model){
				List<Identify> listIdentify=identifyService.queryIdentify();
				model.addAttribute("listIdentify", listIdentify);
				return "updateManager";
			}
}
