<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/questionnaire/questionnaire/questionnaire.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/questionnaire/questionnaire.js"/>"></script>
<div class="list-group" style="margin-top: 10px;">
	<c:forEach items="${questionnaires}" var="questionnaire" varStatus="status">
		<a href="<c:url value="/questionnaire/questionnaire/getQuestionnaire/${questionnaire.id}"/>" class="list-group-item p-a" style="padding-left:5px;">
			<div class="row m-a-0">
				<div class="col-sm-3 col-xs-3 col-lg-3 m-a-0">
					<c:choose>
						<c:when test="${not empty questionnaire.uploadFiles }">
							<img src="${questionnaire.uploadFiles[0].url}" class="img-responsive img-rounded m-a-0" style="width:100%;height:auto;">
						</c:when>
						<c:otherwise>
			              <img src="<c:url value="/images/questionnaire-default.jpg"/>" class="img-responsive img-rounded" style="width:100%;height:auto;">
			       </c:otherwise>
					</c:choose>
				</div>
				<div class="col-sm-9 col-xs-9 col-log-9 m-a-0">
					<h5 class="list-group-item-heading">${status.count}.${questionnaire.name}</h5>
					<p class="list-group-item-text">${questionnaire.remark}</p>
					<p class="text-muted">
						<span class="span-suffix"><fmt:message key="questionnaire.questionnaire.createDate" />:<fmt:formatDate value="${questionnaire.createDate}" pattern="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;
						<c:if test="${questionnaire.isClosed}">
							<span class="text-danger" style="font-size: 9px;"><fmt:message key="questionnaire.questionnaire.closed" /></span>
						</c:if>
					</p>
				</div>
			</div>
		</a>
	</c:forEach>
</div>
