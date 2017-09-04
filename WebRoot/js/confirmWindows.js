

///remove_obj()
jQuery.extend({
	createConfirmWindows:function(d){
		this.initData(d);
		this.create_mask();        
        
/*		var html="";
		html+='<div style="padding:0px 15px; background:#ccc; line-height:40px; border-button:1px solid #bbb;"><b>'+data.title+'</b></div>';
		html+='<div style="padding:5px 10px; height:20px;">'+data.label+'</div>';
		html+='<div style="padding:5px 10px; height:'+(data.height-40-45-20)+';"><textarea id="confirm_value" style="width:100%;height:100%;"></textarea></div>';
		html+='<div style="padding:5px; text-align:center; height:35px;"><input type="button" value="确定" /> <input type="button" value="取消" onclick="'+data.cancelFunc+';remove_obj();" /></div>';
	*/	
        
        var content=$('<div>');
        var item_title=$('<div style="padding:0px 15px; background:#ccc; line-height:40px; border-button:1px solid #bbb;"><b>'+this.bindData.title+'</b></div>');
        var item_label=$('<div style="padding:5px 10px; height:20px;">'+this.bindData.label+'</div>');
        var item_textarea=$('<div style="padding:5px 10px; height:'+(this.bindData.height-40-45-20)+';"><textarea id="confirm_value" style="width:100%;height:100%;"></textarea></div>');
		var item_btn_box=$('<div style="padding:5px; text-align:center; height:35px;"></div>');
		var item_btn_ok=$('<input type="button" value="确定" />');
		var item_btn_cancel=$('<input type="button" value="取消"  />');
		item_btn_ok.click(function(){
			if($.bindData.okFunc){
				$.result=$("#msgbox").find("textarea").eq(0).val();
				$.bindData.params.remark=$.result;
				$.bindData.okFunc($.bindData.params);
			}
			$.remove();
		});
		item_btn_ok.appendTo(item_btn_box);
		item_btn_cancel.appendTo(item_btn_box);
		item_title.appendTo(content);
		item_label.appendTo(content);
		item_textarea.appendTo(content);
		item_btn_box.appendTo(content);
		
		this.create_msgbox(this.bindData.width,this.bindData.height,content);
		this.load_func();

	
	},
	initData:function(d){
		$.bindData.title=d.title?d.title:"提示";
		$.bindData.height=d.height<120?120:d.height;
		$.bindData.width=d.width<100?100:d.width;
		$.bindData.label=d.label?d.label:"内容：";
		$.bindData=d;
	},
	bindData:{
		
	},
	result:"",
	result:function(){
		return $('#msgbox textarea').eq(0).val();
	},		
	get_width:function () {
		return ($(window).width() + $(window).scrollLeft());
	},
	get_height:function () {
		return ($(window).height() + $(window).scrollTop());
	},
	get_left:function (w) {
		var bw = $(window).width();
		var bh = $(window).height();
		w = parseFloat(w);
		return (bw / 2 - w / 2 + $(window).scrollLeft());
	},
	get_top:function (h) {
		var bw = $(window).width();
		var bh = $(window).height();
		h = parseFloat(h);
		return ((bh / 2 - h / 2)*0.5 + $(window).scrollTop());
	},
	create_mask:function () {// 创建遮罩层的函数
		$("<div>",{
			"class":"",
			"id":"mask",
			"css":{
				"position":"absolute",
				"opacity":"0.4",
				"background":"black",
				"top":"0px",
				"left":"0px",
				"width":"100%",
				"height":"100%",
				//"width":get_width(),
				//"height":get_height(),
				"z-index":1000
			}
		}).appendTo($("body"));
	},
	create_msgbox:function (w, h, t) {// 创建弹出对话框的函数
		$("<div>",{
			"id":"msgbox",
			"css":{
				"border":"1px solid #666",
				"background":"#fff",
				"position":"absolute",
				"width":w,
				"height":h,
				"overflow":"visible",
				"z-index":1001
			},
			//html:t,
		}).append(t).appendTo($("body"));
		$.re_pos();
	},
	re_mask:function () {
		/*
		 * 更改遮罩层的大小,确保在滚动以及窗口大小改变时还可以覆盖所有的内容
		 */
		$("#mask").css({width:$.get_width(),height:$.get_height()});
	},
	re_pos:function () {
		/*
		 * 更改弹出对话框层的位置,确保在滚动以及窗口大小改变时一直保持在网页的最中间
		 */
		$("#msgbox").css({left:$.get_left($("#msgbox").width()),top:$.get_top($("#msgbox").height())});
	},
	remove:function () {
		/*
		 * 清除遮罩层以及弹出的对话框
		 */	
		$("#mask").remove();
		$("#msgbox").remove();
	},
	re_show:function () {
		/*
		 * 重新显示遮罩层以及弹出窗口元素
		 */
		$.re_pos();
		$.re_mask();
	},
	load_func:function () {
		//$(window).bind("resize",re_show);
		$(window).bind("scroll",$.re_show);		
		/*window.onresize = re_show;
		window.onscroll = re_show;*/
	}
});
