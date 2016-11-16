<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/questionnaire/questionnaire/questionnaire.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/questionnaire/questionnaire.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/answer/answer.js"/>"></script>
<div class="card card-block p-a" style="background-color: white;">
	<div class="content">
		<input type="hidden" name="questionnaireId" value="${questionnaire.id }">
		<h5>${questionnaire.name}</h5>
		<hr>
		<p class="text-muted">
			已有&nbsp;&nbsp;<span class="text-success">${questionnaire.accessCount}</span>&nbsp;&nbsp;人测试过&nbsp;&nbsp;
			<fmt:formatDate value="${questionnaire.createDate }" pattern="yyyy-MM-dd" />
		</p>
		<c:choose>
			<c:when test="${not empty questionnaire.uploadFiles }">
				<img src="${questionnaire.uploadFiles[0].url}" class="img-responsive img-rounded m-a-0" style="width: 100%; height: auto;">
			</c:when>
			<c:otherwise>
				<img src="<c:url value="/images/questionnaire-default.jpg"/>" class="img-responsive img-rounded" style="width: 100%; height: auto;">
			</c:otherwise>
		</c:choose>
		<p class="text-muted p-a-lg">${questionnaire.remark}</p>
		<script type="text/javascript">
			/*微信广告-20:3*/
			var cpro_id = "u2703199";
		</script>
		<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/cm.js"></script>
		<c:if test="${questionnaire.isClosed }">
			<div class="bg-danger">
				<fmt:message key="questionnaire.questionnaire.close.tip" />
			</div>
		</c:if>
	</div>
	<hr>
	<p>
		<span style="font-weight: bold; color: red; margin-left: 15px; margin-right: 15px;"><b>&darr;</b></span><b><fmt:message key="questionnaire.questionnaire.original.view.tip" /></b>
	</p>
	<div class="content">
		<a href="<c:url value="/questionnaire/questionnaire/getQuestionnaireStep2/${questionnaire.id}?accountId=${param.accountId }"/>"><fmt:message
				key="questionnaire.questionnaire.original.view" /></a>
	</div>
	<div class="msg m-t-md" id="msg"></div>
	<div class="content m-t-lg" style="margin-top: 15px;">
		<a href="<c:url value="/questionnaire/questionnaire/home"/>">更多测试</a>
	</div>
</div>
<h4>更多推荐</h4>
<div class="card-group">
	<c:forEach var="more" items="${questionnaires}">
		<div class="card">
			<a href="<c:url value="/questionnaire/questionnaire/getQuestionnaire/${more.id}"/>"> <c:choose>
					<c:when test="${not empty more.uploadFiles }">
						<img src="${more.uploadFiles[0].url}" class="card-img-top" style="width: 100%; height: auto;">
					</c:when>
					<c:otherwise>
						<img src="<c:url value="/images/questionnaire-default.jpg"/>" class="card-img-top" style="width: 100%; height: auto;">
					</c:otherwise>
				</c:choose>
			</a>
			<div class="card-block">
				<a href="<c:url value="/questionnaire/questionnaire/getQuestionnaire/${more.id}"/>">${more.name}</a>
			</div>
		</div>
	</c:forEach>
</div>
<!-- tujia
<script type="text/javascript">
	var cpro_id = "u2703042";
</script>
<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/mi.js"></script>
 -->


