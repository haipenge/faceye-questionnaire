<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/question/question.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/question/question.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="questionnaire.question.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="questionnaire.question.name"></fmt:message></td>
	<td>${question.name}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.question.isMultiSelect"></fmt:message></td>
	<td>${question.isMultiSelect}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.question.num"></fmt:message></td>
	<td>${question.num}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->



			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/questionnaire/question/edit/${question}.id"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/questionnaire/question/remove/${question}.id"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/questionnaire/question/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
