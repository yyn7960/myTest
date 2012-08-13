package com.yappam.test.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yappam.action.UserAction;

/**
 * yangyunan Jul 8, 2011-9:17:16 AM
 */

public class UserActionTest extends BaseActionTest{

//	@Before
//	public void setUp() throws Exception {
//		ApplicationContext context = new FileSystemXmlApplicationContext("resources//spring//applicationContext.xml");
//		System.out.println(context);
//	}

	@Test
	public void testFind() {
		UserAction userAction = new UserAction() ;
		try {
			userAction.find();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
	}

}
