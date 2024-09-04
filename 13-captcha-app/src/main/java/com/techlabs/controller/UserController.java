package com.techlabs.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techlabs.entity.CaptchaUtil;
import com.techlabs.entity.Users;
import com.techlabs.service.IUserService;

import cn.apiclub.captcha.Captcha;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@GetMapping("/register")
	public String registerUser(Model model) {
		Users user = new Users();
		getCaptcha(user);
		model.addAttribute("user", user);
		return "registerUser";
	}
	
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute Users user,
			Model model
			) {
		if(user.getCaptcha().equals(user.getHiddenCaptcha())) {
		service.createUser(user);
		model.addAttribute("message", "User Registered successfully!");
		return "redirect:allUsers";
		} 
		else {
		model.addAttribute("message", "Invalid Captcha");
		getCaptcha(user);
		model.addAttribute("user", user);
		}
		return "registerUser";
	}
	
	@GetMapping("/allUsers")
	public String getAllUsers(Model model) {
		List<Users> userList= service.getAllUsers();
		model.addAttribute("userList", userList);
		return "listUsers";
	}
	
	private void getCaptcha(Users user) {
		Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
		user.setHiddenCaptcha(captcha.getAnswer());
		user.setCaptcha(""); // value entered by the User
		user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
		
	}
	
	
}