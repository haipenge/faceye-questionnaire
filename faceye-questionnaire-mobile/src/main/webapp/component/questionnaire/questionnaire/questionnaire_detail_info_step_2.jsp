<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/questionnaire/questionnaire/questionnaire.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/questionnaire/questionnaire.js"/>"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<!-- 
<script type="text/javascript" src="<c:url value="/js/lib/zeroclipboard/ZeroClipboard.min.js"/>"></script>
 -->
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/answer/answer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/weixin/js-sdk/weixin.js"/>"></script>
<c:set var="CharStr" value="A,B,C,D,E,F,G,H,I,J,K" />
<c:set var="CharArray" value="${fn:split(CharStr, ',')}" />
<div class="card card-inverse card-warning p-a-0" style="background-color: white;">
	<div class="card-block p-a">
		<input type="hidden" name="questionnaireId" value="${questionnaire.id}"> <input type="hidden" name="accountId" value="${param.accountId}">
		<h5>${questionnaire.name}</h5>
		<img src="${questionnaire.uploadFiles[0].url}" class="img-responsive img-rounded m-a-0" style="width: 100%; height: auto; display: none;">
		<p id="questionnaire-remark">${questionnaire.remark}</p>
		<center>
			<button type="button" class="btn btn-danger btn-block" id="questionnaire-start-test-btn">
				<fmt:message key="questionnaire.questionnaire.start" />
			</button>
		</center>
		<ul class="list-group" id="question-items" style="display: none;">
			<c:forEach items="${mQuestions}" var="mQuestion" varStatus="status">
				<li class="list-group-item" id="${mQuestion.question.id}">
					<h6 class="list-group-item-heading">${status.count}/${fn:length(mQuestions)}.${mQuestion.question.name}</h6> <c:if test="${mQuestion.question.type eq 2 }">
						<p class="list-group-item-text">
							<textarea name="answer-text-${mQuestion.question.id}" rows="2" class="form-control" <c:if test="${questionnaire.isClosed}">readOnly</c:if> class="on-text-check"><c:if
									test="${not empty mQuestion.answerTextRecords}">${mQuestion.answerTextRecords[0].answerText}</c:if></textarea>
						</p>
					</c:if> <c:if test="${mQuestion.question.type eq 0 or mQuestion.question.type eq 1  }">
						<c:forEach items="${mQuestion.manswers}" var="manswer" varStatus="answerStatus">
							<p class="list-group-item-text" style="margin-top:5px;">
								<c:choose>
									<c:when test="${mQuestion.question.type eq 0}">
										<input name="answer-select-radio-${mQuestion.question.id}" <c:if test="${questionnaire.isClosed}">disabled</c:if> class="on-check" value="${manswer.answer.id}"
											<c:if test="${manswer.isChecked }">checked</c:if> type="radio">
									</c:when>
									<c:when test="${mQuestion.question.type eq 1}">
										<input name="answer-select-checkbox-${mQuestion.question.id }" <c:if test="${questionnaire.isClosed}">disabled</c:if> class="on-check" value="${manswer.answer.id}"
											<c:if test="${manswer.isChecked }">checked</c:if> type="checkbox">
									</c:when>
									<c:otherwise>

									</c:otherwise>
								</c:choose>
								&nbsp;&nbsp; <span class="on-check">${CharArray[answerStatus.index] } . ${manswer.answer.name}</span>
							</p>
						</c:forEach>
					</c:if>
				</li>
			</c:forEach>
		</ul>
		<!-- 显示计算提示信息 -->
		<div class="msg m-a text-center" id="msg" style="display: none; margin-bottom: 5px; padding: 15px;"></div>
		<!-- 显示计算结果 -->
		<div class="text-center m-a" id="answer-stat-result" style="display: none;">
			<blockquote class="card-blockquote" style="margin-bottom: 5px;">
				<p class="text-center p-a">
					<span id="stat-result" style="margin-right: 10px;">${questionnaire.resultTip}</span>
					<!--
					<span class="text-warning">快去让小伙伴们也测测!</span> 
					<button id="copy" data-clipboard-target="stat-result-stitle" type="button" class="btn btn-success-outline btn-sm" style="margin-left: 15px;">
						<fmt:message key="global.copy" />
					</button>
					<span id="copy-msg" style="display: none; margin-left: 5px; color: gray;"></span>
 -->
				</p>
			</blockquote>
			<center>
				<button type="button" class="btn btn-success btn-sm text-center" id="btn-share">
					<fmt:message key="questionnaire.questionnaire.weixin.share" />
				</button>
				<!-- 
				<button type="button" class="btn btn-warning btn-sm text-center">
					<fmt:message key="questionnaire.questionnaire.weixin.discuss" />
				</button>
				 -->
			</center>
			<center>
				<p class="center-block text-muted p-a-lg">
					<small>公众号:克拉客</small>
				</p>
			</center>
		</div>
		<div id="answer-ad" style="margin-top: 2px; margin-left: -20px;">
			<script type="text/javascript">
				/*微信广告-20:3*/
				var cpro_id = "u2703199";
			</script>
			<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/cm.js"></script>
		</div>
		<p class="card-text" style="margin-top: 15px;">
			<a href="<c:url value="/questionnaire/questionnaire/home"/>"><font color="red"><b>&rarr;</b></font>更多测试</a>
		</p>
		<div>
			<script type="text/javascript">
				/*微信底部*/
				var cpro_id = "u2703040";
			</script>
			<script src="http://cpro.baidustatic.com/cpro/ui/cm.js" type="text/javascript"></script>
		</div>
	</div>
</div>


