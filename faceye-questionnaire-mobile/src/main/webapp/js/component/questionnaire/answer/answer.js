/**
 * 说明:Answer js 脚本 作者:@haipenge
 */
var Answer = {
	statResult : 0,
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});

		$('.multi-remove').click(function() {
			Answer.multiRemove();
		});

		$('.on-check').click(function() {
			Answer.doSaveAnswer(this);
		});
		$('#btn-share').click(function() {
			Answer.onBtnShareClick();
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/questionnaire/answer/multiRemove',
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
	 * 计算后执行的函数
	 */
	afterExecResult : function(data) {
//		$('#answer-ad').hide();
		$('#msg').hide();
		// Answer.statResult = Tools.random(80, 100);
		// $('#stat-result').append('<font color="red">' + Answer.statResult + '%</font>!');
		$('#answer-stat-result').show();
	},
	wait : function() {

	},
	/**
	 * 取得随机分析的计算结果
	 * 
	 * @param questionId
	 */
	getRandAnswerStatResult : function(questionnaireId) {
		$.ajax({
			url : '/questionnaire/answerStat/home',
			type : 'post',
			dataType : 'json',
			data : {
				questionnaireId : questionnaireId
			},
			success : function(data, textStatus, xhr) {
				var html = '';
				if (data && data.length > 0) {
					var stat = data[Tools.random(0, data.length)];
					if (stat) {
						if (stat.title && stat.title != '') {
							html += '<h5 class="text-success">' + stat.title + '</h5>';
						}
						if (stat.stitle && stat.stile != '') {
							html += '<p class="text-success" id="stat-result-stitle">' + stat.stitle + '</p>';
						}
						if (stat.remark && stat.remark != '') {
							html += '<p class="text-muted">' + stat.remark + '</p>';
						}
					}
				}
				//html=html.replace(' ','');
//				alert("["+html+"]");
				if (html == '') {
					html = '宝宝不哭,宝宝对不起,计算结果被外星人带走了...';
				}
				$('#stat-result').empty().append(html);
			}
		});
	},
	/**
	 * 保存问卷
	 */
	doSaveAnswer : function(dom) {
		$(dom).parent().css({
			'background-color' : 'red'
		});
		if ($('#question-items').find('.list-group-item').length > Questionnaire.questionIndex + 1) {
			window.setTimeout(Answer.afterAnswerSelect, 500);
		} else {
			Answer.afterAnswerSelect(dom);
		}
		// Answer.afterAnswerSelect(dom);
	},
	afterAnswerSelect : function(dom) {
		var questionnaireId = $('input[name="questionnaireId"]').val();
		var questionId = $(dom).parent().parent().attr('id');
		var isChecked = $(dom).is(':checked');
		var type = $(dom).attr('type');
		var answerId = $(dom).val();
		// alert('type is:' + type + ',is checked:' + isChecked + ',questionnaire id:' + questionnaireId+',answer id
		// is:'+answerId+',question id is:'+questionId);
		if (type == 'radio') {

		} else {

		}
		// alert('index:'+Questionnaire.questionIndex+",count:"+Questionnaire.questionCount);
		var index = Questionnaire.questionIndex + 1;
		$('#question-items').find('.list-group-item:nth-child(' + index + ')').hide();
		// alert('index:'+Questionnaire.questionIndex+",count:"+Questionnaire.questionCount);
		if (Questionnaire.questionIndex < Questionnaire.questionCount - 1) {
			Questionnaire.questionIndex += 1;
			index += 1;
			$('#question-items').find('.list-group-item:nth-child(' + index + ')').show();
		} else {
			$('#msg').show();
			$('#msg').append('正在努力计算测试结果,请耐心等待...');
			$('#answer-ad').show();
			Answer.getRandAnswerStatResult(questionnaireId);
			window.setTimeout(Answer.afterExecResult, 3000);
		}
		// $.ajax({
		// url : '/questionnaire/answer/save',
		// type : 'post',
		// dataType : 'json',
		// data : {
		// questionnaireId : questionnaireId,
		// questionId : questionId,
		// answerId : answerId,
		// isChecked : isChecked,
		// type : type
		// },
		// success : function(data, textStatus, xhr) {
		//
		// }
		// });
	},
	/**
	 * 保存文本回复
	 */
	doSaveAnswerText : function() {
		var textarea = $('textarea');
		$.each(textarea, function(i, record) {
			var name = $(record).attr('name');
			var val = $(record).val();
			// alert('name:'+name+",val:"+val);
			$.ajax({
				url : '/questionnaire/answer/saveAnswerText',
				type : 'post',
				dataType : 'json',
				data : {
					name : name,
					val : val
				},
				success : function(data, textStatus, xhr) {
					Weixin.share(data);
				}
			});
		});
	},
	/**
	 * 当点击分享按钮时
	 */
	onBtnShareClick : function() {
		var url = location.href.split('#')[0];
		var id = $('input[name="questionnaireId"]').val();
		var accountId = $('input[name="accountId"]').val();
		$
				.ajax({
					url : '/questionnaire/questionnaire/getWeixinShareRequest',
					type : 'post',
					dataType : 'json',
					data : {
						url : url,
						id : id,
						accountId : accountId,
						statResult : Answer.statResut
					},
					success : function(data, textStatus, xhr) {
						Weixin.share(data);
						var body = '';
						body += '<div class="content"><img src="/images/right.png" style="margin-right:5px;float:right;"></div>'
						body += '<div class="content text-center"><p class="text-center" style="margin-top:150px;font-weight:bold;font-size:16px;">点击"..." 发送给朋友 或 分享到朋友圈</p>';
						body += '<p></p>';
						body += '<p></p>';
						body += '<p></p>';
						body += '<p></p>';
						body += '<p></p>';
						body += '</div>';
						body += '<div class="modal-footer">';
						body += '<p><button type="button" class="close pull-right" data-dismiss="modal" aria-hidden="true">&times;</button></p>';
						body += '</div>'
						var modal = new Modal({
							body : body,
							footer : false,
							header : false
						});
						modal.show();
					}

				});
	}
};
$(document).ready(function() {
	Answer.init();
//	Tools.copy('#copy');
});
