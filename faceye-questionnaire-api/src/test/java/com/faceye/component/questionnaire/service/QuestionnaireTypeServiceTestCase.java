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
import org.springframework.util.Assert;

import com.faceye.component.questionnaire.entity.QuestionnaireType;
import com.faceye.component.questionnaire.service.QuestionnaireTypeService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * QuestionnaireType  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class QuestionnaireTypeServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private QuestionnaireTypeService questionnaireTypeService = null;
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
		Assert.isTrue(questionnaireTypeService != null);
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
		//this.questionnaireTypeService.removeAllInBatch();
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
		QuestionnaireType entity = new QuestionnaireType();
		this.questionnaireTypeService.save(entity);
		List<QuestionnaireType> entites = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		QuestionnaireType entity = new QuestionnaireType();
		this.questionnaireTypeService.save(entity);
		List<QuestionnaireType> entites = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			this.questionnaireTypeService.save(entity);
		}
		List<QuestionnaireType> entities = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		QuestionnaireType entity = new QuestionnaireType();
		this.questionnaireTypeService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		QuestionnaireType e = this.questionnaireTypeService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		QuestionnaireType entity = new QuestionnaireType();
		this.questionnaireTypeService.save(entity);
		this.questionnaireTypeService.remove(entity);
		List<QuestionnaireType> entities = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			this.questionnaireTypeService.save(entity);
		}
		List<QuestionnaireType> entities = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.questionnaireTypeService.removeAllInBatch();
		entities = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			this.questionnaireTypeService.save(entity);
		}
		this.questionnaireTypeService.removeAll();
		List<QuestionnaireType> entities = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<QuestionnaireType> entities = new ArrayList<QuestionnaireType>();
		for (int i = 0; i < 5; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			
			this.questionnaireTypeService.save(entity);
			entities.add(entity);
		}
		this.questionnaireTypeService.removeInBatch(entities);
		entities = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			this.questionnaireTypeService.save(entity);
		}
		List<QuestionnaireType> entities = this.questionnaireTypeService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			this.questionnaireTypeService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<QuestionnaireType> page = this.questionnaireTypeService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.questionnaireTypeService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.questionnaireTypeService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			this.questionnaireTypeService.save(entity);
			id = entity.getId();
		}
		QuestionnaireType e = this.questionnaireTypeService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			QuestionnaireType entity = new QuestionnaireType();
			this.questionnaireTypeService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<QuestionnaireType> entities = this.questionnaireTypeService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
