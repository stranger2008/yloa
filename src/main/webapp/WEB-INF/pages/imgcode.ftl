<img src="${ctx}imagecode.action" id="Verify"  style="cursor:pointer;vertical-align:middle;" title="看不清，换一张"/> 
<script>
	$(function(){
	//点击图片更换验证码  
    	$("#Verify").click(function(){
    		$(this).attr("src","${ctx}imagecode.action?timestamp="+new Date().getTime());
    	});
    });
</script>