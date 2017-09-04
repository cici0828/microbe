$(function() {
	pageInit(window);
});

var expertview = {
	page : "第{0}/{1}页"
};

function pageInit() {
	$("#da_evf_table").jqGrid(
	{
		url : "cslt/getCsltlistByExpertid.action",
		datatype : "json",
		height : 410,
		mtype: "post",
	    jsonReader: {  
	            root:"gridModel",  
	            records: "record",  
	            repeatitems : false,  
	    },
		colNames : [ "序号", "功能", "状态", "条码号", "病理编号", "患者姓名", "性别", "年龄", "取材部位", "费用", "送检中心", "发起人", "送检医院" ],
		colModel : [
		            {name : "pid", index : "pid", width : 0, hidden : true}, 
		            {name : "act", index : "act", width : 50 ,align : "center", sortable : false}, 
		            {name : "state", index : "state", width : 60}, 
		            {name : "pt_barcode", index : "pt_barcode", width : 100}, 
		            {name : "patient.folderno", index : "patient.folderno",width : 125}, 
		            {name : "patient.patientname", index : "patient.patientname", width : 90}, 
		            {name : "patient.sex", index : "patient.sex", width : 45, align : "center", sortable : false},
		            {name : "patient.agex", index : "patient.agex", width : 40, sortable : false}, 
		            {name : "patient.sendstuff", index : "patient.sendstuff", width : 150}, 
		            {name : "fee", index : "fee", width : 45, sortable : false}, 
		            {name : "dept", index : "dept", width : 90},
		            {name : "loginname", index : "loginname", width : 75}, 
		            {name : "patient.samplefrom", index : "patient.samplefrom", width : 300,} 
		],
		rowNum : 30,
		altRows : true,
		//rowList : [ 30, 60, 120 ],
		pager : "#pager2",
		sortname : "pt_barcode",
		loadonce : true,
		viewrecords : true,
		sortorder : "desc",
		hoverrows : false,
		gridComplete : function() 
		{
			var table = jQuery("#da_evf_table");
			var ids = table.jqGrid('getDataIDs');
			for ( var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var open = "<a title='打开病例' class='opencase' href='javascript:void(0)' onclick=opencase({0})><span class='ui-icon ui-icon-folder-open'><span></a>";
				open = $.myformat(open, cl);
				var commit = "<a title='提交病例' class='commitcase' href='javascript:void(0)' onclick=commitcase({0})><span class='ui-icon ui-icon-check'><span></a>";
				commit = $.myformat(commit, cl);
				var back = "<a title='退回病例' class='backcase' href='javascript:void(0)' onclick=backcase({0})><span class='ui-icon ui-icon-closethick'><span></a>";
				back = $.myformat(back, cl);
				table.jqGrid(
					'setRowData', 
					ids[i], {
						act : open + commit + back
					});
			}
		},
		ondblClickRow : function(id) 
		{
			//opencase(id);
		},
		subGrid : true,
		subGridOptions : 
		{
			plusicon : "ui-icon ui-icon-plus",
			minusicon : "ui-icon ui-icon-minus",
			openicon : "ui-icon ui-icon-arrowreturnthick-1-e",
			//selectOnExpand : false,
			//reloadOnExpand : false
		},
		subGridBeforeExpand : function(subgrid_id, row_id)
		{
			//$(".sgexpanded").removeClass("sgexpanded").addClass("sgcollapsed");	
		},
		subGridRowColapsed: function(subgrid_id, row_id)
		{
		},
		subGridRowExpanded : function(subgrid_id, row_id)
		 {		 	
			var subgrid_table_id, pager_id;

			subgrid_table_id = subgrid_id + "_t";
			pager_id = "p_" + subgrid_table_id;
			
			$("#" + subgrid_id).html(
				"<table id='"+ subgrid_table_id+ "' class='scroll'></table><div id='"+ pager_id+ "' class='scroll'></div>");
			var r = $("#da_evf_table").jqGrid('getRowData', row_id);
			$.ajax({
				type : "post",
				url : "cslt/getCsltBaseInfoByPID.action",
				data : "pid=" + r["pid"],
				success : function(csltbaseinfo) {
					jQuery("#" + subgrid_table_id).jqGrid(
							{
								datatype : "local",
								sortname : "bid",
								colNames : [ "序号","临床资料", "病史", "大体所见", "初诊意见"],
								colModel : [
								            { name:"bid", width : 0, hidden : true},
								{
								 	name : "clidata",
								 	index : "clidata",
								 	width : 200
								 }, 
							     {
									 name : "clihistory",
									 index : "clihistory",
									 width : 200
								 }, 
								 {
									 name : "gross",
									 index : "gross",
									 width : 300
								 }, 
								 {
									 name : "first_option",
									 index : "first_option",
									 width : 400
								 },
								 ],
								 rowNum : 1,
								 height: 100
								});
					jQuery("#" + subgrid_table_id).jqGrid('addRowData', 0, csltbaseinfo);
				}			   
			});
			/*jQuery("#" + subgrid_table_id).jqGrid(
				{
					datatype : "json",
					url : "cslt/getCsltBaseInfoByPID.action?pid=" + r["pid"],
					sortname : "bid",
					colNames : [ "序号","临床资料", "病史", "大体所见", "初诊意见"],
					colModel : [
					            { name:"bid", width : 100, hidden : false},
					{
					 	name : "clidata",
					 	index : "clidata",
					 	width : 200
					 }, 
				     {
						 name : "clihistory",
						 index : "clihistory",
						 width : 200
					 }, 
					 {
						 name : "gross",
						 index : "gross",
						 width : 200
					 }, 
					 {
						 name : "first_option",
						 index : "first_option",
						 width : 200
					 },
					 ],
					 rowNum : 10,
					 height: 100,
		             prmNames: {search: "search"},  
		             pager: pager_id,
		             sortorder : "desc"
					});*/
		}
	});

$("#da_evf_table").jqGrid(
	'navGrid', '#pager2', 
	{
		edit : false,
		add : false,
		del : false
	}
	);

$("#pager2 .ui-pager-table").css("width","660px");
$("#pager2 .pager2_left").css("display","none");
}

function opencase(id) {
	var data = jQuery("#da_evf_table").jqGrid('getRowData', id);
	var stitle = data["patient.patientname"] + "|" + data["patient.agex"] + "|"+ data["patient.sex"] + "|" + data["pt_barcode"];
	if ($("#da_evf_info").tabs('exists', stitle)) {
		$("#da_evf_info").tabs('select', stitle);
	} else
	{
		$("#da_evf_info").tabs({
			onLoad : function(title) {
				var tableid = "#table_baseinfo_" + data['pid'];
				var table = $(tableid);
				$("#vpatientname", table).html(data["patient.patientname"]);
				$("#vsex", table).html(data["patient.sex"]);
				$("#vagex", table).html(data["patient.agex"]);
				$("#vsamplefrom", table).html(data["patient.samplefrom"]);
				$("#vfolderno", table).html(data["patient.folderno"]);
				$("#vsendstuff", table).html(data["patient.sendstuff"]);	
				
				// 获取临床资料
				$.ajax(
				{
					type : "post",
					url : "cslt/getCsltBaseInfoByPID.action",
					data : "pid=" + data["pid"],
					success : function(csltbaseinfo) {
						$("#vclidata" + data["pid"]).html(csltbaseinfo["clidata"]);
						$("#vclihistory" + data["pid"]).html(csltbaseinfo["clihistory"]);
						$("#vgross" + data["pid"]).html(csltbaseinfo["gross"]);
						$("#vfirst_option" + data["pid"]).html(csltbaseinfo["first_option"]);
					}
				});
				
				// 获取切片信息
				$.ajax(
				{
					type : "post",
					url : "cslt/getCsltSliceInfoByBarcode.action",
					data : "barcode=" + data["pt_barcode"],
					success : function(slicelist) {
						var tb = $("#table_short_slicepic_list"+ data["pid"]);
						var sRowHtml = '';
						$.each(slicelist,function(i, v) {
							var picurl = v["shortpicurl"];
							var dsno = v["dsno"];
							var dsid = v["dsid"];
							var rs = "";
							var picfrom;
							if ($.trim(picurl) != "")
							{
								picfrom = "<td><img class='short_slicepic' src={0}></img></td>";
								picfrom = $.myformat(picfrom, picurl);
							}
							else
							{
								picfrom = "<td><a id='{1}' href='javascript:void(0)'><img id='p{1}' class='short_slicepic'  src='pic/loading.gif'></img></a></td>";
								picfrom = $.myformat(picfrom, dsid, "slice"+dsid);
								//cslt/getSliceShortPicByDSID.action?dsid={0}
							}
								
							sRowHtml = "<tr>"+ "<td>"+ (i + 1)+ "</td>"+ picfrom+ "<td>"+ dsno+ "</td>"+ "<td>"+ rs+ "</td>"+ "</tr>";
							tb.append(sRowHtml);

							
						    if ($.trim(picurl) == "")
							{								
						    	var click = function(){
									$("#pslice"+dsid).click(function(){
										viewSlice(dsid);});
									};	
						    	$.loadPic("cslt/getSliceShortPicByDSID.action?dsid="+dsid, "pslice"+dsid, click);
							}						
						});						
					}
				});
				
				// 获取诊断信息
				$.ajax({
					type : "post",
					url : "cslt/GetUnCommitResult.action",
					data : "pid=" + data["pid"],
					success : function(resultInfo) {
						var c = $("#da_csltdetail_result_input"+ data["pid"]);
						c.val(resultInfo.expertcslt);
						autosize(c);						
						c.keyup(function(){
							resultDesc=$(this).val();
							/*$.cookie(resultName, resultDesc , {
							 * expires : 365
							});*/});
						
						var resultName = "r"+ data["pid"];
						/*var resultDesc = $.cookie(resultName);					
						if (resultDesc != "null") c.val(resultDesc);*/
						var oSaveReport = $("#saveReport" + data["pid"]);
				        var oBackCase = $("#backCase" + data["pid"]);
				        var oCancelReport = $("#cancelReport" + data["pid"]);
				        var oEditReport = $("#editReport" + data["pid"]);
				        oSaveReport.click(function(){saveReport(data["pid"],resultName);});
				        oBackCase.click(function(){backcase(data["pid"]);});
				        oCancelReport.click(function(){cancelReport(data["pid"]);});
				        oEditReport.click(function(){startEdit(data["pid"]);});
				        var piclist = resultInfo.attcPictureList;
				        var oPicArea = $("#da_reportPicAreaContainer" + data["pid"]);
				        var oPicScroll = $(".da_reportPicAreaScroll", oPicArea);
				        var len = (typeof(piclist) === 'undefined')? 0: piclist.length;
				        
	                    for ( var i = 0; i < len; i++){
	                    	 var picInfo = piclist[i];
	                    	 var id = picInfo["id"].replace(/-|{|}/g,"");
		                     var picArea = "picArea" + id;
		                     var h = "<div id={3} class='da_reportPicArea' style='display:{2}'>" 
		                    	 + "<span class='da_reportPicTool'><span class='da_delReportPic'>" 
		                    	 +"<a href='javascript:void(0)' onclick=deletePicPrompt('{3}')>" 
		                    	 +"<img src='pic/delete.png' style='width:22px;height:22px' alt='删除图片' title='删除图片'/>" 
		                    	 + "</a></span></span>"
					             //+ "<span class='da_reportPic'><img src={0}></span>"
		                    	 + "<span class='da_reportPic'><span><img id={0} src='pic/loading.gif'></span></span>"
					             + "<span class='da_reportPicDesc'>"
					             + "<textarea class='da_csltdetail_result_input da_reportPicDescInput' placeholder='在此输入图片描述......'>{1}</textarea></span>"
					             + "</div>";
		                     var display = "block";
		                     if (i > 1) display = "none";
		                     var snapID="snap"+id;
		                     
		                     h = $.myformat(h,/*"cslt/getCsltPicByID.action?id="+ picInfo["id"]*/snapID,
		                    		 picInfo["describe"] ,display, picArea);
		                     
		                     var child = $(h);
		                     oPicScroll.append(child);
		                     //$("#"+snapID).css("width","75px").css("height","75px");		                     
		                     var finish = {};
		                     finish.obj = $("#"+snapID);
		                     finish.success=function(){
		                    	// this.obj.css("width","100%").css("height","100%");	
		                     };
		                     
		                     
		                     $.loadPic("cslt/getCsltPicByID.action?id="+ picInfo["id"], snapID, finish);
		                     $("span textarea", child).data("picDescName",picArea).keyup(
		                    		 function(){
		                    		 });				
	                     }//for
	                     						 
						 if (len > 0) {
							    oPicArea.css("display", "block");
								var h = "<div class='da_reportPicPageCount'>{0}</div>";
								h = $.myformat(h,$.myformat(expertview.page,1,Math.round(len / 2)));
								oPicScroll.append(h);
						 }
						 endEdit(data["pid"]);
					}						
				});//获取诊断信息$.ajax				
			}//onload			
		});//tabs
		
		$("#da_evf_info").tabs("add", {
			class : "datapanel",
			title : stitle,
			closable : true,
			href : "cslt/getCsltDetailForm.action?pid=" + data["pid"]
		});	
	}//if else
}

function commitcase(id) {
	alert("提交病例" + id);
};

function backcase(pid) {
	alert("backcase" + pid);
	endEdit(pid);
};

function saveReport(pid, resultName) {
	alert("save"+pid);
	$.cookie(resultName, null);
	endEdit(pid);
}

function cancelReport(pid){
	alert("cancel"+pid);
	endEdit(pid);
}

function startEdit(pid){
	var oSaveReport = $("#saveReport" + pid);
	var oBackCase = $("#backCase" + pid);
	var oCancelReport = $("#cancelReport" + pid);
	var oEditReport = $("#editReport" + pid);
	var oReportResult = $("#da_csltdetail_result_input"+ pid);
	var oPicArea = $("#da_reportPicAreaContainer" + pid);
	var oPicDescInput = oPicArea.find("textarea");
	var oPicTool = $(".da_reportPicTool");
	oSaveReport.show();
	oBackCase.show();
	oCancelReport.show();
	oPicTool.show();
	oEditReport.hide();	
	oReportResult.removeAttr("readonly");
	oPicDescInput.removeAttr("readonly");
}

function endEdit(pid){
	var oSaveReport = $("#saveReport" + pid);
	var oBackCase = $("#backCase" + pid);
	var oCancelReport = $("#cancelReport" + pid);
	var oEditReport = $("#editReport" + pid);
	var oReportResult = $("#da_csltdetail_result_input"+ pid);
	var oPicArea = $("#da_reportPicAreaContainer" + pid);
	var oPicDescInput = oPicArea.find("textarea");
	var oPicTool = $(".da_reportPicTool");
	oSaveReport.hide();
	oBackCase.hide();
	oCancelReport.hide();
	oEditReport.show();
	oPicTool.hide();
	oReportResult.attr("readonly","readonly");
	oPicDescInput.attr("readonly","readonly");
}

function resizeTextArea(o) {
	autosize(o);
}

function PicScroll(flag, area) {
	var a = $(area);
	var p = $(".da_reportPicAreaScroll", a);
	var pc = p.children(".da_reportPicPageCount");
	var len = p.children().not(':last').length;
	if (len == 0)
		return;
	if ((flag == -1) && (p.children().eq(0).css("display") == "block")) {
		return;
	}
	if ((flag == 1) && (p.children().eq(len - 1).css("display") == "block")) {
		return;
	}
	for ( var i = 0; i < p.children().length; i++) {
		var c = p.children().eq(i);
		if ((c.css("display") == "block")) {
			break;
		}
	}
	Math.round((i + 1) / 2);
	p.children().eq(i).css("display", "none");
	if (i + 1 < len)
		p.children().eq(i + 1).css("display", "none");
	if (flag == 1) {
		p.children().eq(i + 2).css("display", "block");
		p.children().eq(i + 3).css("display", "block");
	}
	if (flag == -1) {
		p.children().eq(i - 1).css("display", "block");
		p.children().eq(i - 2).css("display", "block");
	}
	pc.html($.myformat(expertview.page, Math.round((i + 1) / 2) + flag, Math.round(len / 2)));
}

function deletePicPrompt(id){
	var oParent = $("#"+id);
	var oPrompt=$("<span></span>");
	oPrompt.addClass("da_reportPicDelPrompt");
	oPrompt.append("<div>");
	oPrompt.append("<div>");
	$("div:eq(0)", oPrompt).html("确实要删除该图片吗？")
	.css("position","relatvie")
	.css("margin-top","75px")
	.css("text-align","center")
	.css("color","red")
	.css("font-size","16px")
	.css("font-weight","bold");
	
	$("div:eq(1)", oPrompt)
	.css("position","relatvie")
	.css("text-align","center")
	.append("<a href='javascript:void(0)'>确认</a>").append("<a href='javascript:void(0)'>取消</a>");
	
	$("a",oPrompt).linkbutton({
	}).css("width","50px").css("margin-left","5px");
	var r = {c:1};
	$("a:eq(0)",oPrompt).bind("click",r,function(event){
		deleteReportPic(id);
		oPrompt.remove();
	});
		
	$("a:eq(1)",oPrompt).click(function(){
		oPrompt.remove();
	});
	
	oParent.prepend(oPrompt);
}

function deleteReportPic(id)
{
	var oDel = $("#"+id);
	var oParent = oDel.parent();
	var oPage = $(".da_reportPicPageCount", oParent);
	var len = oParent.children().length - 1; // 减去页数提示div
	var index = oDel.index();
	var i = 0;
	var blockIndex = index;
	if ((index + 1) < len) {
		i = (index % 2 == 0) ? index + 2 : index + 1;
		blockIndex = oParent.children().eq(i).css("display", "block").index();
	} else {
		oParent.children().eq(index - 1).css("display", "block").index();
		oParent.children().eq(index - 2).css("display", "block").index();
	}
	len = len - 1;
	oDel.remove();
	if (len == 0)
		oParent.parent().css("display", "none");
	else {
		var pages = Math.round((len) / 2);
		oPage.html($.myformat(expertview.page, Math.round(blockIndex / 2), pages));
	}	
}

function viewSlice(id){
   $("#da_evf_container").prepend("<div id='da_viewSlice'><div><a href='javascript:void(0)'><img src='pic/close.png' style='width:50px;height:50px'/><a></div></div>");
   var c=$("#da_viewSlice");
   $("a", c).click(function(){
	   c.remove();
   });
}

