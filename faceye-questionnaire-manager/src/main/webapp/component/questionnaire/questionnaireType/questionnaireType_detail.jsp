<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/questionnaireType/questionnaireType.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/questionnaireType/questionnaireType.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="questionnaire.questionnaireType.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="questionnaire.questionnaireType.name"></fmt:message></td>
	<td>${questionnaireType.name}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.questionnaireType.orderIndex"></fmt:message></td>
	<td>${questionnaireType.orderIndex}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.questionnaireType.type"></fmt:message></td>
	<td>${questionnaireType.type}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->



			</table>
		</div>
	</div>
</div>
