package com.yappam.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yappam.model.User;
import com.yappam.service.IUserService;

/**
 * yangyunan Jul 7, 2011-4:55:09 PM
 */
public class UserAction extends ActionSupport{
	
	/**
	 * version 1: 2012-8-5 copy from model
	 */
	private static final long serialVersionUID = 1L;
	private String name ;
	private String pass ;
	
	private IUserService userService ;
	
	public String find() throws Exception {
		User u = userService.findById(1) ;
		System.out.println(u.getName());
		return SUCCESS ;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
