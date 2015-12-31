function checkall(val){
	if(val.checked == true){
		$("input[id^='checkall_']").attr("checked","true");
	}else{
		$("input[id^='checkall_']").attr("checked","");
	}
}

function delinfo(formAction){
	batchOper("确定删除吗?",formAction);
}

function savesort(formAction){
	batchOper("确定排序吗?",formAction);
}

function batchOper(val,formAction){
	var checkall = $("input[id^='checkall_']");
	var cid = 0;
	for(var i=0;i<checkall.length;i++){
		if(checkall[i].checked){
			cid += 1;
		}
	}
	if(cid == 0){
		alert("请至少选择一条记录!");
	}else{
		if(confirm(val)){
			$("#indexForm").attr("action",formAction);
			$("#indexForm").submit();
		}
	}
}

function linktourl(val){
	var url = "";
	var para = "";
	var wpos = val.indexOf("?");
	if(wpos > 0){
		url = val.substring(0,wpos);
		para = val.substring(wpos+1,val.length);
	}else{
		url = val;
	}
	if(para != ""){
		var parastr = para.split("&");
		for(var i=0;i<parastr.length;i++){
			if(parastr[i].indexOf("=") > -1){
				var onepara = parastr[i].split("=");
				if(onepara.length == 2){
					var $hidetxt = $("<input name='"+onepara[0]+"' value='"+onepara[1]+"' type='hidden'/>");
					$("#commonForm").append($hidetxt);
				}
			}
		}
	}
	$("#commonForm").attr("action",url);
	$("#commonForm").submit();
}

//搜索让分页回到第一页
function listsearch(){
	var target_obj = $("#indexForm input[name='pages_search']");
	 if (target_obj.length > 0) {
	 	target_obj.val("1");
	 }
	 $("#indexForm").submit();
}

function sAlert(str){
    var msgw,msgh,bordercolor;
    msgw=200;//提示窗口的宽度
    msgh=80;//提示窗口的高度
    titleheight=25 //提示窗口标题高度
    bordercolor="#2887B5";//提示窗口的边框颜色
    titlecolor="#99CCFF";//提示窗口的标题颜色
    var sWidth,sHeight;
    sWidth=document.body.offsetWidth;
    sHeight=screen.height;
    var bgObj=document.createElement("div");
    bgObj.setAttribute('id','bgDiv');
    bgObj.style.position="absolute";
    bgObj.style.top="0";
    bgObj.style.background="#777";
    bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
    bgObj.style.opacity="0.6";
    bgObj.style.left="0";
    bgObj.style.width=sWidth + "px";
    bgObj.style.height="600px";
    bgObj.style.zIndex = "10000";
    document.body.appendChild(bgObj);
    var msgObj=document.createElement("div")
    msgObj.setAttribute("id","msgDiv");
    msgObj.setAttribute("align","center");
    msgObj.style.background="white";
    msgObj.style.border="1px solid " + bordercolor;
    msgObj.style.position = "absolute";
    msgObj.style.left = "50%";
    msgObj.style.top = "50%";
    msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
    msgObj.style.marginLeft = "-100px" ;
    msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
    msgObj.style.width = msgw + "px";
    msgObj.style.height =msgh + "px";
    msgObj.style.textAlign = "center";
    msgObj.style.lineHeight ="25px";
    msgObj.style.zIndex = "10001";
    var title=document.createElement("h4");
    title.setAttribute("id","msgTitle");
    title.setAttribute("align","left");
    title.style.margin="0";
    title.style.padding="3px";
    title.style.background=bordercolor;
    title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
    title.style.opacity="0.75";
    title.style.border="1px solid " + bordercolor;
    title.style.height="18px";
    title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif";
    title.style.color="white";
    title.style.cursor="pointer";
    title.innerHTML="系统操作提示";
    document.body.appendChild(msgObj);
    document.getElementById("msgDiv").appendChild(title);
    var txt=document.createElement("p");
    txt.style.margin="1em 0"
    txt.setAttribute("id","msgTxt");
    txt.innerHTML=str;
    document.getElementById("msgDiv").appendChild(txt);
    $("#msgDiv").animate({opacity:"0"}, 2000);
    $("#bgDiv").fadeOut(2000);
    setTimeout("$('#msgDiv').remove();$('#bgDiv').remove();",2000);
}
