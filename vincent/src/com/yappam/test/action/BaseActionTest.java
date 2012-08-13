package com.yappam.test.action;
/**
 * yangyunan Jul 8, 2011-10:22:23 AM
 */
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class BaseActionTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	protected String[] getConfigLocations() {
		setAutowireMode(AUTOWIRE_BY_TYPE);
		return new String[] { "classpath*:spring//*.xml",
				"file:resources\\spring\\applicationContext-action.xml" };
	}

}
