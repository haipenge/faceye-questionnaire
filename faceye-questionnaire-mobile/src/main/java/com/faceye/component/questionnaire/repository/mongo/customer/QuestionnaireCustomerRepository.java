package com.faceye.component.questionnaire.repository.mongo.customer;

import org.springframework.data.repository.NoRepositoryBean;

import com.faceye.component.questionnaire.entity.Questionnaire;

@NoRepositoryBean
public interface QuestionnaireCustomerRepository {

	public void increaceQuestionnaireAccessCount(Questionnaire questionnaire);
}
