/**
 * 说明:Questionnaire js 脚本 作者:@haipenge
 */
var Questionnaire = {
	/**
	 * 问题Form容器ID
	 */
	questionFormContainer : 'question-form-container',
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('.multi-remove').click(function() {
			Questionnaire.multiRemove();
		});
		$('#add-question').click(function() {
			Questionnaire.addQuestion(this);
		});
		$('#' + Questionnaire.questionFormContainer).delegate('#btn-save-answer', 'click', function() {
			Questionnaire.doQuestionSave();
		});
		// show-more
		$('#' + Questionnaire.questionFormContainer).delegate('.show-more', 'click', function() {
			$('.form-group-is-show').show();
			$('.show-more').hide();
			return false;
		});
		//inlineRadio3
		$('#' + Questionnaire.questionFormContainer).delegate('#inlineRadio2', 'click', function() {
			$('.answer-item').hide();
//			return false;
		});
		$('#' + Questionnaire.questionFormContainer).delegate('#inlineRadio1', 'click', function() {
			$('.answer-item').show();
//			return false;
		});
		$('#' + Questionnaire.questionFormContainer).delegate('#inlineRadio0', 'click', function() {
			$('.answer-item').show();
//			return false;
		});
		$('.push2Weixin').click(function(){
			Questionnaire.pushQuestionnaire2Weixin();
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/questionnaire/questionnaire/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var type = data.result ? '' : 'warning';
					var msg = new Msg({
						msg : data.msg,
						type : type
					});
					if (data.result) {
						var idArray = checkedIds.split(',');
						for (var i = 0; i < idArray.length; i++) {
							$('#' + idArray[i]).remove();
						}
					}
					msg.show();
				}
			});
		} else {
			var msg = new Msg({
				msg : '请选择要删除的数据',
				type : 'warning'
			});
			msg.show();
		}
	},
	/**
	 * 添加问题
	 */
	addQuestion : function(dom) {
		var html = '';
		html += '<form class="form-horizontal">';
		html += '<input type="hidden" name="id" value="">';
		html += '<div class="form-group">';
		html += '  <label for="question-name" class="col-sm-2 control-label">问题</label>';
		html += '  <div class="col-sm-6">';
		html += '<textarea  name="question-name" class="form-control" id="question-name" rows="4" placeholder="请输入问题"></textarea>';
		html += '</div>';
		html += '</div>';
		html += '<div class="form-group">';
		html += '  <label for="question-name" class="col-sm-2 control-label">问题类型</label>';
		html += '<div class="checkbox col-sm-6">';
		html += '<label class="radio-inline">';
		html += '<input type="radio" name="type" id="inlineRadio0" checked value="0"> 单选';
		html += '</label>';
		html += '<label class="radio-inline">';
		html += '<input type="radio" name="type" id="inlineRadio1" value="1"> 多选';
		html += '</label>';
		html += '<label class="radio-inline">';
		html += '<input type="radio" name="type" id="inlineRadio2" value="2"> 填空';
		html += '</label>';
		html += '</div>';
		html += '</div>';
		for (var i = 0; i < 10; i++) {
			html += '<div class="form-group answer-item';
			if (i > 4) {
				html += ' form-group-is-show"';
				html += ' style="display:none;"';
			} else {
				html += '"';
			}
			html += '>';
			html += '  <label for="answer-"' + i + ' class="col-sm-2 control-label">答案&nbsp;&nbsp;'
					+ Questionnaire.number2Char(i) + '</label>';
			html += '  <div class="col-sm-6">';
			html += '<input type="text" name="answer-' + i + '" class="form-control" id="answer-' + i
					+ '" placeholder="请输入答案">';
			html += '</div>';
			html += '</div>';
		}
		html += '<div class="from-group answer-item">';
		html += '  <div class="col-sm-1 col-sm-offset-7" style="padding:10px;">';
		html += '<a href="#" class="show-more">更多...</a> ';
		html += '</div>';
		html += '</div>';

		html += '<div class="form-group">';
		html += '  <div class="col-sm-6 col-sm-offset-2">';
		html += '<button type="button" class="btn btn-block btn-primary" id="btn-save-answer">提交</button>';
		html += '</div>';
		html += '</div>';
		html += '</form>';
		$('#' + Questionnaire.questionFormContainer).empty().append(html);
		$('#add-question').hide();
	},
	number2Char : function(num) {
		var numbers = [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ];
		var c = [ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N' ];
		return c[num];
	},
	/**
	 * 保存问题
	 */
	doQuestionSave : function() {
		var questionnaireId = $('input[name="questionnaireId"]').val();
		var questionName = $('textarea[name="question-name"]').val();
//		var isMultiSelect = $('input[name="isMultiSelect"]:checked').val();
		var type=$('input[name="type"]:checked').val();
		var answerInputs = $('input');
		var answer0 = "";
		var answer1 = "";
		var answer2 = "";
		var answer3 = "";
		var answer4 = "";
		var answer5 = "";
		var answer6 = "";
		var answer7 = "";
		var answer8 = "";
		var answer9 = "";
		var answer10 = "";
		for (var i = 0; i < answerInputs.length; i++) {
			var name = $(answerInputs[i]).attr('name');
			if (name) {
				var val = $(answerInputs[i]).val();
				if (name.indexOf('-') != -1) {
					var names = name.split('-');
					var inputName = names[0];
					var index = names[1];
					if (inputName == 'answer') {
						if (index == '0') {
							answer0 = val;
						} else if (index == '1') {
							answer1 = val;
						} else if (index == '2') {
							answer2 = val;
						} else if (index == '3') {
							answer3 = val;
						} else if (index == '4') {
							answer4 = val;
						} else if (index == '5') {
							answer5 = val;
						} else if (index == '6') {
							answer6 = val;
						} else if (index == '7') {
							answer7 = val;
						} else if (index == '8') {
							answer8 = val;
						} else if (index == '9') {
							answer9 = val;
						} else if (index == '10') {
							answer10 = val;
						}

					}
				}
			}
		}
		$.ajax({
			url : '/questionnaire/question/save',
			type : 'post',
			dataType : 'json',
			data : {
				questionnaireId : questionnaireId,
				name : questionName,
				type : type,
				answer0 : answer0,
				answer1 : answer1,
				answer2 : answer2,
				answer3 : answer3,
				answer4 : answer4,
				answer5 : answer5,
				answer6 : answer6,
				answer7 : answer7,
				answer8 : answer8,
				answer9 : answer9,
				answer10 : answer10
			},
			success : function(data, textStatus, xhr) {
				$('#' + Questionnaire.questionFormContainer).empty();
				$('#add-question').show();
			}
		});
	},
	pushQuestionnaire2Weixin:function(){
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		var weixinAccountIds=Check.getCheckedIds($('input[name="weixinAccountId"]'));
		$.ajax({
			url:'/questionnaire/questionnaire/pushQuestionnaire2Weixin',
			type:'post',
			dataType:'json',
			data:{
				accountIds:weixinAccountIds,
				questionnaireIds:checkedIds
			},
			success:function(data,textStatus,xhr){
				var msg=new Msg({msg:'已成功推送到微信.'});
				msg.show();
			}
		});
	}
};

$(document).ready(function() {
	Questionnaire.init();
});
