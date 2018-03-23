package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.questionnaire.entity.Discuss;
import com.faceye.component.questionnaire.repository.mongo.DiscussRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Discuss DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class DiscussRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DiscussRepository discussRepository = null;

	@Before
	public void before() throws Exception {
		//this.discussRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Discuss entity = new Discuss();
		this.discussRepository.save(entity);
		Iterable<Discuss> entities = this.discussRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Discuss entity = new Discuss();
		this.discussRepository.save(entity);
        this.discussRepository.deleteById(entity.getId());
        Iterable<Discuss> entities = this.discussRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Discuss entity = new Discuss();
		this.discussRepository.save(entity);
		Discuss discuss=this.discussRepository.findById(entity.getId()).get();
		Assert.assertTrue(discuss!=null);
	}

	
}
