<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/questionnaire/questionnaire.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/questionnaire/questionnaire.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/answer/answer.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/question/question.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="questionnaire.questionnaire.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<input type="hidden" name="questionnaireId"
					value="${questionnaire.id }">
				<tr>
					<td class="width-p-20 bg-gray"><fmt:message
							key="questionnaire.questionnaire.name"></fmt:message></td>
					<td colspan="3">${questionnaire.name}&nbsp;&nbsp;<small><span
							class="span-suffix"><fmt:message
									key="questionnaire.questionnaire.createDate"></fmt:message>:<fmt:formatDate
									value="${questionnaire.createDate}" pattern="yyyy-MM-dd HH:mm" /></span></small></td>
				</tr>
				<tr>
					<td class="bg-gray"><fmt:message
							key="questionnaire.questionnaire.remark"></fmt:message></td>
					<td colspan="3">${questionnaire.remark}</td>
				</tr>
				<tr>
					<td class="bg-gray width-p-20"><fmt:message
							key="questionnaire.questionnaire.isClosed"></fmt:message></td>
					<td><f:boolean value="${questionnaire.isClosed}" /></td>

					<td class="bg-gray width-p-20"><fmt:message
							key="questionnaire.questionnaire.isLocked"></fmt:message></td>
					<td><f:boolean value="${questionnaire.isLocked}" /></td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<c:if test="${!questionnaire.isLocked }">
		<div class="content">
			<p>
				<button type="button"
					class="btn btn-primary btn-sm reset-answer-support-count">Reset
					Answer Support Count</button>
			</p>
		</div>
	</c:if>
	<c:set var="CharStr" value="A,B,C,D,E,F,G,H,I,J,K" />
	<c:set var="CharArray" value="${fn:split(CharStr, ',')}" />
	<div class="content">
		<ul class="list-group">
			<c:forEach items="${mQuestions}" var="mQuestion" varStatus="status">
				<li class="list-group-item" id="${mQuestion.question.id}">
					<h5 class="list-group-item-heading"
						style="font: 16px; font-weight: bold;">${status.count}.
						${mQuestion.question.name}<small><c:if
								test="${!questionnaire.isLocked }">
								<a href="#" class="remove-question pull-right"><fmt:message
										key="global.remove" /></a>
							</c:if></small>
					</h5>
					 <c:if test="${mQuestion.question.type eq 2  and not empty mQuestion.answerTextRecords}">
					   <p class="list-group-item-text">
					     <c:forEach items="${mQuestion.answerTextRecords}" var="record">
					        ${record.answerText},
					     </c:forEach>
					   </p>
					</c:if>
					 <c:forEach items="${mQuestion.manswers}" var="manswer"
						varStatus="answerStatus">
						<p class="list-group-item-text" id="${manswer.answer.id }">
							<span> &nbsp;&nbsp; ${CharArray[answerStatus.index] } .
								&nbsp;&nbsp; <c:choose>
									<c:when test="${mQuestion.question.type eq 0}">
										<input name="answer-select-radio" value="${manswer.answer.id}"
											type="radio">
									</c:when>
									<c:when test="${mQuestion.question.type eq 1}">
										<input name="answer-select-checkbox"
											value="${manswer.answer.id}" type="checkbox">
									</c:when>
									<c:otherwise>

									</c:otherwise>
								</c:choose> &nbsp;&nbsp; ${manswer.answer.name}
							</span><span><c:if test="${!questionnaire.isLocked }">
									<a href="#" class="remove-answer"><fmt:message
											key="global.remove" /></a>
								</c:if></span><span class="span-suffix pull-right">投票数:${manswer.answer.supportCount}&nbsp;&nbsp;比率:${manswer.rate}</span>
						</p>
					</c:forEach>
				</li>
			</c:forEach>
		</ul>
	</div>

	<div class="row">
		<div class="content" id="question-form-container"></div>
	</div>
	<div class="row">
		<div class="col-md-4 col-sm-offset-4">
			<button class="btn btn-lg btn-success btn-block" id="add-question">+</button>
		</div>
	</div>
	<div class="content">
		<a
			href="<c:url value="/questionnaire/questionnaire/edit/${questionnaire.id}"/>"
			class="btn btn-sm btn-primary"><fmt:message key="global.edit" /></a>
			<!-- 
		<a
			href="<c:url value="/questionnaire/questionnaire/remove/${questionnaire}.id"/>"
			class="btn btn-sm btn-danger"><fmt:message key="global.remove" /></a>
			 -->
		<a href="<c:url value="/questionnaire/questionnaire/home"/>"
			class="btn btn-sm btn-info"><fmt:message key="global.back" /></a>
	</div>
</div>
