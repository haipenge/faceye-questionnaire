<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/answer/answer.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/answer/answer.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="questionnaire.answer.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="questionnaire.answer.name"></fmt:message></td>
	<td>${answer.name}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answer.mark"></fmt:message></td>
	<td>${answer.mark}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answer.supportCount"></fmt:message></td>
	<td>${answer.supportCount}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->



			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/questionnaire/answer/edit/${answer}.id"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/questionnaire/answer/remove/${answer}.id"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/questionnaire/answer/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
