package com.lostandfound.services;

import java.util.Optional;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lostandfound.Repository.UserRepository;
import com.lostandfound.bean.UserModel;
import com.lostandfound.entities.User;
import com.lostandfound.utils.Validator;

@Component
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userModelRepository;

	@Autowired
	private Validator validator;

	@Override
	public String registerUser(UserModel user)throws Exception {

		try {

			validator.validateRegistrationDetails(user);

			User userModel = new User();
			userModel.setName(user.getName());
			userModel.setPhoneNumber(user.getPhoneNumber());
			userModel.setEmailId(user.getEmailId());
			userModel.setAddress(user.getAddress());
			userModel.setIsActive(true);

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			userModel.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

			userModelRepository.save(userModel);

			return "User registered successfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
	}
	
	@Override
	public boolean isAdmin(String mobileNumber)
	{
		try
		{
			Optional<User> userOptional=userModelRepository.findByPhoneNumberAndIsActiveTrueAndIsAdminTrue(mobileNumber);
			if(userOptional.isPresent())
			{
				return true;
			}
			else 
				return false;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

}
