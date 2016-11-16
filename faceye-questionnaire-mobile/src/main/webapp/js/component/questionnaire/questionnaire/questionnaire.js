/**
 * 说明:Questionnaire js 脚本 作者:@haipenge
 */
var Questionnaire = {
	questionCount : 0,
	questionIndex : 0,
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
		$('#submit-questionnaire').click(function() {
			var msg = new Msg({
				msg : '调查问卷提交成功.如多次提交,只有最后一次提交有效.'
			});
			Answer.doSaveAnswerText();
			msg.show();
		});
		/**
		 * 点击开始测试
		 */
		$('#questionnaire-start-test-btn').click(function() {
			$(this).hide();
//			$('#questionnaire-remark').hide();
			$('#questionnaire-remark').remove();
			Questionnaire.questionCount=$('#question-items').find('.list-group-item').length;
			$('#question-items').show();
			$('#question-items').find('.list-group-item').each(function(index, el) {
				if (index > 0) {
					$(el).hide();
				}
			});
//			 $('#question-items').find('.list-group-item:nth-child(0)').show();
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
	}
};

$(document).ready(function() {
	Questionnaire.init();
});
