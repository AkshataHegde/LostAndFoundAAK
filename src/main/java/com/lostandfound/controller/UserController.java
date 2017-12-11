package com.lostandfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lostandfound.bean.UserModel;
import com.lostandfound.services.UserService;

@RestController
@EnableAutoConfiguration
@RequestMapping("rest/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String UserRegistration(@RequestBody UserModel user) throws Exception {
		try {
			return userService.registerUser(user);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/isAdmin/{phoneNumber}", method = RequestMethod.POST)
	public boolean isAdmin(@PathVariable(value = "phoneNumber") String phoneNumber) throws Exception
	{
		try
		{
			return userService.isAdmin(phoneNumber);
		}
		catch(Exception e)
		{
			throw e;
		}
	}

}
