<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/questionnaireType/questionnaireType.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/questionnaireType/questionnaireType.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="questionnaire.questionnaireType.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/questionnaire/questionnaireType/input"/>"> <fmt:message
				key="questionnaire.questionnaireType.add"></fmt:message>
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
		<div class="content">
			<form action="<c:url value="/questionnaire/questionnaireType/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|name" value="${searchParams.name}"
		placeholder="<fmt:message key="questionnaire.questionnaireType.name"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|orderIndex" value="${searchParams.orderIndex}"
		placeholder="<fmt:message key="questionnaire.questionnaireType.orderIndex"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|type" value="${searchParams.type}"
		placeholder="<fmt:message key="questionnaire.questionnaireType.type"></fmt:message>"
		class="form-control input-sm">
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
		  <div id="msg"></div>
	       <button class="btn btn-primary btn-sm multi-remove"><fmt:message key="global.remove"></fmt:message></button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='questionnaire.questionnaireType.name'></fmt:message></th>   
 <th><fmt:message key='questionnaire.questionnaireType.orderIndex'></fmt:message></th>   
 <th><fmt:message key='questionnaire.questionnaireType.type'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="questionnaireType">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${questionnaireType.id}"></td>
								<td>${questionnaireType.name}</td>   
 <td>${questionnaireType.orderIndex}</td>   
 <td>${questionnaireType.type}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/questionnaire/questionnaireType/edit/${questionnaireType.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/questionnaire/questionnaireType/remove/${questionnaireType.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/questionnaire/questionnaireType/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
