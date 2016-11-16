package com.faceye.test.component.questionnaire.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.component.questionnaire.service.AnswerService;
import com.faceye.test.feature.service.BaseServiceTestCase;

/**
 * Answer 服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class AnswerServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private AnswerService answerService = null;

	/**
	 * 初始化
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(answerService != null);
	}

	/**
	 * 清理
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		// this.answerService.removeAllInBatch();
	}

	/**
	 * 保存测试
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Answer entity = new Answer();
		this.answerService.save(entity);
		List<Answer> entites = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Answer entity = new Answer();
		this.answerService.save(entity);
		List<Answer> entites = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Answer entity = new Answer();
			this.answerService.save(entity);
		}
		List<Answer> entities = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Answer entity = new Answer();
		this.answerService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Answer e = this.answerService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Answer entity = new Answer();
		this.answerService.save(entity);
		this.answerService.remove(entity);
		List<Answer> entities = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Answer entity = new Answer();
			this.answerService.save(entity);
		}
		List<Answer> entities = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.answerService.removeAllInBatch();
		entities = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Answer entity = new Answer();
			this.answerService.save(entity);
		}
		this.answerService.removeAll();
		List<Answer> entities = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Answer> entities = new ArrayList<Answer>();
		for (int i = 0; i < 5; i++) {
			Answer entity = new Answer();

			this.answerService.save(entity);
			entities.add(entity);
		}
		this.answerService.removeInBatch(entities);
		entities = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Answer entity = new Answer();
			this.answerService.save(entity);
		}
		List<Answer> entities = this.answerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Answer entity = new Answer();
			this.answerService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Answer> page = this.answerService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.answerService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.answerService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Answer entity = new Answer();
			this.answerService.save(entity);
			id = entity.getId();
		}
		Answer e = this.answerService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Answer entity = new Answer();
			this.answerService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Answer> entities = this.answerService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}


}
