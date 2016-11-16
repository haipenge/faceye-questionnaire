<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/discuss/discuss.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/discuss/discuss.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty discuss.id}">
					<fmt:message key="questionnaire.discuss.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="questionnaire.discuss.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/questionnaire/discuss/save" method="post" role="form" cssClass="form-horizontal"
			commandName="discuss">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="content"> <spring:message
			code="questionnaire.discuss.content"/>
	</label>
	<div class="col-md-6">
		<form:input path="content" cssClass="form-control"/>
		<form:errors path="content" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="questionnaireId"> <spring:message
			code="questionnaire.discuss.questionnaireId"/>
	</label>
	<div class="col-md-6">
		<form:input path="questionnaireId" cssClass="form-control"/>
		<form:errors path="questionnaireId" cssClass="error"/>
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
