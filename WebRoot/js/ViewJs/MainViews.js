var updateState=false;
var whereData="";

/*
jQuery.extend({
	loaderBox:function(id,active){
		$("#"+id).css("opacity","0.3");
		switch(active){
		case "open":
			$("#"+id).show();
			break;
		case "close":
			$("#"+id).hide();
			break;
		}
	}
});
*/

jQuery.fn.extend({
	loaderBox:function(active){
		switch(active){
		case "open":
			//$(this).animate({"opacity":"0.3"});
			$(this).css({"opacity":"0.3"});
			$(this).show();
			break;
		case "close":
			//$(this).animate({"opacity":"0"});
			$(this).hide();
			break;
		}
	}
});

$(function(){   
    initInput();
    init();
	$("#jqxLoader").jqxLoader({ isModal: false, width: 100, height: 60, imagePosition: 'top',autoOpen:false});

	/*$('#window').on('close', function (event) { 
	     			$.loaderBox("loaderBox","close");
		$("#jqxLoader").jqxLoader("close");
	});*/
});

//初始页面对象
function init(){
	var winHeight=document.body.clientHeight;
	if(authCode==1){
	    autosize($(".text,textarea"));
	}else{
		$("input,textarea").attr("disabled","disabled");
		$("#actionButtons").hide();
		$("textarea").jqxTextArea({});
	}
	bandClick();
	
    createElements(1000,winHeight);
 	$('#window').jqxWindow('close');
    
    //左右框架
	$('#mainSplitter').jqxSplitter({ width: "100%", height: winHeight, panels: [{ size: 180 }] });
	//左栏
    $('#jqxTree').jqxTree({ height: '100%', width: "100%"});
    $('#jqxTree').jqxTree('expandAll');
    $('#jqxTree').on('select', function (event) {
    	whereData=$(event.args.element).attr("whereData");
       gridViewBand(whereData);
    });
    // Create jqxTabs.
    $('#jqxTabs').jqxTabs({animationType:"fade",selectionTracker:true, width: '100%', height: winHeight-(authCode==1?91:40), position: 'top'});
    bandTotalForUrl();//绑定统计
    $("#jqxTree").jqxTree('selectItem', $("#jqxTree10_11_17"));//设定默认选择
    backgroundBand();//绑定背景
    
    //双击数据行
    $("#jqxgrid").on('rowdoubleclick', function (event) {
     	$("#jqxLoader").jqxLoader("open");
    	//$.loaderBox("loaderBox","open");
    	$("#loaderBox").loaderBox("open");
    	var cn_success=false;
    	var en_success=false;
     	var num=event.args.rowindex;
     	var arr=event.args.row.bounddata;
     	bandWindowEn();
 		bandWindowCn();
     	resetButtonState(arr.state);
     	$("input[name='barcode']").val(arr.barcode);
     	$("input[name='ebid']").val(arr.ebid);
     	$("input[name='state']").val(arr.state);
     	$("input[name='age']").val(arr.age);
     	$("input[name='picnum']").val(arr.picnum);
     	//$("input[name='bid']").val(arr.bid);
 
     	if(arr.ebid>0){
	     	//获取绑定英文信息
	     	$.ajax({
	     		url:"cslt/getAuditView.action",
	     		data:{EBID:$(":input[name='ebid']").val()},
	    		type: "get",
	    		dataType: "json",
	    		success:function(data){
	    			bandWindowEn(data);
	         		en_success=true;
	     		},
	     		error:function(data){
	     			alert("英文数据获取失败！");
	     		},
	     		complete:function(){
	         		if(cn_success&&en_success){
		                $('#window').jqxWindow('focus');
		             	$('#window').jqxWindow('open');
	         		}
	     			$("#jqxLoader").jqxLoader("close");	 
	     			//$.loaderBox("loaderBox","close");
	     			$("#loaderBox").loaderBox("close");
	     		}
	     	});
     	}else{
     		en_success=true;     		
     	}
     	
     	//获取绑定中文信息
     	$.ajax({
     		url:"cslt/getAuditCnView.action",
     		data:{BARCODE:$(":input[name='barcode']").val()},
    		type: "get",
    		dataType: "json",
    		success:function(data){
     			bandWindowCn(data);
         		$("input[name='bid']").val(data.bid);
         		cn_success=true;
     		},
     		error:function(data){
     			alert("中文数据获取失败！");
     		},
     		complete:function(){
         		if(cn_success&&en_success){
	                $('#window').jqxWindow('focus');
	             	$('#window').jqxWindow('open');
         		}
     			$("#jqxLoader").jqxLoader("close");	 
     			//$.loaderBox("loaderBox","close");
     			$("#loaderBox").loaderBox("close");
     		}
     	});	
     	bandLogBy($(":input[name='barcode']").val());//绑定日志     
     	bandPics(arr.barcode);
    });
}
//初始化输入框
function initInput(){
  
	$('#saveButton').jqxButton({width: '75px' });
	$('#auditButton').jqxButton({width: '75px' });
	$('#jhhButton').jqxButton({width: '75px' });
	$('#releaseButton').jqxButton({width: '75px' });
	
	$('input[name="patientname"]').jqxInput({width: '190px' });
	//$('input[name="sex"]').jqxInput({width: '150px' });
	$('input[name="sendstuff"]').jqxInput({width: '370px' });


	//$("textarea").jqxTextArea({});
  //autosize($(".text,textarea"));   

/*	$('textarea[name="CLIDATA"]').jqxTextArea({});
	$('textarea[name="CLIHISTORY"]').jqxTextArea({});
	$('textarea[name="FIRST_OPTION"]').jqxTextArea({  });
	$('textarea[name="GROSS"]').jqxTextArea({});
	$('textarea[name="REMARK"]').jqxTextArea({});
	$('textarea[name="CLIRESULT_EN"]').jqxTextArea({});
	$('textarea[name="LETTER"]').jqxTextArea({  });
	*/
}

//创建winodw
function createElements(width,height) {
    $('#window').jqxWindow({ 
    	isModal: true, 
    	//position: 'center, right',
        resizable: false,
        width: width-0<0?0:width,
        height:height-0<0?0:height,
        minWidth: 1000,
        minHeight: 400,
        maxWidth:1200,
        maxHeight:800
    });
    $('#window').jqxWindow('focus');
}
//数据绑定
function gridViewBand(stateStr){
	var url = "cslt/getTranslateList.action?state="+stateStr;
    // prepare the data
    var source =
    {
        datatype: "json",
        datafields: [
            { name: 'ebid', type: 'string' },
            { name: 'barcode', type: 'string' },
            { name: 'state_msg', type: 'string' },
            { name: 'patientname', type: 'int' },
            { name: 'sex', type: 'string' },
            { name: 'age', type: 'string' },
            { name: 'samplefrom', type: 'string' },
            { name: 'sampleto', type: 'string' },
            { name: 'state', type: 'string' },
            { name: 'state_msg', type: 'string' },
            { name: 'senddate', type: 'date' },
            { name: 'commitdate', type: 'date' ,sortable:true},
            { name: 'remark', type: 'string' },
            { name: 'picnum', type: 'int' }
        ],
        id: 'id',
        url: url,
        pager: function (pagenum, pagesize, oldpagenum) {
            // callback called when a page or page size is changed.
        }
    };
    var dataAdapter = new $.jqx.dataAdapter(source);//数据对象
    //数据绑定
    $("#jqxgrid").jqxGrid(
    {
        width: "100%",
        height: "100%",
        source: dataAdapter,
        //columnsresize: true,
        //filterable: true,
        sortable:true,
        pageable: true,
        pagesize:20,
        localization: getLocalization('cn'),
        altrows: true,
        columns: [
          { text: '', datafield: 'ebid', width: 250,hidden: true},
          { text: '状态说明', datafield: 'state_msg', width: 100},
          { text: '条码号', datafield: 'barcode', width: 100 },
          { text: '姓名', datafield: 'patientname', width: 90 },
          { text: '性别', datafield: 'sex', width: 45 },
          { text: '年龄', datafield: 'age', width: 40 },
          { text: '切片数量', datafield: 'picnum', width: 70 },
          { text: '送检中心', datafield: 'sampleto', width: 100 },
          { text: '状态值', datafield: 'state', width: 60,hidden: true },
          { text: '送检时间', datafield: 'senddate',filtertype: 'date', width: 160 ,cellsformat:'yyyy-MM-dd HH:mm:ss'},
          { text: '提交时间', datafield: 'commitdate',filtertype: 'date', width: 160 ,cellsformat:'yyyy-MM-dd HH:mm:ss',hidden: true},
          { text: '送检医院', datafield: 'samplefrom', minwidth: 140 },
          { text: '备注', datafield: 'remark', minwidth: 160,hidden: true  }
      ]
    });
}

//绑定日志数据
function bandLogBy(barcode){
	var url="cslt/getCsltLogList.action?barcode="+barcode;	
    var source =
    {
        datatype: "json",
        datafields: [
            { name: 'logid', type: 'int' },
            { name: 'barcode', type: 'string' },
            { name: 'op_userid', type: 'int' },
            { name: 'op_username', type: 'string' },
            { name: 'op_state_mean', type: 'string' },
            { name: 'op_bz', type: 'string' },
            { name: 'op_date', type: 'date' ,sortable:true}
        ],
        id: 'id',
        url: url,
        pager: function (pagenum, pagesize, oldpagenum) {
            // callback called when a page or page size is changed.
        }
    };
    var dataAdapter = new $.jqx.dataAdapter(source);//数据对象
    //数据绑定
    $("#logGrid").jqxGrid(
    {
        width: "99%",
        height: "100%",
        source: dataAdapter,
        columnsresize: true,
        //sortable:true,
        pageable: true,
        pagesize:20,
        localization: getLocalization('cn'),
        altrows: true,
        columns: [
          { text: 'ID', datafield: 'logid', width: 40},
          { text: '条码号', datafield: 'barcode', width: 100 },
          { text: '操作用户ID', datafield: 'op_userid', width: 100 },
          { text: '操作用户名', datafield: 'op_username', width: 90 },
          { text: '操作', datafield: 'op_state_mean', width: 100 },
          { text: '说明', datafield: 'op_bz', minwidth: 160 },
          { text: '操作时间', datafield: 'op_date',filtertype: 'date', width: 160 ,cellsformat:'yyyy-MM-dd HH:mm:ss'}
      ]
    });    
}

//绑定切片信息
function bandPics(barcode){
	//barcode="144718164";
	//var url="cslt/getCsltPicsByBarcode.action?barcode="+barcode;	
	
	 $.ajax({
 		url:"cslt/getCsltPicsByBarcode.action",
 		   type: "GET",
 		   dataType: "json",
 		   data:{barcode:barcode},
 		   success:function(data){
 			  var container="";
 		       for(var i=0;i<data.length;i++){
 					container += "<div><input name='pic_id' type='hidden' value='"+data[i].id+"' />";
 					container+="<div style='margin:5px;padding:5px; width:180px; height:140px; float:left;'><img style='border:1px solid #ccc;' src='http://oa.dazd.cn/others/201406/G/1768674612' width='180' height='140' /></div>";
 					container+="<div style='margin:5px;padding:5px; width:360px; height:140px; float:left; background:#eee;'>"+data[i].describe_en+"</div>";
 					container+="<div style='margin:5px;padding:5px; width:370px; height:140px; float:left;'>翻译(中文)：<br /><textarea style='width:100%; height:120px;' name='pic_describe'>"+data[i].describe+"</textarea></div>";
 					//container+="";
 					container += "</div>";
 		       }
 		       $("#pics").html(container);
 		   },
 		   error:function(data){
 			   
 		   }
 	});	
}

//统计绑定
function bandTotalForUrl(totalUrl){
	 totalUrl=totalUrl?totalUrl:"cslt/getTranslateTotal.action";
	 $.get(totalUrl,function(data){
		 bandTotal(data);
	 });
}
function bandTotal(data){
	 var state10=0;
	 var state11=0;
	 var state12=0;
	 var state13=0;
	 var state14=0;
	 var state15=0;
	 var state16=0;
	 var state17=0;
	 var state18=0;
	 var state19=0;
	 var state21=0;
	 try{
		 for(var i=0;i<data.length;i++){		
			 var obj=data[i];
			 switch(obj.state){
			 case "10":
			 case 10:
				 state10=obj.count;
				 break;
			 case "11":
			 case 11:
				 state11=obj.count;
				 break;
			 case "12":
			 case 12:
				 state12=obj.count;
				 break;
			 case "13":
			 case 13:
				 state13=obj.count;
				 break;
			 case "14":
			 case 14:
				 state14=obj.count;
				 break;
			 case "15":
			 case 15:
				 state15=obj.count;
				 break;
			 case "16":
			 case 16:
				 state16=obj.count;
				 break;
			 case "17":
			 case 17:
				 state17=obj.count;
				 break;
			 case "18":
			 case 18:
				 state18=obj.count;
				 break;
			 case "19":
			 case 19:
				 state19=obj.count;
				 break;
			 case "21":
			 case 21:
				 state21=obj.count;
				 break;
			 }			 
		 }
	 }catch (e){}

	 $("#jqxTree10_11_17 .total_num").html(state11-0+state17+state10);
	 $("#jqxTree12_13 .total_num").html(state12-0+state13);
	 $("#jqxTree15 .total_num").html(state15);
	 $("#jqxTree16_18_19_21 .total_num").html(state16-0+state18+state19+state21);

	 $("#jqxTreeItem10 .total_sub_num").html(state10);
	 $("#jqxTreeItem11 .total_sub_num").html(state11);
	 $("#jqxTreeItem12 .total_sub_num").html(state12);
	 $("#jqxTreeItem13 .total_sub_num").html(state13);
	 $("#jqxTreeItem14 .total_sub_num").html(state14);
	 $("#jqxTreeItem15 .total_sub_num").html(state15);
	 $("#jqxTreeItem16 .total_sub_num").html(state16);
	 $("#jqxTreeItem17 .total_sub_num").html(state17);
	 $("#jqxTreeItem18 .total_sub_num").html(state18);
	 $("#jqxTreeItem19 .total_sub_num").html(state19);
	 $("#jqxTreeItem21 .total_sub_num").html(state21);	 
}

//更新数据
function refreshBandData(){
	bandTotalForUrl();//刷新统计数据    
	gridViewBand(whereData);//刷新数据列表
}

//绑定中英文详细数据
function bandWindowCn(objCn ){	
	$("span[name='patientname'").text(objCn==null||objCn.patientname==null?"":objCn.patientname);
	$("span[name='sex'").text(objCn==null||objCn.sex==null?"":objCn.sex);
	$("span[name='sendstuff'").text(objCn==null||objCn.sendstuff==null?"":objCn.sendstuff);
	$("textarea[name='clidata_cn'").val(objCn==null||objCn.clidata==null?"":objCn.clidata);
	$("textarea[name='clihistory_cn'").val(objCn==null||objCn.clihistory==null?"":objCn.clihistory);
	$("textarea[name='first_option_cn'").val(objCn==null||objCn.first_option==null?"":objCn.first_option);
	$("textarea[name='gross_cn'").val(objCn==null||objCn.gross==null?"":objCn.gross);
	$("textarea[name='letter_cn']").val(objCn==null||objCn.letter==null?"":objCn.letter);
	//$("textarea[name='remark_cn'").val(objCn==null||objCn.remark==null?"":objCn.remark);
	
}
function bandWindowEn(objEn){
	
	$("input[name='patientname']").val(objEn==null||objEn.patientname==null?"":objEn.patientname);
	$("select[name='sex']").val(objEn==null||objEn.sex==null?"":objEn.sex);
	$("input[name='sendstuff']").val(objEn==null||objEn.sendstuff==null?"":objEn.sendstuff);
	$("textarea[name='clidata']").val(objEn==null||objEn.clidata==null?"":objEn.clidata);
	$("textarea[name='clihistory']").val(objEn==null||objEn.clihistory==null?"":objEn.clihistory);
	$("textarea[name='first_option']").val(objEn==null||objEn.first_option==null?"":objEn.first_option);
	$("textarea[name='gross']").val(objEn==null||objEn.gross==null?"":objEn.gross);
	$("textarea[name='letter'").val(objEn==null||objEn.letter==null?"":objEn.letter);
	$("textarea[name='remark']").val(objEn==null||objEn.remark==null?"":objEn.remark);
	$("textarea[name='cliresult_cn']").val(objEn==null||objEn.cliresult_cn==null?"":objEn.cliresult_cn);
	$("textarea[name='cliresult_en'").val(objEn==null||objEn.cliresult_en==null?"":objEn.cliresult_en);
	
}
//按钮显示
function resetButtonState(state){
	//保存按钮	
	if(state=="15"||state=="21"||state=="12"||state=="18"){
		$("#saveButton").attr("disabled","disabled");
		$("#saveButton").hide();
	}else{
		$("#saveButton").removeAttr("disabled");
		$("#saveButton").show();
	}	
	//审核按钮
	if(state=="10"||state=="11"||state=="16"||state=="17"){
		$("#auditButton").removeAttr("disabled");
		$("#auditButton").show();
	}
	else{
		$("#auditButton").attr("disabled","disabled");
		$("#auditButton").hide();
	}	
	//提交JHH按钮
	if(state=="13"||state=="17"){
		$("#jhhButton").removeAttr("disabled");
		$("#jhhButton").show();
	}
	else{
		$("#jhhButton").attr("disabled","disabled");
		$("#jhhButton").hide();
	}	
	//发布
	if(state=="19"){
		$("#releaseButton").removeAttr("disabled");
		$("#releaseButton").show();
	}
	else{
		$("#releaseButton").attr("disabled","disabled");
		$("#releaseButton").hide();
	}
	//lab显示
	if(state=="16"||state=="18"||state=="19"||state=="21"){
	     $('#jqxTabs').jqxTabs('select', 2); 	     
	     //$("#jqxTabs ul li").eq(1).css("display","block");
	     $("#jqxTabs ul li").eq(2).css("display","block");
	     $("#jqxTabs ul li").eq(3).css("display","block");
	     
//	     $('#jqxTabs').jqxTabs('enableAt', 1);
//	     $('#jqxTabs').jqxTabs('enableAt', 2);
//	     $('#jqxTabs').jqxTabs('enableAt', 3);
	     $("textarea.text,select,input:text").attr("disabled","disabled");
	}else{
	     $('#jqxTabs').jqxTabs('select', 0); 
	     //$("#jqxTabs ul li").eq(1).css("display","none");
	     $("#jqxTabs ul li").eq(2).css("display","none");
	     $("#jqxTabs ul li").eq(3).css("display","none");
//	     $('#jqxTabs').jqxTabs('disableAt', 1);		
//	     $('#jqxTabs').jqxTabs('disableAt', 2);		
//	     $('#jqxTabs').jqxTabs('disableAt', 3);

	 	if(state=="15"){
		     $("textarea.text,select,input:text").attr("disabled","disabled");	 
	 	}else{
	     $("textarea.text,select,input:text").removeAttr("disabled");
	 	}
	}	
	
	//发布后所有数据不可操作
	if(state=="21"){
		$("textarea.textarea").attr("disabled","disabled");
	}else{
		if(state=="15"||state=="16"||state=="18"||state=="19"||state=="21"){
			$("textarea[name='letter'").attr("disabled","disabled");
			$("textarea[name='letter_cn'").attr("disabled","disabled");			
		}else{
			$("textarea.textarea").removeAttr("disabled");			
		}
	}
	
	$("textarea[name='cliresult_en'").attr("disabled","disabled");//诊断结果
}

//按钮绑定
function bandClick(){
	//单击绑定
	$("#saveButton").click(function(){	
		state=$("input[name='state']").val();
		setCsltInfo(state);
	});
	
	//提交审核
	$("#auditButton").click(function(){
		ebid=$("input[name='ebid']").val();
		state=$("input[name='state']").val();
		if(state=="10"||state=="11"||state=="16"||state=="17"){
			state=state=="11"||state=="10"||state=="17"?"12":"18";
		}else{
			alert("状态异常!");
			//不正常的状态
			return;
		}
		setCsltInfo(state,true);
	});
	//提交JHH
	$("#jhhButton").click(function(){
		picnum=$("input[name='picnum']").val();
		ebid=$("input[name='ebid']").val();
		state=$("input[name='state']").val();
		if(picnum=="0"){
			alert("没有切片，不能提交给专家！");
			return;
		}
		if(state=="13"||state=="17"){
			state="15";
		}else{
			alert("状态异常!");
			//不正常的状态
			return;
		}
		setCsltInfo(state,true);
	});
	//发布报告
	$("#releaseButton").click(function(){
		state=$("input[name='state']").val();
		if(state=="19"){
			state="21";
		}else{
			alert("状态异常!");
			//不正常的状态
			return;
		}
		setCsltInfo(state,true);
		
		/*
		$.ajax({url:"cslt/setCsltInfo.action",
			type: "POST",
			dataType: "json",
			data:{state:state,ebid:ebid,},
			success:function(){
	    		refreshBandData();
	    		$('#window').jqxWindow('close');
			}
		});
		*/
	});	
}


//绑定背景
function backgroundBand(){
	$("input,.text").focus(function(){
		//$(this).parents(".list").find(".li_l,.li_r").addClass("show");{
		$(this).parents(".list").addClass("show");
		$(this).parents(".list").find(".li_r").css("background","none");
	});
	$("input,.text").blur(function(){
		//$(this).parents(".list").find(".li_l,.li_r").removeClass("show");
		$(this).parents(".list").removeClass("show");
		$(this).parents(".list").find(".li_r").css("background","");
	});
}


//保存数据
function setCsltInfo(state,closeWindow){
	var success_num=0;
	ebid=$("input[name='ebid']").val();
	bid=$("input[name='bid']").val();
	barcode=$("input[name='barcode']").val();
	age=$("input[name='age']").val();
	sex=$("select[name='sex']").val();
	patientname=$('input[name="patientname"]').val();
	sendstuff=$('input[name="sendstuff"]').val();
	clidata=$('textarea[name="clidata"]').val();
	clidata_cn=$('textarea[name="clidata_cn"]').val();
	clihistory=$('textarea[name="clihistory"]').val();
	clihistory_cn=$('textarea[name="clihistory_cn"]').val();
	first_option=$('textarea[name="first_option"]').val();
	first_option_cn=$('textarea[name="first_option_cn"]').val();
	gross=$('textarea[name="gross"]').val();
	gross_cn=$('textarea[name="gross_cn"]').val();
	letter=$("textarea[name='letter'").val();
	letter_cn=$("textarea[name='letter_cn'").val();
	remark=$('textarea[name="remark"]').val();
	//remark_cn=$('textarea[name="remark_cn"]').val();
	cliresult_cn=$('textarea[name="cliresult_cn"]').val();
	cliresult_en=$('textarea[name="cliresult_en"]').val();
		
	//alert($("#form1").serialize());	
   $.ajax({
	   	url:"cslt/setCsltInfo.action",
        type: "POST",
        dataType: "json",
        data:{
    		ebid:ebid
    		,bid:bid
    		,barcode:barcode
    		,age:age
    		,sex:sex
    		,patientname:patientname
    		,sendstuff:sendstuff
    		,state:state
    		,clidata:clidata
    		,clihistory:clihistory
    		,first_option:first_option
    		,gross:gross
    		,letter:letter
    		,remark:remark
    		,cliresult_cn:cliresult_cn
    		,cliresult_en:cliresult_en
        },
        success:function(data){
        	success_num++;
    		if(ebid==""||ebid=="0"){
    			//ebid=data.vo.ebid;
    			$("input[name='ebid']").val(data.vo.ebid);
    			$("input[name='state']").val(data.vo.state);
    		}
        	if(success_num>=2){
        		refreshBandData();
        		//$('#window').jqxWindow('close');
        		alert("操作成功！");
	    		if(closeWindow){$('#window').jqxWindow('close');}
        	}
        },
        error:function(data){
        	//alert(data);
        }
   });
   $.ajax({
		url:"cslt/setCsltBaseinfo.action",
		   type: "POST",
		   dataType: "json",
		   data:{
			ebid:ebid
			,bid:bid
			,barcode:barcode
			,age:age
			,sex:sex
			,patientname:patientname
			,sendstuff:sendstuff
			,state:state
			,clidata:clidata_cn
			,clihistory:clihistory_cn
			,first_option:first_option_cn
			,gross:gross_cn
			,letter:letter_cn
			//,remark:remark_cn
		   },
		   success:function(data){
		       	success_num++;
		    	if(success_num>=2){
		    		refreshBandData();
		    		//$('#window').jqxWindow('close');			    		
		    		//BID 未使用使用
	        		alert("操作成功！");
		    		if(closeWindow){$('#window').jqxWindow('close');}
		    	}
		   },
		   error:function(data){
			   //alert(data);
		   }
	});   
   setPics();
}

function setPics(){
	 var dataArr=new Array();
	 if($("input[name='pic_id']").length>0){
	   	 $("input[name='pic_id']").each(function(i){
	   		dataArr[i]="{id:'"+$(this).val()+"',describe:'"+$("textarea[name='pic_describe']").eq(i).val()+"'}";
	   	 });
	     $.ajax({
	 		url:"cslt/setCsltPics.action",
	 		   type: "POST",
	 		   dataType: "json",
	 		   data:{json:dataArr.join('|:|')},//分割符"|:|"
	 		   success:function(){
	 		       //alert("成功！");
	 		   },
	 		   error:function(data){
	 			   
	 		   }
	 	});
	}
}

