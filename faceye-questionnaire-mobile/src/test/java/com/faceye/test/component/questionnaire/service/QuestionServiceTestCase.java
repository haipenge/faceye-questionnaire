package com.faceye.test.component.questionnaire.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.junit.Assert;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.component.questionnaire.entity.Question;
import com.faceye.component.questionnaire.service.QuestionService;
import com.faceye.test.feature.service.BaseServiceTestCase;

/**
 * Question 服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class QuestionServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private QuestionService questionService = null;

	/**
	 * 初始化
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.assertTrue(questionService != null);
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
		// this.questionService.removeAllInBatch();
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
		Question entity = new Question();
		this.questionService.save(entity);
		List<Question> entites = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Question entity = new Question();
		this.questionService.save(entity);
		List<Question> entites = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Question entity = new Question();
			this.questionService.save(entity);
		}
		List<Question> entities = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Question entity = new Question();
		this.questionService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Question e = this.questionService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Question entity = new Question();
		this.questionService.save(entity);
		this.questionService.remove(entity);
		List<Question> entities = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Question entity = new Question();
			this.questionService.save(entity);
		}
		List<Question> entities = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.questionService.removeAllInBatch();
		entities = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Question entity = new Question();
			this.questionService.save(entity);
		}
		this.questionService.removeAll();
		List<Question> entities = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Question> entities = new ArrayList<Question>();
		for (int i = 0; i < 5; i++) {
			Question entity = new Question();

			this.questionService.save(entity);
			entities.add(entity);
		}
		this.questionService.removeInBatch(entities);
		entities = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Question entity = new Question();
			this.questionService.save(entity);
		}
		List<Question> entities = this.questionService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Question entity = new Question();
			this.questionService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Question> page = this.questionService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.questionService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.questionService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Question entity = new Question();
			this.questionService.save(entity);
			id = entity.getId();
		}
		Question e = this.questionService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Question entity = new Question();
			this.questionService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Question> entities = this.questionService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}

	@Test
	public void testReplace() throws Exception {
		int page = 1;
		String testName = "1/19:I a teset name ";
		Page<Question> answers = this.questionService.getPage(null, page, 1000);
		while (answers != null && CollectionUtils.isNotEmpty(answers.getContent())) {
			if (answers != null && CollectionUtils.isNotEmpty(answers.getContent())) {
				for (Question question : answers.getContent()) {
					String name = question.getName();
					String regexp = "[\\d]+\\/[\\d]+:";
					Pattern p = Pattern.compile(regexp);
					Matcher matcher = p.matcher(name);
					// matcher=p.matcher(testName);
					String afterReplaceName = "";
					if (matcher.find()) {
						logger.debug("name is:" + name);
						afterReplaceName = StringUtils.replacePattern(name, regexp, "");
						if (StringUtils.isNotEmpty(afterReplaceName)) {
							question.setName(afterReplaceName);
							this.questionService.save(question);
						}
						logger.debug(">>Replaced name is:" + afterReplaceName);
//						break;
					} else {
						// logger.debug(">>Name have not matcher."+name);
					}
				}
			}
			page++;
			answers = this.questionService.getPage(null, page, 1000);
		}
	}

}
