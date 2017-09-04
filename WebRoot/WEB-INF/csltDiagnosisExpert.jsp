<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'csltDiagnosisExpert.jsp' starting page</title>

	<link rel="stylesheet" href="js/jqwidgets/styles/jqx.base.css" type="text/css" />
	<script type="text/javascript" src="js/scripts/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/formatDate.js"></script>
	
	<script type="text/javascript" src="js/jqwidgets/jqxcore.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxbuttons.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxscrollbar.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxmenu.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxgrid.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxgrid.selection.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxgrid.columnsresize.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxdata.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxgrid.filter.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxgrid.sort.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxlistbox.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxdropdownlist.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxinput.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxcheckbox.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxcalendar.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxdatetimeinput.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxtextarea.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxgrid.pager.js"></script>
	<script type="text/javascript" src="js/scripts/demos.js"></script>
	
	<script type="text/javascript" src="js/jqwidgets/jqxwindow.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxpanel.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxsplitter.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxtabs.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxtree.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxloader.js"></script>
	<script type="text/javascript" src="js/jqwidgets/jqxdatatable.js"></script>
	<script type="text/javascript" src="js/other/autosize.js"></script>
	<script type="text/javascript" src="js/jqwidgets/globalization/localization.js"></script>
	
	<script type="text/javascript" src="js/jqwidgets/jqxpanel.js"></script>
	
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="css/diagnosis.css" type="text/css"></link>
	<link rel="stylesheet" href="css/jquery-ui/jquery-ui.css" type="text/css"></link>
	<script type="text/javascript" src="js/confirmWindows.js"></script>
	
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="css/easyui/gray/easyui.css" type="text/css"></link>
	<script type="text/javascript" src="js/ViewJs/base.js"></script>

	
	<script type="text/javascript">
		$(function(){
			expertBind();
		});
		
		function expertBind(){
			var cellsrenderer = function (row, columnfield, value, defaulthtml, columnproperties) {
				var html=' <center><a href="javascript:void();" onclick="return false;" class="a_link"> 查看 </a> <a href="javascript:void(0);" onclick="dazd_confirm({text:\'确定删除此专家?\',callback:deleteExpert,back_params:{id:'+value+'}})"> 删除 </a></center> ';	
				return html;		
			};
			//var key=$("#expertKey").val();
			var url = "cslt/getExpertList.action";
			var source = {
				datatype : "json",
				datafields : [ { name : 'id', type : 'int' }, 
				{ name : 'expertname', type : 'string' }, 
				{ name : 'dept', type : 'string' }, 
				{ name : 'tel', type : 'string' }, 
				{ name : 'nature', type : 'string' }, 
				{ name : 'viewnum', type : 'int' }, 
				{ name : 'fee', type : 'float' }, 
				{ name : 'iswork', type : 'string' }, 
				{ name : 'finishdate', type : 'date', sortable : true },
				{ name : 'area', type : 'string' },
				{ name : 'specialty', type : 'string' } ],
				id : 'id', 
				url : url,
				pager : function(pagenum, pagesize, oldpagenum) {
					// callback called when a page or page size is changed.
				}
			};
			var dataAdapter = new $.jqx.dataAdapter(source);//数据对象
			//数据绑定
			$("#expertGrid").jqxGrid({
				width : 1000,
				height : "99%",
				source : dataAdapter,
				//columnsresize : true,
				//sortable:true,
				//pageable : true,
				//pagesize : 20,
		        showtoolbar: true,
		        toolbarheight:35,
				localization : getLocalization('cn'),
				altrows : true,
				columns : [ { text : '', datafield : 'id', width : 80, cellsrenderer:cellsrenderer }, 
				{ text : '专家姓名', datafield : 'expertname', width : 100 }, 
				{ text : '联系方式', datafield : 'tel', width : 100 },
				{ text : '所属中心', datafield : 'dept', width : 90 }, 
				{ text : '工作性质', datafield : 'nature', width : 80 }, 
				{ text : '在看片数', datafield : 'viewnum', width : 60 }, 
				{ text : '在看片数预计完成时间', datafield : 'finishdate', filtertype : 'date', width : 160, cellsformat : 'yyyy-MM-dd' },
				{ text : '是否在岗', datafield : 'iswork', minwidth : 60 } ,
				{ text : '', datafield : 'area', minwidth : 160,hidden:true },
				{ text : '专家服务', datafield : 'specialty', minwidth : 160,hidden:true } ],
			    rendertoolbar: function (toolbar) {
			    	var container = $('<div class="form-group tips"></div>');
			    	var label=$('<label for="">所属专业：</label>');
			    	var select=$('<select id="expert_key" name="expert_key" /></select>');
			    	var add_button=$('<input type="button" class="add_button_r" value="新增专家" />');
			    	
			    	$('<option value="">全部</option>').appendTo(select);
			    	$('<option value="乳腺病理">乳腺病理</option>').appendTo(select);
			    	$('<option value="甲状腺病理">甲状腺病理</option>').appendTo(select);
			    	$('<option value="皮肤病理">皮肤病理</option>').appendTo(select);
			    	$('<option value="神经系统病理">神经系统病理</option>').appendTo(select);
			    	$('<option value="呼吸系统病理">呼吸系统病理</option>').appendTo(select);
			    	$('<option value="消化系统病理">消化系统病理</option>').appendTo(select);
			    	$('<option value="腹膜及腹膜后病理">腹膜及腹膜后病理</option>').appendTo(select);
			    	$('<option value="骨软组织病理">骨软组织病理</option>').appendTo(select);
			    	$('<option value="妇产科病理">妇产科病理</option>').appendTo(select);
			    	$('<option value="肾脏病理">肾脏病理</option>').appendTo(select);
			    	$('<option value="淋巴造血系统病理">淋巴造血系统病理</option>').appendTo(select);
			    	$('<option value="泌尿系统病理">泌尿系统病理</option>').appendTo(select);
			    	$('<option value="耳鼻喉病理">耳鼻喉病理</option>').appendTo(select);
			    	$('<option value="纵膈病理">纵膈病理</option>').appendTo(select);
			    	$('<option value="内分泌病理">内分泌病理</option>').appendTo(select);
			    	$('<option value="肝胆、胰腺病理">肝胆、胰腺病理</option>').appendTo(select);
			    	$('<option value="涎腺病理">涎腺病理</option>').appendTo(select);
			    	$('<option value="细胞病理">细胞病理</option>').appendTo(select);		    	
			    	toolbar.append(container);
			    	container.append(add_button);
			    	container.append(label);
			    	container.append(select);
			    	
			    	select.on("change",function(){
			        	addfilter(select.val());
			    	});
			    	
			    	add_button.on("click",function(){
					    /* var expert_rowid = $('#expertGrid').jqxGrid('getselectedrowindex');
					    var expert_row=$('#expertGrid').jqxGrid('getrowdata',expert_rowid); */
			    		createUpdateWindows();
			    	});
			    }
			});
	
			$("#expertGrid").on('rowselect', function(event) {
				var rownum = event.args.rowindex;
				var data = event.args.row;
			});
		}
		
		//过滤条件
		function addfilter(value){
			if(value==""){
				$("#expertGrid").jqxGrid('clearfilters');
				return;
			}
		    var filtergroup = new $.jqx.filter();
		    var filter_or_operator = 1;
		    var filtervalue = value;
		    var filtercondition = 'contains';
		    var filter1 = filtergroup.createfilter('stringfilter', filtervalue, filtercondition);
		    filtergroup.addfilter(filter_or_operator, filter1);
		    // add the filters.
		    $("#expertGrid").jqxGrid('addfilter', 'specialty', filtergroup );
		    // apply the filters.
		    $("#expertGrid").jqxGrid('applyfilters');
		}
		function deleteExpert(data){
			$.ajax({
				url:"cslt/delExpert.action",
				   type: "POST",
				   dataType: "json",
				   data:{
				   		id : data.id
				   },
				   success:function(data){
				    	if(data.respstate){
			        		dazd_alert({text:"操作成功！"});
				    	}
				   },
				   error:function(data){
					   //alert(data);
				   },
				   complete :function(){
						$("#expertGrid").jqxGrid('updatebounddata');
				   }
				});
		}
		function createUpdateWindows(data) {
			var updata_windows=$("<div>",{
				id : "dazd-update-windows",
				css : {
					padding:"5px 10px"
				}
			}).appendTo($("body"));
			
			updata_windows.dialog({
			    title: "新增专家",
			    width: 800,
			    height:500,
			    closed: false,
			    cache: false,
			    top:0,
				href:"template/insert_expert.action",
			    modal: true,
			    buttons:[{
					text:'确定',
					width:75,
					handler:function(){
					   $.ajax({
						url:"cslt/setExpert.action",
						   type: "POST",
						   dataType: "json",
						   data:{
						   		"expert.expertname":$("#expertname").val(),
						   		"expert.sex":$("#sex").val(),
						   		"expert.tel":$("#tel").val(),
						   		"expert.nature":$("#nature").val(),
						   		"expert.fee":$("#fee").val(),
						   		"expert.specialty":$("#specialty").val(),
						   		"expert.iswork":$("#iswork").val()
						   },
						   success:function(data){
						    	if(data.respstate){
					        		dazd_alert({text:"保存成功！"});
						    	}
						   },
						   error:function(data){
							   //alert(data);
						   },
						   complete :function(){
								$("#expertGrid").jqxGrid('updatebounddata');
								updata_windows.dialog("destroy");			   
						   }
						});
					}
				},{
					text:'取消',
					width:75,
					handler:function(){
						updata_windows.dialog("destroy");
					}
				}],
				onLoad:function(){
					/* if(row.clidata){$("#clidata").val(row.clidata);}
					if(row.clihistory){$("#clihistory").val(row.clihistory);}
					if(row.first_option){$("#first_option").val(row.first_option);}
					if(row.gross){$("#gross").val(row.gross);}
					autosize(updata_windows.find("textarea"));
					autosize.update(updata_windows.find("textarea")); */
				},
				onClose:function(){
					updata_windows.dialog("destroy");
				}
			});		
		}	
	</script>
  </head>
  
  <body>
	  <center>
	    <div id="expertGrid"></div>
	  </center>
  </body>
</html>
