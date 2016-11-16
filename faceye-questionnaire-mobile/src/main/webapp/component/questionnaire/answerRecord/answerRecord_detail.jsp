<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/answerRecord/answerRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/answerRecord/answerRecord.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="questionnaire.answerRecord.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="questionnaire.answerRecord.rabbitId"></fmt:message></td>
	<td>${answerRecord.rabbitId}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answerRecord.questionnaireId"></fmt:message></td>
	<td>${answerRecord.questionnaireId}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answerRecord.questionId"></fmt:message></td>
	<td>${answerRecord.questionId}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answerRecord.answerId"></fmt:message></td>
	<td>${answerRecord.answerId}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answerRecord.createDate"></fmt:message></td>
	<td>${answerRecord.createDate}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->





			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/questionnaire/answerRecord/edit/${answerRecord}.id"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/questionnaire/answerRecord/remove/${answerRecord}.id"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/questionnaire/answerRecord/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
