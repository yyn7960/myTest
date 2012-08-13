package com.yappam.test.service;

import org.junit.Test;

import com.yappam.model.User;
import com.yappam.service.IUserService;
/**
 * yangyunan Jul 8, 2011-9:56:28 AM
 */

public class IUserServiceTest extends BaseServiceTest{
	
	private IUserService userService ;
	

	@Test
	public void testFindById() {
		System.out.println(userService.findById(1).getName());
		assertEquals(userService.findById(1).getName().equals("admin"), true) ;
		assertEquals(userService.findById(1).getName().equals("aaa"), false) ;
	}

	@Test
	public void testHave() {
		assertEquals(userService.have(1), true) ;
		assertEquals(userService.have(3), false) ;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Test
	public void testUpdate(){
//		super.setDefaultRollback(false) ;
		User user = userService.findById(2) ;
		user.setPass("1903") ;
		userService.update(user) ;
	}
	@Test
	public void testAdd(){
		
		User user = new User() ;
		user.setName("aaa") ;
		user.setPass("123") ;
		userService.add(user) ;
		
	}
	
}
