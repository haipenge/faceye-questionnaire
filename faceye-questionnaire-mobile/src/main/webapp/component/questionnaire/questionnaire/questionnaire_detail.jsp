<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/questionnaire/questionnaire/questionnaire.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/questionnaire/questionnaire.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/answer/answer.js"/>"></script>
<div class="card card-block">
	<div class="content">
		<input type="hidden" name="questionnaireId" value="${questionnaire.id }">
		<h5>${questionnaire.name}</h5>
		<p>${questionnaire.remark}</p>
		<c:if test="${questionnaire.isClosed }">
			<div class="bg-danger">
				<fmt:message key="questionnaire.questionnaire.close.tip" />
			</div>
		</c:if>
	</div>
	<c:set var="CharStr" value="A,B,C,D,E,F,G,H,I,J,K" />
	<c:set var="CharArray" value="${fn:split(CharStr, ',')}" />
	<div class="content">
		<ul class="list-group">
			<c:forEach items="${mQuestions}" var="mQuestion" varStatus="status">
				<li class="list-group-item" id="${mQuestion.question.id}">
					<h6 class="list-group-item-heading">${status.count}.${mQuestion.question.name}</h6> <c:if test="${mQuestion.question.type eq 2 }">
						<p class="list-group-item-text">
							<textarea name="answer-text-${mQuestion.question.id}" rows="2" class="form-control" <c:if test="${questionnaire.isClosed}">readOnly</c:if> class="on-text-check"><c:if
									test="${not empty mQuestion.answerTextRecords}">${mQuestion.answerTextRecords[0].answerText}</c:if></textarea>
						</p>
					</c:if> <c:if test="${mQuestion.question.type eq 0 or mQuestion.question.type eq 1  }">
						<c:forEach items="${mQuestion.manswers}" var="manswer" varStatus="answerStatus">
							<p class="list-group-item-text">
								<c:choose>
									<c:when test="${mQuestion.question.type eq 0}">
										<input name="answer-select-radio-${mQuestion.question.id}" <c:if test="${questionnaire.isClosed}">disabled</c:if> class="on-check" value="${manswer.answer.id}"
											<c:if test="${manswer.isChecked }">checked</c:if> type="radio">
									</c:when>
									<c:when test="${mQuestion.question.type eq 1}">
										<input name="answer-select-checkbox-${mQuestion.question.id }" <c:if test="${questionnaire.isClosed}">disabled</c:if> class="on-check" value="${manswer.answer.id}"
											<c:if test="${manswer.isChecked }">checked</c:if> type="checkbox">
									</c:when>
									<c:otherwise>

									</c:otherwise>
								</c:choose>
								&nbsp;&nbsp; ${CharArray[answerStatus.index] } . ${manswer.answer.name}
							</p>
						</c:forEach>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="msg m-t-md" id="msg"></div>
	<div class="content m-t-md">
		<c:choose>
			<c:when test="${questionnaire.isClosed}">
				<div class="bg-danger">
					<fmt:message key="questionnaire.questionnaire.close.tip" />
				</div>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-primary btn-block" id="submit-questionnaire">
					<fmt:message key="global.submit" />
				</button>
			</c:otherwise>
		</c:choose>
	</div>
	<c:import url="/component/template/emergency/copyright_footer.jsp" />
</div>
