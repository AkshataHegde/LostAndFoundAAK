package com.lostandfound.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.lostandfound.Repository.UserRepository;
import com.lostandfound.bean.UserModel;
import com.lostandfound.entities.User;
import com.lostandfound.services.UserServiceImpl;
import com.lostandfound.utils.Validator;

@RunWith( PowerMockRunner.class )
public class UserServiceImplTest {
	
	private static final String REGISTRATION_SUCCESS_MESSAGE="User registered successfully";
	private static final String ADMIN_MOBILE_NUMBER="9482726350";
	
	@Mock
	UserModel userModel;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	Validator validator;
	
	private User user;
	
	
	
	@InjectMocks
	UserServiceImpl userServiceImpl=new UserServiceImpl();

	@Before
	public void setUp() throws Exception {
		userModel=new UserModel();
		userModel.setName("Amulya");
		userModel.setEmailId("amulya@gmail.com");
		userModel.setPassword("amulya");
		userModel.setPhoneNumber("1234567890");
		userModel.setAddress("Mysore");
		
		
	}

	@Test
	public void testRegisterUser() throws Exception {
		
		try {
			doNothing().when(validator).validateRegistrationDetails(userModel);
			doNothing().when(userRepository.save(any(User.class)));
			assertEquals(REGISTRATION_SUCCESS_MESSAGE,userServiceImpl.registerUser(userModel));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Test
	public void testIsAdminForTrue()
	{
		
		try
		{
			when(userRepository.findByPhoneNumberAndIsActiveTrueAndIsAdminTrue(anyString())).thenReturn(Optional.of(user));
			when(Optional.of(user).isPresent()).thenReturn(true);
			assertEquals(true,userServiceImpl.isAdmin(ADMIN_MOBILE_NUMBER));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testIsAdminForFalse()
	{
		try
		{
			when(userRepository.findByPhoneNumberAndIsActiveTrueAndIsAdminTrue(anyString())).thenReturn(Optional.of(user));
			when(Optional.of(user).isPresent()).thenReturn(false);
			assertEquals(false,userServiceImpl.isAdmin("886178980"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
