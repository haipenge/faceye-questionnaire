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

import com.faceye.component.questionnaire.entity.AnswerStat;
import com.faceye.component.questionnaire.service.AnswerStatService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * AnswerStat  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class AnswerStatServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private AnswerStatService answerStatService = null;
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
		Assert.assertTrue(answerStatService != null);
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
		//this.answerStatService.removeAllInBatch();
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
		AnswerStat entity = new AnswerStat();
		this.answerStatService.save(entity);
		List<AnswerStat> entites = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		AnswerStat entity = new AnswerStat();
		this.answerStatService.save(entity);
		List<AnswerStat> entites = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			AnswerStat entity = new AnswerStat();
			this.answerStatService.save(entity);
		}
		List<AnswerStat> entities = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		AnswerStat entity = new AnswerStat();
		this.answerStatService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		AnswerStat e = this.answerStatService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		AnswerStat entity = new AnswerStat();
		this.answerStatService.save(entity);
		this.answerStatService.remove(entity);
		List<AnswerStat> entities = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			AnswerStat entity = new AnswerStat();
			this.answerStatService.save(entity);
		}
		List<AnswerStat> entities = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.answerStatService.removeAllInBatch();
		entities = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			AnswerStat entity = new AnswerStat();
			this.answerStatService.save(entity);
		}
		this.answerStatService.removeAll();
		List<AnswerStat> entities = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<AnswerStat> entities = new ArrayList<AnswerStat>();
		for (int i = 0; i < 5; i++) {
			AnswerStat entity = new AnswerStat();
			
			this.answerStatService.save(entity);
			entities.add(entity);
		}
		this.answerStatService.removeInBatch(entities);
		entities = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			AnswerStat entity = new AnswerStat();
			this.answerStatService.save(entity);
		}
		List<AnswerStat> entities = this.answerStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			AnswerStat entity = new AnswerStat();
			this.answerStatService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<AnswerStat> page = this.answerStatService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.answerStatService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.answerStatService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			AnswerStat entity = new AnswerStat();
			this.answerStatService.save(entity);
			id = entity.getId();
		}
		AnswerStat e = this.answerStatService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			AnswerStat entity = new AnswerStat();
			this.answerStatService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<AnswerStat> entities = this.answerStatService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
