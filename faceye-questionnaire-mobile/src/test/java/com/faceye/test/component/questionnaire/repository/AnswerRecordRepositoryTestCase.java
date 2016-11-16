package com.faceye.test.component.questionnaire.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.questionnaire.entity.AnswerRecord;
import com.faceye.component.questionnaire.repository.mongo.AnswerRecordRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * AnswerRecord DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class AnswerRecordRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AnswerRecordRepository answerRecordRepository = null;

	@Before
	public void before() throws Exception {
		//this.answerRecordRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		AnswerRecord entity = new AnswerRecord();
		this.answerRecordRepository.save(entity);
		Iterable<AnswerRecord> entities = this.answerRecordRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		AnswerRecord entity = new AnswerRecord();
		this.answerRecordRepository.save(entity);
        this.answerRecordRepository.delete(entity.getId());
        Iterable<AnswerRecord> entities = this.answerRecordRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		AnswerRecord entity = new AnswerRecord();
		this.answerRecordRepository.save(entity);
		AnswerRecord answerRecord=this.answerRecordRepository.findOne(entity.getId());
		Assert.isTrue(answerRecord!=null);
	}

	
}
