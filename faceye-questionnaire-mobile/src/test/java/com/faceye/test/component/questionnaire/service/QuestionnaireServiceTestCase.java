package com.faceye.test.component.questionnaire.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.junit.Assert;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.service.QuestionnaireService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Questionnaire  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class QuestionnaireServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private QuestionnaireService questionnaireService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.assertTrue(questionnaireService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.questionnaireService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Questionnaire entity = new Questionnaire();
		this.questionnaireService.save(entity);
		List<Questionnaire> entites = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Questionnaire entity = new Questionnaire();
		this.questionnaireService.save(entity);
		List<Questionnaire> entites = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Questionnaire entity = new Questionnaire();
			this.questionnaireService.save(entity);
		}
		List<Questionnaire> entities = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Questionnaire entity = new Questionnaire();
		this.questionnaireService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Questionnaire e = this.questionnaireService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Questionnaire entity = new Questionnaire();
		this.questionnaireService.save(entity);
		this.questionnaireService.remove(entity);
		List<Questionnaire> entities = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Questionnaire entity = new Questionnaire();
			this.questionnaireService.save(entity);
		}
		List<Questionnaire> entities = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.questionnaireService.removeAllInBatch();
		entities = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Questionnaire entity = new Questionnaire();
			this.questionnaireService.save(entity);
		}
		this.questionnaireService.removeAll();
		List<Questionnaire> entities = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Questionnaire> entities = new ArrayList<Questionnaire>();
		for (int i = 0; i < 5; i++) {
			Questionnaire entity = new Questionnaire();
			
			this.questionnaireService.save(entity);
			entities.add(entity);
		}
		this.questionnaireService.removeInBatch(entities);
		entities = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Questionnaire entity = new Questionnaire();
			this.questionnaireService.save(entity);
		}
		List<Questionnaire> entities = this.questionnaireService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Questionnaire entity = new Questionnaire();
			this.questionnaireService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Questionnaire> page = this.questionnaireService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.questionnaireService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.questionnaireService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Questionnaire entity = new Questionnaire();
			this.questionnaireService.save(entity);
			id = entity.getId();
		}
		Questionnaire e = this.questionnaireService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Questionnaire entity = new Questionnaire();
			this.questionnaireService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Questionnaire> entities = this.questionnaireService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
