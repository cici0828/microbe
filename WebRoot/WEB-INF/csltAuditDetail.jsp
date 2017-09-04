<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'csltAuditDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
    <script type="text/javascript" src="js/scripts/demos.js"></script>
    

    <script type="text/javascript" src="js/jqwidgets/jqxwindow.js"></script>
    <script type="text/javascript" src="js/jqwidgets/jqxpanel.js"></script>
  	
    <script type="text/javascript" src="js/jqwidgets/jqxsplitter.js"></script>
    <script type="text/javascript" src="js/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="js/jqwidgets/jqxloader.js"></script>
    
    
    
    <script type="text/javascript">
    var ebid=${ebid};
    var barcode="${barcode}";
    </script>
  	<script type="text/javascript" src="js/ViewJs/CsltAuditDetail.js"></script>

	<style type="text/css">
		.div_textarea_warp{margin:5px; height:100px;}
		.div_input_warp{margin:5px; min-height:30px;}
		.focus_content{background:#f2bcba;}
	</style>	
  </head>
  
  <body>
  <div id="jqxLoader"></div>  
    
	<div id="window">
		<div style="">
			<input type="button" style='margin-top: 5px;' value="切换显示" id="orientationButton" />
			<input type="button" style='margin-top: 5px;' value="审核通过" id="reButton" />
			 <br><br>
			 <form action="?">
			 <input name="ebid" type="hidden" value="" />
			 <input name="barcode" type="hidden" value="" />
			 <div id="mainSplitter">
			     <div id="splitter1" style="border:1p solid #ddd;">
					<table style="margin:15px 10px;" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="120" align="right">姓名：</td>
							<td><div class="div_input_warp" name="PATIENTNAME"></div></td>
						</tr>
						<tr>
							<td align="right">性别：</td>
							<td><div class="div_input_warp" name="SEX"></div></td>
						</tr>
						<tr>
							<td align="right">取材部位：</td>
							<td><div class="div_textarea_warp" name="SENDSTUFF"></div></td>
						</tr>
						<tr>
							<td align="right">临床资料 ：</td>
							<td><div class="div_textarea_warp" name="CLIDATA"></div></td>
						</tr>
						<tr>
							<td align="right">病史：</td>
							<td><div class="div_textarea_warp" name="CLIHISTORY"></div></td>
						</tr>
						<tr>
							<td align="right">初诊意见：</td>
							<td><div class="div_textarea_warp" name="FIRST_OPTION"></div></td>
						</tr>
						<tr>
							<td align="right">大体所见：</td>
							<td><div class="div_textarea_warp" name="GROSS"></div></td>
						</tr>
						<tr>
							<td align="right">附言：</td>
							<td><div class="div_textarea_warp" name="LETTER"></div></td>
						</tr>
						<tr>
							<td align="right">备注：</td>
							<td height="130"><div class="div_textarea_warp" name="REMARK"></div></td>
						</tr>	
						<tr>
							<td align="right">诊断意见：</td>
							<td><textarea cols="50" rows="5" name="CLIRESULT_EN"></textarea></td>
						</tr>					
					</table>
			     </div>
			     <div id="splitter2" style="border:1px solid #ddd;">
					<table style="margin:15px 10px;" width="100%" border="0">
						<tr>
							<td width="120" align="right">姓名：</td>
							<td><input type="text" name="PATIENTNAME" /></td>
						</tr>
						<tr>
							<td align="right">性别：</td>
							<td><input type="text" name="SEX" /></td>
						</tr>
						<tr>
							<td align="right">取材部位：</td>
							<td><textarea cols="50" rows="5" name="SENDSTUFF"></textarea></td>
						</tr>
						<tr>
							<td align="right">临床资料：</td>
							<td><textarea cols="50" rows="5" name="CLIDATA"></textarea></td>
						</tr>
						<tr>
							<td align="right">病史：</td>
							<td><textarea cols="50" rows="5" name="CLIHISTORY"></textarea></td>
						</tr>
						<tr>
							<td align="right">初诊意见：</td>
							<td><textarea cols="50" rows="5" name="FIRST_OPTION"></textarea></td>
						</tr>
						<tr>
							<td align="right">大体所见：</td>
							<td><textarea cols="50" rows="5" name="GROSS"></textarea></td>
						</tr>
						<tr>
							<td align="right">附言：</td>
							<td><textarea cols="50" rows="5" name="LETTER"></textarea></td>
						</tr>
						<tr>
							<td align="right">备注：</td>
							<td><textarea cols="50" rows="5" name="REMARK"></textarea></td>
						</tr>
						<tr>
							<td align="right">诊断意见：</td>
							<td><div class="div_textarea_warp" name="CLIRESULT_EN"></div></td>
						</tr>
					</table>
				</div>
			 </div>
			</form>
		</div>
	</div>
  </body>
</html>
