<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/question/question.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/question/question.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty question.id}">
					<fmt:message key="questionnaire.question.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="questionnaire.question.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/questionnaire/question/save" method="post" role="form" cssClass="form-horizontal"
			commandName="question">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="name"> <spring:message
			code="questionnaire.question.name"/>
	</label>
	<div class="col-md-6">
		<form:input path="name" cssClass="form-control"/>
		<form:errors path="name" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="isMultiSelect"> <spring:message
			code="questionnaire.question.isMultiSelect"/>
	</label>
	<div class="col-md-6">
		<form:input path="isMultiSelect" cssClass="form-control"/>
		<form:errors path="isMultiSelect" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="num"> <spring:message
			code="questionnaire.question.num"/>
	</label>
	<div class="col-md-6">
		<form:input path="num" cssClass="form-control"/>
		<form:errors path="num" cssClass="error"/>
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
