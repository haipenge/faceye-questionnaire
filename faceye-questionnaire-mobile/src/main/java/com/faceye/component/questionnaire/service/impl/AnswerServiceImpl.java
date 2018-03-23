package com.faceye.component.questionnaire.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.component.questionnaire.entity.AnswerRecord;
import com.faceye.component.questionnaire.entity.Question;
import com.faceye.component.questionnaire.entity.Rabbit;
import com.faceye.component.questionnaire.repository.mongo.AnswerRepository;
import com.faceye.component.questionnaire.service.AnswerRecordService;
import com.faceye.component.questionnaire.service.AnswerService;
import com.faceye.component.questionnaire.service.QuestionService;
import com.faceye.component.questionnaire.service.QuestionnaireService;
import com.faceye.component.questionnaire.service.RabbitService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.service.impl.PrintReporter;
 
import com.faceye.feature.util.bean.BeanContextUtil;
import com.querydsl.core.types.Predicate;

@Service
public class AnswerServiceImpl extends BaseMongoServiceImpl<Answer, Long, AnswerRepository> implements AnswerService {

	@Autowired
	private AnswerRecordService answerRecordService = null;
	@Autowired
	private QuestionnaireService questionnaireService = null;
	@Autowired
	private RabbitService rabbitService = null;
	@Autowired
	private QuestionService questionService=null;

	@Autowired
	public AnswerServiceImpl(AnswerRepository dao) {
		super(dao);
	}

	@Override
	public Page<Answer> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Answer> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Answer> builder = new PathBuilder<Answer>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Answer> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Answer>("id") {
			// })
			List<Answer> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Answer>(items);

		}
		return res;
	}

	@Override
	public Answer saveAnswer(Map params) {
		Long questionnaireId = MapUtils.getLong(params, "questionnaireId");
		Long questionId = MapUtils.getLong(params, "questionId");
		Long answerId = MapUtils.getLong(params, "answerId");
		Boolean isChecked = MapUtils.getBoolean(params, "isChecked");
		String type = MapUtils.getString(params, "type");
		Rabbit rabbit = this.rabbitService.getCurrentLoginRabbit();
		BeanContextUtil.getBean(PrintReporter.class).reporter(params);
		Answer answer = this.get(answerId);
		if (rabbit != null) {
			Map searchAnswerRecordParams = new HashMap();
			searchAnswerRecordParams.put("EQ|questionnaireId", questionnaireId);
			searchAnswerRecordParams.put("EQ|questionId", questionId);
			// searchAnswerRecordParams.put("EQ|answerId", answerId);
			searchAnswerRecordParams.put("EQ|rabbitId", rabbit.getId());
			List<AnswerRecord> answerRecords = this.answerRecordService.getPage(searchAnswerRecordParams, 1, 0).getContent();
			// Map searchAnswerParams=new HashMap();
			// searchAnswerParams.put("EQ|question.$id", questionId);

			// List<Answer> answers=this.getPage(searchAnswerParams, 1, 0).getContent();
			if (StringUtils.equalsIgnoreCase(type, "radio")) {
				if (CollectionUtils.isNotEmpty(answerRecords)) {
					boolean isExistAnswerRecord = false;
					AnswerRecord existAnswerRecord = null;
					for (AnswerRecord answerRecord : answerRecords) {
						if (answerRecord.getAnswerId().compareTo(answerId) == 0) {
							isExistAnswerRecord = true;
							existAnswerRecord = answerRecord;
							break;
						}
					}
					if (isExistAnswerRecord) {
						if (!existAnswerRecord.getIsChecked()) {
							for (AnswerRecord answerRecord : answerRecords) {
								if (!(answerRecord.getAnswerId().compareTo(answerId) == 0)) {
									if (answerRecord.getIsChecked()) {
										Answer inAnswer = this.get(answerRecord.getAnswerId());
										inAnswer.setSupportCount(inAnswer.getSupportCount() - 1);
										this.save(inAnswer);
										answerRecord.setIsChecked(false);
										this.answerRecordService.save(answerRecord);
									}
								}
							}
							answer.setSupportCount(answer.getSupportCount() + 1);
							this.save(answer);
							existAnswerRecord.setIsChecked(true);
							this.answerRecordService.save(existAnswerRecord);
						}
					} else {
						AnswerRecord answerRecord = new AnswerRecord();
						answerRecord.setAnswerId(answerId);
						answerRecord.setIsChecked(isChecked);
						answerRecord.setQuestionId(questionId);
						answerRecord.setQuestionnaireId(questionnaireId);
						answerRecord.setRabbitId(rabbit.getId());
						this.answerRecordService.save(answerRecord);
						answer.setSupportCount(answer.getSupportCount() + 1);
						this.save(answer);
						for(AnswerRecord answerR:answerRecords){
							if(answerR.getIsChecked()){
								answerR.setIsChecked(false);
								this.answerRecordService.save(answerR);
								Answer inAnswer=this.get(answerR.getAnswerId());
								inAnswer.setSupportCount(inAnswer.getSupportCount()-1);
								this.save(inAnswer);
							}
						}
					}
				} else {
					AnswerRecord answerRecord = new AnswerRecord();
					answerRecord.setAnswerId(answerId);
					answerRecord.setIsChecked(isChecked);
					answerRecord.setQuestionId(questionId);
					answerRecord.setQuestionnaireId(questionnaireId);
					answerRecord.setRabbitId(rabbit.getId());
					this.answerRecordService.save(answerRecord);
					answer.setSupportCount(answer.getSupportCount() + 1);
					this.save(answer);
				}
			} else {
				// type ==checkbox
				if (CollectionUtils.isEmpty(answerRecords)) {
					AnswerRecord answerRecord = new AnswerRecord();
					answerRecord.setAnswerId(answerId);
					answerRecord.setIsChecked(isChecked);
					answerRecord.setQuestionId(questionId);
					answerRecord.setQuestionnaireId(questionnaireId);
					answerRecord.setRabbitId(rabbit.getId());
					this.answerRecordService.save(answerRecord);
					answer.setSupportCount(answer.getSupportCount() + 1);
					this.save(answer);
				} else {
					if (isChecked) {
						Map singleSearchParams = new HashMap();
						singleSearchParams.put("EQ|answerId", answerId);
						singleSearchParams.put("EQ|rabbitId", rabbit.getId());
						List<AnswerRecord> storedAnswerRecords = this.answerRecordService.getPage(singleSearchParams, 1, 0).getContent();
						if (CollectionUtils.isNotEmpty(storedAnswerRecords)) {
							AnswerRecord storedAnswerRecord = storedAnswerRecords.get(0);
							if (!storedAnswerRecord.getIsChecked()) {
								storedAnswerRecord.setIsChecked(true);
								this.answerRecordService.save(storedAnswerRecord);
//								Answer singleAnswer = this.get(storedAnswerRecord.getAnswerId());
								answer.setSupportCount(answer.getSupportCount() + 1);
								this.save(answer);
							}
						} else {
							AnswerRecord answerRecord = new AnswerRecord();
							answerRecord.setAnswerId(answerId);
							answerRecord.setIsChecked(isChecked);
							answerRecord.setQuestionId(questionId);
							answerRecord.setQuestionnaireId(questionnaireId);
							answerRecord.setRabbitId(rabbit.getId());
							this.answerRecordService.save(answerRecord);
							answer.setSupportCount(answer.getSupportCount() + 1);
							this.save(answer);
						}
					} else {
						Map singleSearchParams = new HashMap();
						singleSearchParams.put("EQ|answerId", answerId);
						singleSearchParams.put("EQ|rabbitId", rabbit.getId());
						List<AnswerRecord> storedAnswerRecords = this.answerRecordService.getPage(singleSearchParams, 1, 0).getContent();
						if (CollectionUtils.isNotEmpty(storedAnswerRecords)) {
							AnswerRecord storedAnswerRecord = storedAnswerRecords.get(0);
							if (storedAnswerRecord.getIsChecked()) {
								storedAnswerRecord.setIsChecked(false);
								this.answerRecordService.save(storedAnswerRecord);
								answer.setSupportCount(answer.getSupportCount() - 1);
								this.save(answer);
							}
						}
					}
				}
			}

		} else {
			logger.debug(">>FaceYe rabbit is null.");
		}
		return answer;
	}

	@Override
	public void saveInitAnswerSupportCount(Long questionnaireId) {
		Map searchParams=new HashMap();
		searchParams.put("EQ|questionnaire.$id", questionnaireId);
		List<Answer> answers=this.getPage(searchParams, 1, 0).getContent();
		if(CollectionUtils.isNotEmpty(answers)){
			for(Answer answer:answers){
				answer.setSupportCount(0);
				this.save(answer);
			}
		}
		Map searchAnswerRecordsParams=new HashMap();
		searchAnswerRecordsParams.put("EQ|questionnaireId", questionnaireId);
		List<AnswerRecord> answerRecords=this.answerRecordService.getPage(searchAnswerRecordsParams, 1, 0).getContent();
		if(CollectionUtils.isNotEmpty(answerRecords)){
			this.answerRecordService.removeInBatch(answerRecords);
		}
	}

	@Override
	public Answer saveAnswerText(Map params) {
		String name=MapUtils.getString(params, "name");
		String value=MapUtils.getString(params, "val");
		if(StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(value)){
			String [] nameArray=StringUtils.split(name, "-");
			if(nameArray!=null && nameArray.length==3 && StringUtils.equals(nameArray[0], "answer")&&StringUtils.equals(nameArray[1], "text")){
				Long questionId=Long.parseLong(StringUtils.trim(nameArray[2]));
				Question question=this.questionService.get(questionId);
				Map searAnserRecordParams=new HashMap();
				searAnserRecordParams.put("EQ|questionId",questionId);
				List<AnswerRecord> records=this.answerRecordService.getPage(searAnserRecordParams, 1, 0).getContent();
				if(CollectionUtils.isNotEmpty(records)){
					AnswerRecord answerRecord=records.get(0);
					answerRecord.setAnswerText(value);
					this.answerRecordService.save(answerRecord);
				}else{
					AnswerRecord answerRecord=new AnswerRecord();
					answerRecord.setAnswerText(value);
					answerRecord.setIsChecked(true);
					answerRecord.setQuestionId(questionId);
					answerRecord.setQuestionnaireId(question.getQuestionnaire().getId());
					Rabbit rabbit=this.rabbitService.getCurrentLoginRabbit();
					if(rabbit!=null){
						answerRecord.setRabbitId(rabbit.getId());
					}
					this.answerRecordService.save(answerRecord);
				}
			}
		}
		return null;
	}

}/** @generate-service-source@ **/
