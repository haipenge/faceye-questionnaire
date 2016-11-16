<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/answerRecord/answerRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/answerRecord/answerRecord.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty answerRecord.id}">
					<fmt:message key="questionnaire.answerRecord.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="questionnaire.answerRecord.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/questionnaire/answerRecord/save" method="post" role="form" cssClass="form-horizontal"
			commandName="answerRecord">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="rabbitId"> <spring:message
			code="questionnaire.answerRecord.rabbitId"/>
	</label>
	<div class="col-md-6">
		<form:input path="rabbitId" cssClass="form-control"/>
		<form:errors path="rabbitId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="questionnaireId"> <spring:message
			code="questionnaire.answerRecord.questionnaireId"/>
	</label>
	<div class="col-md-6">
		<form:input path="questionnaireId" cssClass="form-control"/>
		<form:errors path="questionnaireId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="questionId"> <spring:message
			code="questionnaire.answerRecord.questionId"/>
	</label>
	<div class="col-md-6">
		<form:input path="questionId" cssClass="form-control"/>
		<form:errors path="questionId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="answerId"> <spring:message
			code="questionnaire.answerRecord.answerId"/>
	</label>
	<div class="col-md-6">
		<form:input path="answerId" cssClass="form-control"/>
		<form:errors path="answerId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="createDate"> <spring:message
			code="questionnaire.answerRecord.createDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="createDate" cssClass="form-control"/>
		<form:errors path="createDate" cssClass="error"/>
	</div>
</div>
<!--@generate-entity-jsp-property-update@-->





				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
