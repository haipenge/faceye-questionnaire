<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload.css"/>">
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui.css"/>">
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/lib/ueditor/ueditor.config.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/lib/ueditor/ueditor.all.min.js"/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/lib/ueditor/lang/zh-cn/zh-cn.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/questionnaire/questionnaire/questionnaire.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/questionnaire/questionnaire/questionnaire.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty questionnaire.id}">
					<fmt:message key="questionnaire.questionnaire.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="questionnaire.questionnaire.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/questionnaire/questionnaire/save" method="post" role="form" cssClass="form-horizontal" commandName="questionnaire">
			<form:hidden path="id" />
			<form:hidden path="accessCount" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="questionnaire.questionnaire.name" />
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="remark"> <spring:message code="questionnaire.questionnaire.remark" />
					</label>
					<div class="col-md-8">
						<form:textarea path="remark" id="editor" style="height:150px;" placeholder="Enter text ..." />
						<form:errors path="remark" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="resultTip"> <spring:message code="questionnaire.questionnaire.resultTip" />
					</label>
					<div class="col-md-6">
						<form:input path="resultTip" cssClass="form-control" />
						<form:errors path="resultTip" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isClosed"> <spring:message code="questionnaire.questionnaire.isClosed" />
					</label>
					<div class="col-md-6">
						<label class="radio-inline"><form:radiobutton path="isClosed" value="true" /><f:boolean value="true"/></label> <label class="radio-inline"><form:radiobutton path="isClosed" value="false" /><f:boolean value="false"/></label>
						<form:errors path="isClosed" cssClass="error" />
					</div>
				</div>
				
				
				<!--@generate-entity-jsp-property-update@-->

				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="questionnaire.questionnaire.next"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	window.UEDITOR_HOME_URL = "/"
	var ue = UE.getEditor('editor');
	/**
	 *afterExecCommand
	 *insertimage
	 *afterinsertimage
	 */
	ue.addListener('afterinsertimage', function(type, opt) {
		//get upload img url:opt[0]._src
		var images = [];
		/* if (opt) {
			for (var i = 0; i < opt.length; i++) {
				var img = opt[i];
				images.push(img._src);
			}
		} */
		//alert('fire after insert image.'+opt[0]._src);
	});
	//simpleupload
	ue.addListener('afterExecCommand', function(type, opt, arg1) {
		//alert(type+','+opt+',');
		/* var args = '';
		for (var i = 0; i < arg1.length; i++) {
			args += arg1[i];
		} */
		//alert(args);
	});

	ue.addListener('afterinserthtml', function(type, opt) {
		//alert('fire after insert html .......' + opt);
	});

	ue.addListener('contentChange', function() {
		var content = ue.getContent();
		//var regex="<img[^>]*?data\\-src=[\"\\']?([^\"\\'>]+)[\"\\']?[^>]*\\/>";
		//var regex=/<img[^>]*?src=[\"\']?([^\"\'>]+)[\"\']?[^>]*\/>/gim;
		/* var regexp = new RegExp("<img[^>]*?src=[\"\\']?([^\"\\'>]+)[\"\\']?[^>]*\\/>", 'g');
		var images = [];
		alert("content is:" + content);
		if (content && content.length > 0) {
			var record = '';
			while ((record = regexp.exec(content)) != null) {
				images.push(RegExp.$1);
			}
		}
		if (images && images.length > 0) {
			var str = '';
			for (var i = 0; i<images.length>0; i++) {
				str += '' + i + images[i];
			}
			alert("distil img result is:" + str);
		} */
		//
		var root = ue.body;
		var imageNodes = UE.dom.domUtils.getElementsByTagName(root, 'img');
		if (imageNodes && imageNodes.length > 0) {
			for (var i = 0; i < imageNodes.length; i++) {
				var node = imageNodes[i];
				UE.dom.domUtils.addClass(node, 'img-responsive');
				UE.dom.domUtils.setStyles(node, {
					'max-width' : '100%',
					'height' : 'auto'
				});
			}
		}
		var content = ue.getContent();
	});

	function isFocus(e) {
		alert(UE.getEditor('editor').isFocus());
		UE.dom.domUtils.preventDefault(e)
	}
	function setblur(e) {
		UE.getEditor('editor').blur();
		UE.dom.domUtils.preventDefault(e);
	}
	function insertHtml() {
		var value = prompt('插入html代码', '');
		UE.getEditor('editor').execCommand('insertHtml', value);
	}
	function createEditor() {
		enableBtn();
		UE.getEditor('editor');
	}
	function getAllHtml() {
		alert(UE.getEditor('editor').getAllHtml());
	}
	function getContent() {
		var arr = [];
		arr.push("使用editor.getContent()方法可以获得编辑器的内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getContent());
		alert(arr.join("\n"));
	}
	function getPlainTxt() {
		var arr = [];
		arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getPlainTxt());
		alert(arr.join('\n'));
	}
	function setContent(isAppendTo) {
		var arr = [];
		arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
		UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
		alert(arr.join("\n"));
	}
	function setDisabled() {
		UE.getEditor('editor').setDisabled('fullscreen');
		disableBtn("enable");
	}

	function setEnabled() {
		UE.getEditor('editor').setEnabled();
		enableBtn();
	}

	function getText() {
		//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		var range = UE.getEditor('editor').selection.getRange();
		range.select();
		var txt = UE.getEditor('editor').selection.getText();
		alert(txt);
	}

	function getContentTxt() {
		var arr = [];
		arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
		arr.push("编辑器的纯文本内容为：");
		arr.push(UE.getEditor('editor').getContentTxt());
		alert(arr.join("\n"));
	}
	function hasContent() {
		var arr = [];
		arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
		arr.push("判断结果为：");
		arr.push(UE.getEditor('editor').hasContents());
		alert(arr.join("\n"));
	}
	function setFocus() {
		UE.getEditor('editor').focus();
	}
	function deleteEditor() {
		disableBtn();
		UE.getEditor('editor').destroy();
	}
	function disableBtn(str) {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			if (btn.id == str) {
				UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
			} else {
				btn.setAttribute("disabled", "true");
			}
		}
	}
	function enableBtn() {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
		}
	}

	function getLocalData() {
		alert(UE.getEditor('editor').execCommand("getlocaldata"));
	}

	function clearLocalData() {
		UE.getEditor('editor').execCommand("clearlocaldata");
		alert("已清空草稿箱");
	}
</script>