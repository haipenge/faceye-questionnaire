/**
 * 说明:AnswerRecord js 脚本 作者:@haipenge
 */
var AnswerRecord={
  init:function(){
	  /**
		 * 全选，全不选
		 */
	  $('input[name="check-all"]').click(function(){
	    Check.onCheck($('input[name="check-all"]'),$('input[name="check-single"]'));
	  });
	  $('.multi-remove').click(function(){
		  AnswerRecord.multiRemove();
	  });
	  
  },
  /**
	 * 批量删除
	 */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="check-single"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/questionnaire/answerRecord/multiRemove',
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
  }
};

$(document).ready(function(){
	AnswerRecord.init();
});
