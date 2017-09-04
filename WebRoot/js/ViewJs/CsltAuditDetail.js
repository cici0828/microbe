var bindCn=false;
var bindEn=false;
$(function(){
    var winWidth=document.body.clientWidth;
    var winHeight=document.body.clientHeight-60;
	var panels = [{ size: winWidth/2-50}];
    $("#splitter1").jqxPanel({ width: winWidth/2-50, height: winHeight});
    $("#splitter2").jqxPanel({ width:winWidth- winWidth/2+10, height: winHeight});
    $('#mainSplitter').jqxSplitter({showSplitBar:false, height: winHeight, width: winWidth-30, panels: panels });

    $('#reButton').jqxButton({  width: 95 });
    $('#orientationButton').jqxButton({  width: 90 });
    $('#orientationButton').click(function () {
        var currentOrientation = $('#mainSplitter').jqxSplitter('orientation');
        winWidth=document.body.clientWidth;
        winHeight=document.body.clientHeight-60;
        if (currentOrientation === 'vertical') {
       	 	panels = [{ size: winHeight/2-30 }];
            $('#mainSplitter').jqxSplitter('orientation', 'horizontal');

            $("#splitter1").jqxPanel({ width: winWidth-30, height: winHeight/2-30});
            $("#splitter2").jqxPanel({ width:winWidth-30, height: winHeight/2+30});
        } else {
       	 	panels = [{ size: winWidth/2-50 }];
            $('#mainSplitter').jqxSplitter('orientation', 'vertical');
            $("#splitter1").jqxPanel({ width: winWidth/2-50, height: winHeight});
            $("#splitter2").jqxPanel({ width:winWidth- winWidth/2+10, height: winHeight});
        }               
        
        $('#mainSplitter').jqxSplitter({showSplitBar:false, height: 600, width: winWidth-30, panels: panels });
    });    
    
    
    $("#jqxLoader").jqxLoader({ isModal: true, width: 100, height: 60, imagePosition: 'top' });
    $('#jqxLoader').jqxLoader('open');
    //获取绑定中文信息
 	$.get("cslt/getAuditCnView.action",{BARCODE:barcode},function(data){
 		if(data!=null){
	 		bandWindowCn(data);
	 		bindCn=true;
	 		if(bindCn&&bindEn){$('#jqxLoader').jqxLoader('close');}
 		}else{
 			alert("获取数据错误!");
 		}
 	});
 	
 	//获取绑定英文信息
 	$.get("cslt/getAuditView.action",{EBID:ebid},function(data){
 		if(data!=null){
	 		bandWindowEn(data);
	 		bindEn=true;
	 		if(bindCn&&bindEn){$('#jqxLoader').jqxLoader('close');}
 		}else{
 			alert("获取数据错误!");
 		}
 	});
 	//背景绑定
    backgroundBand();
    
    
    
    $("#reButton").on("click",function(){
    	if(confirm("是否确认通过审核 ?")){
    		var patientname=$("input[name='PATIENTNAME']").val();
    		var sex=$("input[name='SEX']").val();
    		var sendstuff=$("textarea[name='SENDSTUFF']").val();
    		var clidata=$("textarea[name='CLIDATA']").val();
    		var clihistory=$("textarea[name='CLIHISTORY']").val();
    		var first_option=$("textarea[name='FIRST_OPTION']").val();
    		var gross=$("textarea[name='GROSS']").val();
    		var letter=$("textarea[name='LETTER']").val();
    		var remark=$("textarea[name='REMARK']").val();
    		var cliresult_cn=$("textarea[name='CLIRESULT_EN']").val();
    		
    		 /* alert(patientname+"-"+sex+"-"+sendstuff+"-"+clidata+"-"+clihistory+"-"+first_option
    				+"-"+gross+"-"+letter+"-"+remark+"-"+cliresult_cn);*/
    				
    		$.post("cslt/commitAudit.action"
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
					,ebid:ebid
					,barcode:barcode}
				,function(data){
				if(data.respstate){
					location.href="cslt/auditView.action";
				}else{
					alert("服务器错误，请于管理员联系！");
				}    			
			});
    	}
    });
});


function bandWindowCn(objCn ){
	$("div[name='PATIENTNAME'").text(objCn.patientname==null?"":objCn.patientname);
	$("div[name='SEX'").text(objCn.sex==null?"":objCn.sex);
	$("div[name='SENDSTUFF'").text(objCn.sendstuff==null?"":objCn.sendstuff);
	$("div[name='CLIDATA'").text(objCn.clidata==null?"":objCn.clidata);
	$("div[name='CLIHISTORY'").text(objCn.clihistory==null?"":objCn.clihistory);
	$("div[name='FIRST_OPTION'").text(objCn.first_option==null?"":objCn.first_option);
	$("div[name='GROSS'").text(objCn.gross==null?"":objCn.gross);
	$("div[name='LETTER'").text(objCn.letter==null?"":objCn.letter);
	$("div[name='REMARK'").text(objCn.remark==null?"":objCn.remark);
}

function bandWindowEn(objEn){
	$("input[name='PATIENTNAME']").val(objEn.patientname==null?"":objEn.patientname);
	$("input[name='SEX']").val(objEn.sex==null?"":objEn.sex);
	$("textarea[name='SENDSTUFF']").val(objEn.sendstuff==null?"":objEn.sendstuff);
	$("textarea[name='CLIDATA']").val(objEn.clidata==null?"":objEn.clidata);
	$("textarea[name='CLIHISTORY']").val(objEn.clihistory==null?"":objEn.clihistory);
	$("textarea[name='FIRST_OPTION']").val(objEn.first_option==null?"":objEn.first_option);
	$("textarea[name='GROSS']").val(objEn.gross==null?"":objEn.gross);
	$("textarea[name='LETTER']").val(objEn.letter==null?"":objEn.letter);
	$("textarea[name='REMARK']").val(objEn.remark==null?"":objEn.remark);
	$("textarea[name='CLIRESULT_EN'").val(objEn.cliresult_cn==null?"":objEn.cliresult_cn);
	$("div[name='CLIRESULT_EN']").text(objEn.cliresult_en==null?"":objEn.cliresult_en);
	
	if(objEn.state=="12"){
		$("textarea[name='CLIRESULT_EN']").attr("disabled",true);
	}else if(objEn.state=="18"){
		$("input[name='PATIENTNAME']").attr("disabled",true);
		$("input[name='SEX']").attr("disabled",true);
		$("textarea[name='SENDSTUFF']").attr("disabled",true);
		$("textarea[name='CLIDATA']").attr("disabled",true);
		$("textarea[name='CLIHISTORY']").attr("disabled",true);
		$("textarea[name='FIRST_OPTION']").attr("disabled",true);
		$("textarea[name='GROSS']").attr("disabled",true);
		$("textarea[name='LETTER']").attr("disabled",true);
	}else{
		$("textarea[name='REMARK']").attr("disabled",true);		
		$("textarea[name='CLIRESULT_EN']").attr("disabled",true);
		$("input[name='PATIENTNAME']").attr("disabled",true);
		$("input[name='SEX']").attr("disabled",true);
		$("textarea[name='SENDSTUFF']").attr("disabled",true);
		$("textarea[name='CLIDATA']").attr("disabled",true);
		$("textarea[name='CLIHISTORY']").attr("disabled",true);
		$("textarea[name='FIRST_OPTION']").attr("disabled",true);
		$("textarea[name='GROSS']").attr("disabled",true);
		$("textarea[name='LETTER']").attr("disabled",true);
	}
}

function backgroundBand(){
	$("input,textarea").focus(function(){
		$("div[name='"+$(this).attr("name")+"']").addClass("focus_content");
	});
	$("input,textarea").blur(function(){
		$("div[name='"+$(this).attr("name")+"']").removeClass("focus_content");
	});
}

