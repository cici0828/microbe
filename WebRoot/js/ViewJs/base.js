	function allPrpos(obj) {
		// 用来保存所有的属性名称和值 
		var props = "";
		// 开始遍历 
		for ( var p in obj) { // 方法 
			if (typeof (obj[p]) == " function ") { //obj [ p ]() ; 
			} else { // p 为属性名称，obj[p]为对应属性的值 
				props += p + " = " + obj[p] + " \n ";
			}
		}
		// 最后显示所有的属性 
		alert(props);
	}	

	//弹窗输入
	function dazd_prompt2(params){
		$.messager.defaults = { ok: "确定", cancel: "取消" ,width:"350"};
		params.title=params.title?params.title:"提示";
		params.text=params.text?params.text:"输入信息";
		$.messager.prompt(params.title,params.text, function(r){
			if(r){
				if(params.okCallback){
					params.back_params.dazd_prompt_value=r;
					params.okCallback(params.back_params);
				}
			}
		});
	}
	
	//弹窗输入
	function dazd_prompt(params) {
		params.title=params.title?params.title:"提示";
		params.text=params.text?params.text:"输入信息";
		
		if(params && params.back_params && params.back_params.index>=0){
			var row = getBindRows()[params.back_params.index];
			var key_v=" ["+row.patientname+" | "+(row.age?row.age:"-")+(row.ageunit?row.ageunit:"岁")+" | "+(row.sex?row.sex:"未知")+" | "+row.barcode+"] ";

			params.text=params.text.replace("[]",key_v);
		}else{
			params.text=params.text.replace("[]","");
		}
		
		var da_prompt=$('<div>',{
			id:"dazd-dialog-prompt",
		}).appendTo($("body"));
		
		var warp=$("<div>",{
			"class":"form-group",
			css:{
				padding:"5px 10px",
			},
		}).appendTo(da_prompt);
		
		$("<label>",{
			"for":"dazd-dialog-prompt-value",
			html:params.text,
		}).appendTo(warp);
		
		autosize($("<textarea>",{
			id:"dazd-dialog-prompt-value",
			"class":"form-control",
			"rows":"5",
		}).appendTo(warp));
		
		da_prompt.dialog({
		    title:params.title,
		    width: 400,
		    top:50,
		    zIndex : 19999,
		    height: "auto",
		    closed: false,
		    modal: true,
		    buttons:[{
				text:'确定',
				width:75,
				handler:function(){
					if(params.okCallback){
						params.back_params.dazd_prompt_value=$("#dazd-dialog-prompt-value").val();
						//dazd_confirm("确定进行操作?",null,okCallback,params);
						params.okCallback(params.back_params);
					}
					da_prompt.dialog("destroy");
				}
			},{
				text:'取消',
				width:75,
				handler:function(){
					if(params.cancelCallback){
						params.back_params.dazd_prompt_value=$("#dazd-dialog-prompt-value").val();
						params.cancelCallback(params.back_params);
					}
					da_prompt.dialog("destroy");
				}
			}]
		});
	}
	//弹窗提示
	function dazd_alert(params) {
		$.messager.defaults = { modal:true, ok: "确定", cancel: "取消" ,width:"350", top:"120"};
		params.text=params.text?params.text:"";
		params.title=params.title?params.title:"提示";
		params.ico=params.ico?params.ico:"info";
		
		if(params && params.back_params && params.back_params.index>=0){
			var row = getBindRows()[params.back_params.index];
			var key_v=" ["+row.patientname+" | "+(row.age?row.age:"-")+(row.ageunit?row.ageunit:"岁")+" | "+(row.sex?row.sex:"未知")+" | "+row.barcode+"] ";

			params.text=params.text.replace("[]",key_v);
		}else{
			params.text=params.text.replace("[]","");
		}
		
		$.messager.alert(params.title,params.text);
	}
	
	//dazd_confirm({back_params:{index:0}});
	//弹窗选择
	function dazd_confirm(params) {//text,title,callback
		$.messager.defaults = {top:50, modal:true, ok: "确定", cancel: "取消" ,width:"350"};
		params.text=params.text?params.text:"是否确定?";
		params.title=params.title?params.title:"提示";
		params.ico=params.ico?params.ico:"question";

		$.messager.confirm(params.title,params.text,function(r){
			if(r){
				if(params.callback){
					if(params.back_params){
						params.callback(params.back_params);
					}else{
						params.callback;
					}
				}
			}
		});		
	}
	
	function dazd_load_open(){
		var load_obj=$('<div id="dazdLoad" style="z-index:9999999;"></div>');
		var modal=$('<div>',{"id":"dazd_modal","css":{"z-index":"9999",background:"#ccc",width:"100%",height:"100%","opacity":"0.4","position":"absolute","top":"0px"}});
		modal.appendTo($("body"));
		load_obj.appendTo($("body"));
		load_obj.jqxLoader({autoOpen: true, isModal: false, width: 100, height: 60, imagePosition: 'top' });
/*		load_obj.click(function(){
			dazd_load_close();
		});*/
	}
	function dazd_load_close(){
		//$("#dazdLoad").jqxLoader("close");
		$("#dazdLoad").remove();
		$("#dazd_modal").remove();
	}