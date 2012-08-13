package com.yappam.test.service;

import org.junit.Test;

import com.yappam.model.Type;
import com.yappam.service.ITypeService;
/**
 * yangyunan Jul 8, 2011-9:56:28 AM
 */

public class ITypeServiceTest extends BaseServiceTest{
	
	private ITypeService typeService ;

	public ITypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(ITypeService typeService) {
		this.typeService = typeService;
	}
	
	@Test
	public void testAdd() {
		Type type = new Type() ;
		type.setName("test 测试") ;
		type.setLevel(0) ;
		type.setFid(0) ;
		
		typeService.add(type) ;
	}
	@Test
	public void testDelete() {
		typeService.deleteById(1) ;
	}
	@Test
	public void testFindById(){
		assertEquals(typeService.findById(1)==null, false) ;
		assertEquals(typeService.findById(100)==null, true) ;
	}
	@Test
	public void testFindAll() {
		assertEquals(typeService.getAll().size()>0 , true) ;
		assertEquals(typeService.getAll().size()==0, false) ;
	}
	@Test
	public void testUpdate() {
		Type type = typeService.findById(1) ;
		type.setName("我修改一下") ;
		
		typeService.update(type) ;
	}
	@Test
	public void testGetListByLevel() {
		assertEquals(typeService.getListByLevel(0).size()>0, true) ;
		assertEquals(typeService.getListByLevel(100).size()>0, false) ;
	}
	
}
