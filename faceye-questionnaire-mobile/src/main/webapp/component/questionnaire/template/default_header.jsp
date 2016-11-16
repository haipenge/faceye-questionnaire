<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<!-- 
		<div class="col-xs-3 col-sm-2">
			<div class="btn-group">
				<button type="button" class="btn btn-sm btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">&#9776;<fmt:message key="shop.mobile"/></button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<c:url value="/setting/shop/home"/>"><fmt:message key="global.home"></fmt:message></a>
					<a class="dropdown-item" href="/default/forward?forward=shop.about"><fmt:message key="global.about" /></a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<c:url value="/order/order/home"/>"><fmt:message key="order.order" /></a> <a
						class="dropdown-item" href="<c:url value="/order/cartItem/home"/>"><fmt:message key="order.cart" /></a>
					<div class="dropdown-divider"></div>
					<c:if test="${not empty pageContext.request.userPrincipal}">
						<a class="dropdown-item" href="#">Welcome <sec:authentication property="principal.username" /></a>
						<a class="dropdown-item"
							href="<c:url value="/security/web/user/profile/${pageContext.request.userPrincipal.name}"/>"><fmt:message
								key="security.web.user.profile"></fmt:message></a>
						<a class="dropdown-item" href="<c:url value="/j_spring_security_logout"/>"><fmt:message
								key="security.web.user.logout"></fmt:message></a>
					</c:if>
					<c:if test="${empty pageContext.request.userPrincipal}">
						<a class="dropdown-item" href="<c:url value="/login"/>"><fmt:message key="security.web.user.login" /></a>
						<a class="dropdown-item" href="<c:url value="/register"/>"><fmt:message key="security.web.user.register" /></a>
					</c:if>
				</div>
			</div>
		</div>
		 -->

	<!--  <div class="collapse navbar-toggleable-xs" id="exCollapsingNavbar2">-->
		<ul class="nav navbar-nav" style="padding-left:5px;padding-right:5px;">
			</li>
			<c:if test="${not empty questionnaireTypes }">
				<c:forEach var="questionnaireType" items="${questionnaireTypes}" varStatus="status">
					<li class="nav-item"><a class="nav-link" href="<c:url value="/questionnaire/questionnaire/home?EQ|questionnaireType.$id=${questionnaireType.id }"/>">${questionnaireType.name }</a>
					</li>					
				</c:forEach>
			</c:if>
		</ul>
		<!-- 
		<form class="form-inline m-a-0 p-a-0"  action="<c:url value="/questionnaire/questionnaire/home"/>">
			<fieldset class="form-group">
				<input type="text" class="form-control form-control-sm" name="like|name" placeholder="输入关键字进行搜索..."> <span class="input-group-btn"> </span>
			</fieldset>
			<button class="btn btn-sm btn-secondary" type="submit">搜索</button>
		</form>
		
	</div>
	 -->
	<!-- 
		<div class="col-xs-12 col-sm-12 m-a-0 p-a-0">
			<form class="form-inline navbar-form pull-right m-a-0 p-a-0" action="<c:url value="/emergency/emergencyKnowledge/home"/>">
				<div class="input-group">
					<input type="text" class="form-control form-control-sm" name="like|name" placeholder="<fmt:message key="emergency.mobile.search.tip"/>"> <span class="input-group-btn">
						<button class="btn btn-sm btn-secondary" type="submit"><fmt:message key="emergency.mobile.search"/></button>
					</span>
				</div>
			</form>
		</div>
		-->
</div>
