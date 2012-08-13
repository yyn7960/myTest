package com.yappam.test.service;
/**
 * yangyunan Jul 8, 2011-10:22:23 AM
 */
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class BaseServiceTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	protected String[] getConfigLocations() {
		setAutowireMode(AUTOWIRE_BY_TYPE);
		return new String[] { "classpath*:spring//*.xml",
				"file:resources\\spring\\applicationContext.xml" };
	}

}
