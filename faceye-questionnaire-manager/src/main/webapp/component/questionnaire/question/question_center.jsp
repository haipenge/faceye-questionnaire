<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/questionnaire/question/question.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/questionnaire/question/question.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="questionnaire.question.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/questionnaire/question/input"/>"> <fmt:message
				key="questionnaire.question.add"></fmt:message>
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
			<form action="<c:url value="/questionnaire/question/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|name" value="${searchParams.name}"
		placeholder="<fmt:message key="questionnaire.question.name"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|isMultiSelect" value="${searchParams.isMultiSelect}"
		placeholder="<fmt:message key="questionnaire.question.isMultiSelect"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|num" value="${searchParams.num}"
		placeholder="<fmt:message key="questionnaire.question.num"></fmt:message>"
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
	       <button class="btn btn-primary btn-sm multi-remove"><fmt:message key="global.remove"></fmt:message></button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='questionnaire.question.name'></fmt:message></th>   
 <th><fmt:message key='questionnaire.question.isMultiSelect'></fmt:message></th>   
 <th><fmt:message key='questionnaire.question.num'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view"/></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="question">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${question.id}"></td>
								<td>${question.name}</td>   
 <td>${question.isMultiSelect}</td>   
 <td>${question.num}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/questionnaire/question/detail/${question.id}"/>">
										<fmt:message key="global.view"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/questionnaire/question/edit/${question.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/questionnaire/question/remove/${question.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/questionnaire/question/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
