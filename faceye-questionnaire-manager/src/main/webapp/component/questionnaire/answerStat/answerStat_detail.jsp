<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/answerStat/answerStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/answerStat/answerStat.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="questionnaire.answerStat.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="questionnaire.answerStat.title"></fmt:message></td>
	<td>${answerStat.title}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answerStat.stitle"></fmt:message></td>
	<td>${answerStat.stitle}</td>
</tr>
<tr>
	<td><fmt:message key="questionnaire.answerStat.remark"></fmt:message></td>
	<td>${answerStat.remark}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->



			</table>
		</div>
	</div>
</div>
