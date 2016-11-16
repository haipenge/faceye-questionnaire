package com.faceye.component.questionnaire.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.faceye.component.questionnaire.entity.AnswerRecord;
import com.faceye.component.questionnaire.entity.Question;

public class MQuestion {

	private Question question = null;

	private List<MAnswer> manswers = null;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<MAnswer> getManswers() {
		return manswers;
	}

	public void setManswers(List<MAnswer> manswers) {
		this.computeRate(manswers);
		this.manswers = manswers;
	}

	private Integer type = 0;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	private void computeRate(List<MAnswer> manswers) {
		if (CollectionUtils.isNotEmpty(manswers)) {
			DecimalFormat df = new DecimalFormat("####.00");
			Integer totalCount = 0;
			for (MAnswer manswer : manswers) {
				totalCount += manswer.getAnswer().getSupportCount();
			}
			for (MAnswer manswer : manswers) {
				Double rate = 0.00;
				if (totalCount != 0) {
					rate = new Double(new Double(manswer.getAnswer().getSupportCount()) / new Double(totalCount));
				} else {
					rate = 0D;
				}
				String res = df.format(rate * 100) + "%";
				manswer.setRate(res);
			}

		}
	}

	private List<AnswerRecord> answerTextRecords = new ArrayList<AnswerRecord>(0);

	public List<AnswerRecord> getAnswerTextRecords() {
		return answerTextRecords;
	}

	public void setAnswerTextRecords(List<AnswerRecord> answerTextRecords) {
		this.answerTextRecords = answerTextRecords;
	}

}
