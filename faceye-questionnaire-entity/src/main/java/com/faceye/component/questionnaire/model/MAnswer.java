package com.faceye.component.questionnaire.model;

import com.faceye.component.questionnaire.entity.Answer;

public class MAnswer {

	private Answer answer = null;

	private Boolean isChecked = false;

	/**
	 * 比率
	 */
	private String rate = "0%";

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
	

	

}
