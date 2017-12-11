package com.lostandfound.services;

import com.lostandfound.bean.UserModel;

public interface UserService {
	
	public String registerUser(UserModel user) throws Exception;
	
	public boolean isAdmin(String mobileNumber) throws Exception;

}
