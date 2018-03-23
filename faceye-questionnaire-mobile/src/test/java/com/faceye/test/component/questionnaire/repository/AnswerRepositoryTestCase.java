package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.component.questionnaire.repository.mongo.AnswerRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Answer DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class AnswerRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AnswerRepository answerRepository = null;

	@Before
	public void before() throws Exception {
		//this.answerRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Answer entity = new Answer();
		this.answerRepository.save(entity);
		Iterable<Answer> entities = this.answerRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Answer entity = new Answer();
		this.answerRepository.save(entity);
        this.answerRepository.deleteById(entity.getId());
        Iterable<Answer> entities = this.answerRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Answer entity = new Answer();
		this.answerRepository.save(entity);
		Answer answer=this.answerRepository.findById(entity.getId()).get();
		Assert.assertTrue(answer!=null);
	}

	
}
