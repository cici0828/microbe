
var EBID=0;
var BARCODE=0;
$(document).ready(function () {
    var winWidth=document.body.clientWidth-100;
    var winHeight=document.body.clientHeight-60;
    bandData();//绑定数据
     //双击数据行
    $("#jqxgrid").on('rowdoubleclick', function (event) {
     	var num=event.args.rowindex;
     	var arr=event.args.row.bounddata;
     	EBID=arr.ebid;
     	BARCODE=arr.barcode;
     	$("input[name='state']").val(arr.state);
     	     	
     	//获取绑定中文信息
     	$.get("cslt/getAuditCnView.action",{BARCODE:BARCODE},function(data){
     		if(data!=null){
	     		bandWindowCn(data);
     		}
     	});
     	
     	//获取绑定英文信息
     	$.get("cslt/getAuditView.action",{EBID:EBID},function(data){
     		if(data!=null){
     			if(data.state=="18"||data.state==18){  				
//				     $('#jqxTabs').jqxTabs('enableAt', 2);
//				     $('#jqxTabs').jqxTabs('enableAt', 3);
     				$('#jqxTabs ul li').eq(2).css("disable","block");
     				$('#jqxTabs ul li').eq(3).css("disable","block");   
     			     $('#jqxTabs').jqxTabs('select', 2); 	
				}else{
//				     $('#jqxTabs').jqxTabs('disableAt', 2);
//				     $('#jqxTabs').jqxTabs('disableAt', 3);
     				$('#jqxTabs ul li').eq(2).css("display","none");
     				$('#jqxTabs ul li').eq(3).css("display","none");    
     			     $('#jqxTabs').jqxTabs('select', 0); 	  
				}
	     		bandWindowEn(data);
     		}
     	});
     	bandPics(BARCODE);
        createElements(1000,winHeight);     
     	$('#window').jqxWindow('open');
    });

    //openWindows处理
    //addEventListeners();
    //$("#jqxWidget_windows").css('visibility', 'visible');
    $('#window').hide();

    // Create jqxTabs.
    $('#jqxTabs').jqxTabs({animationType:"fade",selectionTracker:true, width: '100%', height: winHeight-85, position: 'top'});
    
    initInput();
    backgroundBand();
    autosize($(".text,textarea"));    
    $("#reButton").on("click",function(){
    	if(confirm("是否确认通过审核 ?")){
    		setCsltInfo();
    	}
    });    
});

function setCsltInfo(){
	var patientname=$("input[name='PATIENTNAME']").val();
	var sex=$("select[name='SEX']").val();
	var sendstuff=$("input[name='SENDSTUFF']").val();
	var clidata=$("textarea[name='CLIDATA']").val();
	var clihistory=$("textarea[name='CLIHISTORY']").val();
	var first_option=$("textarea[name='FIRST_OPTION']").val();
	var gross=$("textarea[name='GROSS']").val();
	var letter=$("textarea[name='LETTER']").val();
	var remark=$("textarea[name='REMARK']").val();
	var cliresult_cn=$("textarea[name='CLIRESULT_EN']").val();
	 /* alert(patientname+"-"+sex+"-"+sendstuff+"-"+clidata+"-"+clihistory+"-"+first_option
			+"-"+gross+"-"+letter+"-"+remark+"-"+cliresult_cn);*/
	var state=$("input[name='state']").val()=="12"?"13":"19";
	$.post("cslt/setCsltInfo.action"
		,{patientname:patientname
			,sex:sex
			,sendstuff:sendstuff
			,clidata:clidata
			,clihistory:clihistory
			,first_option:first_option
			,gross:gross
			,letter:letter
			,remark:remark
			,cliresult_cn:cliresult_cn
			,ebid:EBID
			,barcode:BARCODE
			,state:state}
		,function(data){
		if(data.respstate){
			//location.href="cslt/auditView.action";
		    bandData();//绑定数据
		    $('#window').jqxWindow("close");
		}else{
			alert("服务器错误，请于管理员联系！");
		}    			
	});
	setPics();
}
//图片属性处理
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


function createElements(width,height) {
    $('#window').jqxWindow({ isModal: true, 
        resizable: false,
        width: width,
        height:height,
        minWidth: 600,
        minHeight: 400,
        maxWidth:1200,
        maxHeight:700
    });
    $('#window').jqxWindow('focus');
    //$('#showWindowButton').jqxButton({width: '100px' });
}

function bandData(){
    var url = "cslt/getAuditList.action";
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
            { name: 'state', type: 'string' },
            { name: 'commitdate', type: 'date' ,sortable:true},
            { name: 'remark', type: 'string' }
        ],
        id: 'id',
        url: url
    };
    var dataAdapter = new $.jqx.dataAdapter(source);//数据对象
    //数据绑定
    $("#jqxgrid").jqxGrid(
    {
        width: "99%",
        height: "100%",
        source: dataAdapter,
        columnsresize: true,
        //filterable: true,
        sortable:true,
        altrows: true,
        columns: [
          { text: '', datafield: 'ebid', width: 250,hidden: true},
          { text: '条码号', datafield: 'barcode', width: 100 },
          { text: '状态', datafield: 'state_msg', width: 100 },
          { text: '姓名', datafield: 'patientname', width: 90 },
          { text: '性别', datafield: 'sex', width: 45 },
          { text: '年龄', datafield: 'age', width: 40 },
          { text: '状态值', datafield: 'state', width: 60 ,hidden: true},
          { text: '提交时间', datafield: 'commitdate',filtertype: 'date', width: 160 ,cellsformat:'yyyy-MM-dd HH:mm:ss'},
          { text: '备注', datafield: 'remark', minwidth: 160 }
      ]
    });
}




function initInput(){
	$('#reButton').jqxButton({width: '100px' });
	$('input[name="PATIENTNAME"]').jqxInput({width: '150px' });
	//$('input[name="SEX"]').jqxInput({width: '150px' });
	$('input[name="SENDSTUFF"]').jqxInput({width: '330px' });

/*	$('textarea[name="CLIDATA"]').jqxTextArea({height:"80",enable:true });
	$('textarea[name="CLIHISTORY"]').jqxTextArea({ height:"80",enable:true });
	$('textarea[name="FIRST_OPTION"]').jqxTextArea({height:"80",enable:true  });
	$('textarea[name="GROSS"]').jqxTextArea({ height:"80",enable:true });
	$('textarea[name="REMARK"]').jqxTextArea({height:"80",enable:true  });
	$('textarea[name="CLIRESULT_EN"]').jqxTextArea({ height:"400",enable:true });
	$('textarea[name="LETTER"]').jqxTextArea({ height:"400",enable:true });*/
}

function bandWindowCn(objCn ){
	$("span[name='PATIENTNAME'").text(objCn.patientname==null?"":objCn.patientname);
	$("span[name='SEX'").text(objCn.sex==null?"":objCn.sex);
	$("span[name='SENDSTUFF'").text(objCn.sendstuff==null?"":objCn.sendstuff);
	$("span[name='CLIDATA'").text(objCn.clidata==null?"":objCn.clidata);
	$("span[name='CLIHISTORY'").text(objCn.clihistory==null?"":objCn.clihistory);
	$("span[name='FIRST_OPTION'").text(objCn.first_option==null?"":objCn.first_option);
	$("span[name='GROSS'").text(objCn.gross==null?"":objCn.gross);
	//$("textarea[name='LETTER']").val(objCn.letter==null?"":objCn.letter);
	$("span[name='LETTER'").text(objCn.letter==null?"":objCn.letter);
	$("span[name='REMARK'").text(objCn.remark==null?"":objCn.remark);
	
}

function bandWindowEn(objEn){
	$("input[name='PATIENTNAME']").val(objEn.patientname==null?"":objEn.patientname);
	$("select[name='SEX']").val(objEn.sex);
	$("input[name='SENDSTUFF']").val(objEn.sendstuff==null?"":objEn.sendstuff);
	$("textarea[name='CLIDATA']").val(objEn.clidata==null?"":objEn.clidata);
	$("textarea[name='CLIHISTORY']").val(objEn.clihistory==null?"":objEn.clihistory);
	$("textarea[name='FIRST_OPTION']").val(objEn.first_option==null?"":objEn.first_option);
	$("textarea[name='GROSS']").val(objEn.gross==null?"":objEn.gross);
	//$("span[name='LETTER'").text(objEn.letter==null?"":objEn.letter);
	$("textarea[name='LETTER']").val(objEn.letter==null?"":objEn.letter);
	$("textarea[name='REMARK']").val(objEn.remark==null?"":objEn.remark);
	$("textarea[name='CLIRESULT_EN']").val(objEn.cliresult_cn==null?"":objEn.cliresult_cn);
	$("span[name='CLIRESULT_EN'").text(objEn.cliresult_en==null?"":objEn.cliresult_en);
}


//绑定背景
function backgroundBand(){
	$("input,.text").focus(function(){
		$(this).parents(".list").addClass("show");
	});
	$("input,.text").blur(function(){
		$(this).parents(".list").removeClass("show");
	});
}




    