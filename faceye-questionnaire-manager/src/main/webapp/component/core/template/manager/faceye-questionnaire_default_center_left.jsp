<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<li><a href="#"><i class="fa fa-smile-o"></i><span><fmt:message key="questionnaire.questionnaire"/></span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/questionnaire/questionnaire")%>"><a href="<c:url value="/questionnaire/questionnaire/home"/>"><fmt:message
					key="questionnaire.questionnaire.manager"></fmt:message></a></li>
	</ul></li>

<li><a href="#"><i class="fa fa-smile-o"></i><span>Generater Component</span></a>
	<ul class="sub-menu">
		<!--
		<li class="<%=JspUtil.isActive(request, "/spider/link")%>"><a href="/spider/link/home"><fmt:message key="spider.link.manager"></fmt:message></a></li>
		-->
		<li  class="<%=JspUtil.isActive(request, "answerStat")%>"><a  href="/questionnaire/answerStat/home"><fmt:message key="questionnaire.answerStat.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "questionnaireType")%>"><a  href="/questionnaire/questionnaireType/home"><fmt:message key="questionnaire.questionnaireType.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "discuss")%>"><a  href="/questionnaire/discuss/home"><fmt:message key="questionnaire.discuss.manager"></fmt:message></a></li>
<!--@generate-entity-manager-list-group-item@-->



	</ul></li>
