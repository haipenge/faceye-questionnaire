package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.questionnaire.entity.AnswerStat;
import com.faceye.component.questionnaire.repository.mongo.AnswerStatRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * AnswerStat DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class AnswerStatRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AnswerStatRepository answerStatRepository = null;

	@Before
	public void before() throws Exception {
		//this.answerStatRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		AnswerStat entity = new AnswerStat();
		this.answerStatRepository.save(entity);
		Iterable<AnswerStat> entities = this.answerStatRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		AnswerStat entity = new AnswerStat();
		this.answerStatRepository.save(entity);
        this.answerStatRepository.delete(entity.getId());
        Iterable<AnswerStat> entities = this.answerStatRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		AnswerStat entity = new AnswerStat();
		this.answerStatRepository.save(entity);
		AnswerStat answerStat=this.answerStatRepository.findOne(entity.getId());
		Assert.isTrue(answerStat!=null);
	}

	
}
