<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/questionnaire/questionnaire/questionnaire.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/questionnaire/questionnaire.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="questionnaire.questionnaire.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/questionnaire/questionnaire/input"/>"> <fmt:message key="questionnaire.questionnaire.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/questionnaire/questionnaire/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="like|name" value="${searchParams.name}" placeholder="<fmt:message key="questionnaire.questionnaire.name"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<select name="EQ|questionnaireType.$id" class="form-control">
								<option value="">Type</option>
								<c:forEach var="type" items="${questionnaireTypes}">
									<option value="${type.id}" <c:if test="${searchParams.questionnaireTypeid eq type.id}"> selected</c:if>>${type.name }</option>
								</c:forEach>
							</select>
						</div>


						<!--@generate-entity-jsp-query-detail@-->




						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
			<button class="btn btn-success btn-sm push2Weixin">
				<fmt:message key="questionnaire.questionnaire.push.2.weiixn" />
			</button>
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div class="content">
				<c:forEach var="account" items="${accounts}">
					<label class="checkbox-inline"> <input type="checkbox" id="${account.id }" name="weixinAccountId" value="${account.id }"> ${account.name}
					</label>
				</c:forEach>
			</div>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='questionnaire.questionnaire.name'></fmt:message></th>
							<th>Image</th>
							<th><fmt:message key='questionnaire.questionnaire.createDate'></fmt:message></th>
							<th><fmt:message key='questionnaire.questionnaire.isClosed'></fmt:message></th>
							<th><fmt:message key='questionnaire.questionnaire.isLocked'></fmt:message></th>
							<th><fmt:message key="questionnaire.questionnaire.accessCount" /></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view" /></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
							<th><fmt:message key="questionnaire.questionnaire.isLocked"></fmt:message></th>
							<th><fmt:message key="questionnaire.questionnaire.isClosed" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${questionnaires}" var="questionnaire">
							<tr>
								<td><input type="checkbox" name="check-single" value="${questionnaire.id}"></td>
								<td>${questionnaire.name}</td>
								<td><c:choose>
										<c:when test="${not empty questionnaire.uploadFiles}">
											<img src="${questionnaire.uploadFiles[0].url}" class="img-responsive img-rounded img-thumbnail img-small">
										</c:when>
										<c:otherwise>No Pic</c:otherwise>
									</c:choose></td>

								<td><fmt:formatDate value="${questionnaire.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
								<td><f:boolean value="${questionnaire.isClosed}" /></td>
								<td><f:boolean value="${questionnaire.isLocked}" /></td>
								<td>${questionnaire.accessCount }</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/questionnaire/questionnaire/detail/${questionnaire.id}"/>"> <fmt:message key="global.view"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/questionnaire/questionnaire/edit/${questionnaire.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><c:if test="${!questionnaire.isLocked }">
										<a href="<c:url value="/questionnaire/questionnaire/remove/${questionnaire.id}"/>"> <fmt:message key="global.remove"></fmt:message>
										</a>
									</c:if></td>
								<td><c:choose>
										<c:when test="${questionnaire.isLocked}">
											<a href="<c:url value="/questionnaire/questionnaire/lock?id=${questionnaire.id}&isLocked=false"/>"> <fmt:message key="questionnaire.questionnaire.unlock"></fmt:message></a>
										</c:when>
										<c:otherwise>
											<a href="<c:url value="/questionnaire/questionnaire/lock?id=${questionnaire.id}&isLocked=true"/>"> <fmt:message key="questionnaire.questionnaire.lock"></fmt:message></a>
										</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${questionnaire.isClosed}">
											<a href="<c:url value="/questionnaire/questionnaire/close?id=${questionnaire.id}&isClosed=false"/>"><fmt:message key="questionnaire.questionnaire.open" /></a>
										</c:when>
										<c:otherwise>
											<a href="<c:url value="/questionnaire/questionnaire/close?id=${questionnaire.id}&isClosed=true"/>"><fmt:message key="questionnaire.questionnaire.close" /></a>
										</c:otherwise>
									</c:choose></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/questionnaire/questionnaire/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
