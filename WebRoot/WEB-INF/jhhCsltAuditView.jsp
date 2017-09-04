<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <style type="text/css">
    	.focus_content{background:#ddd;}
    	.show{background:#f0eeee;}
    	.list{border-bottom:1px dotted #ccc;}
    	.list .li_l{padding:5px 1%; width:48%; float:left;}
    	.list .li_r{padding:5px 1%; background:#eeeeee; width:48%; float:left;}
    	.list .line{margin:0px auto; clear:both;}
    	.list .text{width:95%; min-height:60px;}
    	.list .textarea{width:95%; min-height:400px;}
    </style>
    <title>霍普金斯项目管理中心</title>
    
    <link rel="stylesheet" href="js/jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="js/scripts/jquery-1.11.1.min.js"></script>
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
    <script type="text/javascript" src="js/scripts/demos.js"></script>    

    <script type="text/javascript" src="js/jqwidgets/jqxwindow.js"></script>
    <script type="text/javascript" src="js/jqwidgets/jqxpanel.js"></script>  	
    <script type="text/javascript" src="js/jqwidgets/jqxsplitter.js"></script>
    <script type="text/javascript" src="js/jqwidgets/jqxtabs.js"></script>
    <script type="text/javascript" src="js/jqwidgets/jqxdatatable.js"></script>

	<script type="text/javascript" src="js/other/autosize.js"></script>
    <script type="text/javascript" src="js/jqwidgets/globalization/globalize.js"></script>
    <script type="text/javascript" src="js/jqwidgets/globalization/globalize.culture.ch-CN.js"></script>


  	<script type="text/javascript" src="js/ViewJs/CsltAuditView.js"></script>
  	
 
    <script type="text/javascript">    
    function allPrpos ( obj ) { 
	  // 用来保存所有的属性名称和值 
	  var props = "" ; 
	  // 开始遍历 
	  for ( var p in obj ){ // 方法 
	  if ( typeof ( obj [ p ]) == " function " ){ //obj [ p ]() ; 
	  } else { // p 为属性名称，obj[p]为对应属性的值 
	  props += p + " = " + obj [ p ] + " \n " ; 
	  } } // 最后显示所有的属性 
	  alert ( props ) ;
	}
    </script>    
  </head>
  
<body class='default'>

<div id="da_evf_container">
<!-- 		<div id="da_evf_head" style="margin:0px auto; min-width:1000px; width:99%; height:60px; overflow:hidden;">
			<iframe class="da_title" src="head.jsp" frameborder="0" style="width:99%"></iframe>			
		</div> -->
    <div id='jqxWidget' style="font-size: 13px; font-family: Verdana; width: 99%">
		<div id="jqxgrid" style="margin:0px auto; min-width:1000px; width:99%"></div>
		<form id="form1" name="form1" action="?">
		<div id="window">
			<div>详细信息</div>
			<div>
				<div style="padding-bottom:10px;">
					<input type="button" style='margin-top: 5px;' value="审核通过" id="reButton" />
				</div>	
				<div id='jqxTabs'>
		            <ul>
		                <li style="margin-left: 30px;">基本信息</li>
		                <li>附言</li>
		                <li><span id="hos"></span>诊断意见</li>
		                <li>诊断截图</li>
		            </ul>			
					<div>
						 <input name="ebid" type="hidden" value="" />
						 <input name="barcode" type="hidden" value="" />
						 <input name="state" type="hidden" value="" />
						<div id="mail_detail">
							<div class="list">
								<div class="li_l"><h2>中文信息</h2></div>
								<div class="li_r"><h2>英文信息</h2></div>
								<div class="line"></div>
							</div>							
							<div class="list">
								<div class="li_l">
									<strong>姓名：</strong>
									<span name="PATIENTNAME"></span>
									<strong>性别：</strong>
									<span name="SEX"></span>
								</div>
								<div class="li_r">
									<strong>姓名：</strong>
									<input type="text" name="PATIENTNAME" />
									<strong>性别：</strong>
									<!--<input type="text" name="SEX" />  -->
									 <select name="SEX">
										<option value=""> </option>
										<option value="M">Male</option>
										<option value="F">Female</option>
									</select>
								</div>
								<div class="line"></div>
							</div>
							
							<div class="list">
								<div class="li_l">
									<strong>取材部位：</strong>
									<span name="SENDSTUFF"></span>
								</div>
								<div class="li_r">
									<strong>取材部位：</strong>
									<input type="text" name="SENDSTUFF" />
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>临床资料 ：</strong>
									<div>
										<span name="CLIDATA"></span>
									</div>
								</div>
								<div class="li_r">
									<strong>临床资料 ：</strong>
									<div>
										<textarea class="text" name="CLIDATA"></textarea>
									</div>
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>病史：</strong>
									<div>
										<span name="CLIHISTORY"></span>
									</div>
								</div>
								<div class="li_r">
									<strong>病史：</strong>
									<div>
										<textarea class="text"  name="CLIHISTORY"></textarea>								
									</div>
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>初诊意见：</strong>
									<div>
										<span name="FIRST_OPTION"></span>
									</div>
								</div>
								<div class="li_r">
									<strong>初诊意见：</strong>
									<div>
										<textarea class="text" name="FIRST_OPTION"></textarea>
									</div>
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>大体所见：</strong>
									<div>
										<span name="GROSS"></span>
									</div>
								</div>
								<div class="li_r">
									<strong>大体所见：</strong>
									<div>
										<textarea class="text" name="GROSS"></textarea>								
									</div>
								</div>
								<div class="line"></div>
							</div>	
							<div class="list">
								<div style="padding:5px 1%;">
									<strong>备注：</strong>
									<div>
										<textarea class="text" name="REMARK" style="width:98%;"></textarea>
									</div>
								</div>
<!-- 								<div class="li_l">
									<strong>备注：</strong>
									<div>
										<span name="REMARK"></span>
									</div>
								</div>
								<div class="li_r">
									<strong>备注：</strong>
									<div>
										<textarea class="text" name="REMARK"></textarea>
									</div>
								</div> -->
								<div class="line"></div>
							</div>	
						</div>
					</div>
					<div>
						<div class="list">
							<div class="li_l">
								<strong>附言：</strong>
								<div>
									<span name="LETTER"></span>
								</div>
							</div>
							<div class="li_r">
								<strong>附言：</strong>
								<div>
									<textarea class="textarea" name="LETTER"></textarea>
								</div>
							</div>
							<div class="line"></div>
						</div>	
					</div>		
					<div>
						<div class="list">
							<div class="li_l">
								<strong>诊断意见：</strong>
								<div>
									<div>
										<textarea class="textarea"  rows="20" name="CLIRESULT_EN"></textarea>
									</div>
								</div>
							</div>
							<div class="li_r">
								<strong>诊断意见：</strong>
								<div>
									<span name="CLIRESULT_EN"></span>
								</div>
							</div>
							<div class="line"></div>
						</div>	
					</div>								
					<div>		
						<div id="pics">
						</div>
						<div class="line"></div>
					</div>	
				</div>
			</div>
		</div>
		</form>		
	</div>

	<div id="da_evf_footer">

	</div>
</div>
</body>
</html>
