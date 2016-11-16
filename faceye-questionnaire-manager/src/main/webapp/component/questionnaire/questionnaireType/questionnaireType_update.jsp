<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/questionnaireType/questionnaireType.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/questionnaireType/questionnaireType.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty questionnaireType.id}">
					<fmt:message key="questionnaire.questionnaireType.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="questionnaire.questionnaireType.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/questionnaire/questionnaireType/save" method="post" role="form" cssClass="form-horizontal"
			commandName="questionnaireType">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="name"> <spring:message
			code="questionnaire.questionnaireType.name"/>
	</label>
	<div class="col-md-6">
		<form:input path="name" cssClass="form-control"/>
		<form:errors path="name" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="orderIndex"> <spring:message
			code="questionnaire.questionnaireType.orderIndex"/>
	</label>
	<div class="col-md-6">
		<form:input path="orderIndex" cssClass="form-control"/>
		<form:errors path="orderIndex" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="type"> <spring:message
			code="questionnaire.questionnaireType.type"/>
	</label>
	<div class="col-md-6">
		<form:input path="type" cssClass="form-control"/>
		<form:errors path="type" cssClass="error"/>
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
