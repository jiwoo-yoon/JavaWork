$(document).ready(function(){
	loadPage();
	
	$("#mycate select").on("change", function(){
		var parent = $(this).val()
		var depth = $(this).children(":selected").attr("data-depth");
		depth = (depth*1)+1;
		
		if(parent == 0){
			for(var i = depth; i <= $("#mycate>span").length; i++){
				$('.cate span:nth-child('+ i + ') select').html("");
				$('.cate span:nth-child('+ i + ') select').attr("disabled", true);
			}
		}else{
			loadPage(depth, parent);
		}
	});
});

function loadPage(depth, parent){
 
	$.ajax({
		url : "cate_list.ajax",
		type : "POST",
		data: {
			"depth": depth,
			"parent": parent
		},
		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.count != 0)updateList(data);
			}
		}
	});
	
}


function updateList(jsonObj){
	//result = "<option >====선택하세요====</option>";
	if(jsonObj.status == "OK"){
		var count = jsonObj.count;
		
		var i;
		var items = jsonObj.data; // data 는 배열!
		result = "<option value = '0' data-depth='"+ items[0].depth+"'>====선택하세요====</option>";
		for(i=0; i<count; i++){
			$('.cate span:nth-child(' + items[0].depth + ') select').attr("disabled", false);
			result += "<option value='"+ items[i].uid+"' data-depth='"+ items[i].depth+"'>" + items[i].name + "</option>"
		}
		$('.cate span:nth-child('+ items[0].depth + ') select').html(result);
		//$('.cate span:nth-child('+ (items[0].depth + 1) + ') select').html("");
		//$('.cate span:nth-child('+ (items[0].depth + 1) + ') select').attr("disabled", false);
		var depth = items[0].depth;
		if(depth>0){
			for(var i = depth; i < $("#mycate>span").length; i++){
				$('.cate span:nth-child('+ (i + 1) + ') select').html("");
				$('.cate span:nth-child('+ (i + 1) + ') select').attr("disabled", true);

			}
		}
		return true;

	}else {
		alert(jsonObj.message);
		return false;
	}
	return false;
}














