package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.questionnaire.entity.QuestionnaireType;
import com.faceye.component.questionnaire.repository.mongo.QuestionnaireTypeRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * QuestionnaireType DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class QuestionnaireTypeRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private QuestionnaireTypeRepository questionnaireTypeRepository = null;

	@Before
	public void before() throws Exception {
		//this.questionnaireTypeRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		QuestionnaireType entity = new QuestionnaireType();
		this.questionnaireTypeRepository.save(entity);
		Iterable<QuestionnaireType> entities = this.questionnaireTypeRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		QuestionnaireType entity = new QuestionnaireType();
		this.questionnaireTypeRepository.save(entity);
        this.questionnaireTypeRepository.deleteById(entity.getId());
        Iterable<QuestionnaireType> entities = this.questionnaireTypeRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		QuestionnaireType entity = new QuestionnaireType();
		this.questionnaireTypeRepository.save(entity);
		QuestionnaireType questionnaireType=this.questionnaireTypeRepository.findById(entity.getId()).get();
		Assert.assertTrue(questionnaireType!=null);
	}

	
}
