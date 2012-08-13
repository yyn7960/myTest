package com.yappam.test.service;

import org.junit.Test;

import com.yappam.model.News;
import com.yappam.service.INewsService;
/**
 * yangyunan Jul 8, 2011-9:56:28 AM
 */

public class INewsServiceTest extends BaseServiceTest{
	
	private INewsService newsService ;

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	@Test
	public void testAdd() {
		News news = new News() ;
		news.setTitle("aaaaa") ;
		news.setContent("vbbb") ;
		news.setFid(1) ;
		news.setImg("img") ;
		
		newsService.add(news) ;
	}
	@Test
	public void testDelete() {
		newsService.delete(1) ;
	}
	@Test
	public void testFindAll(){
		assertEquals(newsService.findAll().size()>0, true) ;
		assertEquals(newsService.findAll().size()==0, false) ;
	}
	@Test
	public void testFindById() {
		assertEquals(newsService.findById(1)!=null, true) ;
		assertEquals(newsService.findById(500000)==null, true) ;
	}
	@Test
	public void testFindByFid() {
		assertEquals(newsService.findByFid(1).size()>0, true) ;
		assertEquals(newsService.findByFid(100000).size()>0, false) ;
	}
	@Test
	public void testUpdate() {
		News news = newsService.findById(1) ;
		news.setTitle("hahahahah") ;
		
		newsService.update(news) ;
	}
}
