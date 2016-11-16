/**
 * 说明:Answer js 脚本 作者:@haipenge
 */
var Answer={
  init:function(){
	  /**
		 * 全选，全不选
		 */
	  $('input[name="check-all"]').click(function(){
	    Check.onCheck($('input[name="check-all"]'),$('input[name="check-single"]'));
	  });
	  $('.multi-remove').click(function(){
		  Answer.multiRemove();
	  });
	  
	  $('.reset-answer-support-count').click(function(){
			Answer.resetAnswerSupportCount();
		});
	  
	  $('.remove-answer').click(function(){
		  Answer.remove(this);
		  return false;
	  });
	  
  },
  /**
	 * 批量删除
	 */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="check-single"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/questionnaire/answer/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var type = data.result? '':'warning';
				  var msg=new Msg({msg:data.msg,type:type});
				  if(data.result){
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
					  $('#'+idArray[i]).remove();
				  }
				  }
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	  }
  },
  remove:function(dom){
	 var id=$(dom).parent().parent().attr('id');
	 $.ajax({
		 url:'/questionnaire/answer/remove/'+id,
		 type:'post',
		 dataType:'json',
		 success:function(data,textStatus,xhr){
//			 $('#'+id).remove();
			 $(dom).parent().parent().remove();
		 }
	 });
  },
  resetAnswerSupportCount:function(){
		var questionnaireId = $('input[name="questionnaireId"]').val();
		$.ajax({
			url:'/questionnaire/answer/initSupportCount',
			type:'post',
			dataType:'json',
			data:{
				questionnaireId:questionnaireId
			},
			success:function(data,textStatus,xhr){
				var msg=new Msg({msg:'初始化成功'});
				msg.show();
			}
		});
	}
};

$(document).ready(function(){
	Answer.init();
});
