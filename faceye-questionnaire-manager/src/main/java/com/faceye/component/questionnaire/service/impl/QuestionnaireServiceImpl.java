package com.faceye.component.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.repository.mongo.QuestionnaireRepository;
import com.faceye.component.questionnaire.service.QuestionnaireService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.entity.ResponseContent;
import com.faceye.component.weixin.entity.ResponseContentItem;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.ResponseContentItemService;
import com.faceye.component.weixin.service.ResponseContentService;
import com.faceye.feature.entity.UploadFile;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.PropertyService;
import com.faceye.feature.service.UploadFileService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.JspUtil;
import com.faceye.feature.util.ServiceException;
import com.faceye.feature.util.regexp.RegexpUtil;
import com.querydsl.core.types.Predicate;

@Service
public class QuestionnaireServiceImpl extends BaseMongoServiceImpl<Questionnaire, Long, QuestionnaireRepository> implements QuestionnaireService {

	@Autowired
	private PropertyService propertyService = null;
	@Autowired
	private UploadFileService uploadFileService = null;
	@Autowired
	private ResponseContentService responseContentService = null;
	@Autowired
	private ResponseContentItemService responseContentItemService = null;
	@Autowired
	private AccountService accountService = null;

	@Autowired
	public QuestionnaireServiceImpl(QuestionnaireRepository dao) {
		super(dao);
	}

	public Questionnaire save(Questionnaire questionnaire) {
		super.save(questionnaire);
		String content = questionnaire.getRemark();
		String DISTILL_IMG_SRC = "<img[^>]*?src=[\"\\']?([^\"\\'>]+)[\"\\']?[^>]*\\/>";
		List<UploadFile> uploadFiles = new ArrayList<UploadFile>(0);
		if (StringUtils.isNotEmpty(content)) {
			try {
				List<Map<String, String>> matches = RegexpUtil.match(content, DISTILL_IMG_SRC);
				if (CollectionUtils.isNotEmpty(matches)) {
					for (Map<String, String> map : matches) {
						String url = map.get("1");
						logger.debug(">>FaceYe --> image url is:" + url);
						String imgServer = this.propertyService.get("image.server");
						logger.debug(">>FaceYe --> Img Server is:" + imgServer);
						String storePath = url.replace(imgServer, "");
						logger.debug(">>FaceYe --> Store Path is:" + storePath);
						if (StringUtils.isNotEmpty(url)) {
							UploadFile uploadFile = this.uploadFileService.getUploadFileByUrl(url);
							if (null == uploadFile) {
								uploadFile = this.uploadFileService.save(storePath, url, questionnaire.getId(), Questionnaire.class.getName());
							}
							uploadFiles.add(uploadFile);
						}
					}
				}
			} catch (Exception e) {
				logger.error(">>FaceYe throws Exception: --->", e);
			}
			questionnaire.setUploadFiles(uploadFiles);
			super.save(questionnaire);
		}
		return questionnaire;

	}

	@Override
	public Page<Questionnaire> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Questionnaire> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Questionnaire> builder = new PathBuilder<Questionnaire>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.ASC, "id");
		Page<Questionnaire> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Questionnaire>("id") {
			// })
			List<Questionnaire> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Questionnaire>(items);

		}
		return res;
	}

	@Override
	public boolean pushQuestionnaire2Weixin(String accountIds, String questionnaireIds) {
		if (StringUtils.isNotEmpty(accountIds) && StringUtils.isNotEmpty(questionnaireIds)) {
			String[] ids = StringUtils.split(accountIds, ",");
			String[] qIds = StringUtils.split(questionnaireIds, ",");
			if (ids != null && qIds != null) {
				for (String qId : qIds) {
					Questionnaire questionnaire = this.get(Long.parseLong(qId));
					for (String id : ids) {
						if (StringUtils.isNotEmpty(id)) {
							Account account = this.accountService.get(Long.parseLong(id));
							if (account != null) {
								String url = "/questionnaire/questionnaire/getQuestionnaire/" + questionnaire.getId() + "?accountId=" + account.getId();
								if (account != null) {
									logger.debug(">>FaceYe trace ---> account is:" + account.getId());
									ResponseContent responseContent = this.responseContentService.getDefaultResponseContent(account);
									// this.responseContentService.remove(responseContent);
									// responseContent = null;
									if (responseContent != null) {
										ResponseContentItem responseContentItem = this.responseContentItemService.getResponseContentItemByUrl(url);
										if (responseContentItem != null) {
											this.responseContentItemService.remove(responseContentItem);
											responseContentItem = null;
										}
										if (responseContentItem == null) {
											responseContentItem = new ResponseContentItem();
											responseContentItem.setName(questionnaire.getName());
											// List<Image> images = this.imageService.getImagesByContent(entity);
											if (CollectionUtils.isNotEmpty(questionnaire.getUploadFiles())) {
												responseContentItem.setPicUrl(questionnaire.getUploadFiles().get(0).getStorePath());
											} else {
												logger.debug(">>FaceYe --> article images is empty.");
											}
											responseContentItem.setRemark(JspUtil.getSummary(questionnaire.getRemark(), 40));
											responseContentItem.setResponseContent(responseContent);
											responseContentItem.setUrl(url);
											this.responseContentItemService.save(responseContentItem);
										}
									} else {
										logger.debug(">>FaceYe Trace Response content is empty.");
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

}/** @generate-service-source@ **/
