package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.questionnaire.entity.Question;
import com.faceye.component.questionnaire.repository.mongo.QuestionRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Question DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class QuestionRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private QuestionRepository questionRepository = null;

	@Before
	public void before() throws Exception {
		//this.questionRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Question entity = new Question();
		this.questionRepository.save(entity);
		Iterable<Question> entities = this.questionRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Question entity = new Question();
		this.questionRepository.save(entity);
        this.questionRepository.deleteById(entity.getId());
        Iterable<Question> entities = this.questionRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Question entity = new Question();
		this.questionRepository.save(entity);
		Question question=this.questionRepository.findById(entity.getId()).get();
		Assert.assertTrue(question!=null);
	}

	
}
