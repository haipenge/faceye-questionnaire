package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.repository.mongo.QuestionnaireRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Questionnaire DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class QuestionnaireRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private QuestionnaireRepository questionnaireRepository = null;

	@Before
	public void before() throws Exception {
		//this.questionnaireRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Questionnaire entity = new Questionnaire();
		this.questionnaireRepository.save(entity);
		Iterable<Questionnaire> entities = this.questionnaireRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Questionnaire entity = new Questionnaire();
		this.questionnaireRepository.save(entity);
        this.questionnaireRepository.delete(entity.getId());
        Iterable<Questionnaire> entities = this.questionnaireRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Questionnaire entity = new Questionnaire();
		this.questionnaireRepository.save(entity);
		Questionnaire questionnaire=this.questionnaireRepository.findOne(entity.getId());
		Assert.isTrue(questionnaire!=null);
	}

	
}
