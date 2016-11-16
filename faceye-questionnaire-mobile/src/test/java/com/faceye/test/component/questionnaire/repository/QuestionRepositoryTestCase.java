package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

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
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Question entity = new Question();
		this.questionRepository.save(entity);
        this.questionRepository.delete(entity.getId());
        Iterable<Question> entities = this.questionRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Question entity = new Question();
		this.questionRepository.save(entity);
		Question question=this.questionRepository.findOne(entity.getId());
		Assert.isTrue(question!=null);
	}

	
}
