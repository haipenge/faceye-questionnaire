<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/discuss/discuss.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/discuss/discuss.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="questionnaire.discuss.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="questionnaire.discuss.content"></fmt:message></td>
	<td>${discuss.content}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.discuss.questionnaireId"></fmt:message></td>
	<td>${discuss.questionnaireId}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->


			</table>
		</div>
	</div>
</div>
