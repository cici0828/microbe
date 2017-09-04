<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>迪安远程会诊系统</title>

<script type="text/javascript">
	var authCode = ${authCode};
	var resourceImageDomain = '${resourceImageDomain}';
</script>
<link rel="stylesheet" href="js/jqwidgets/styles/jqx.base.css"
	type="text/css" />
<script type="text/javascript" src="js/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/formatDate.js"></script>

<script type="text/javascript" src="js/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="js/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="js/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="js/jqwidgets/jqxmenu.js"></script>
<script type="text/javascript" src="js/jqwidgets/jqxgrid.js"></script>
<script type="text/javascript" src="js/jqwidgets/jqxgrid.selection.js"></script>
<script type="text/javascript"
	src="js/jqwidgets/jqxgrid.columnsresize.js"></script>
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
<script type="text/javascript"
	src="js/jqwidgets/globalization/localization.js"></script>

<script type="text/javascript" src="js/jqwidgets/jqxpanel.js"></script>

<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet" href="css/diagnosis.css" type="text/css"></link>
<link rel="stylesheet" href="css/jquery-ui/jquery-ui.css"
	type="text/css"></link>
<script type="text/javascript" src="js/confirmWindows.js"></script>

<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="css/easyui/gray/easyui.css" type="text/css"></link>
<script type="text/javascript" src="js/ViewJs/base.js"></script>
<script type="text/javascript" src="js/ViewJs/diagnosisRequest.js"></script>
	<link rel="stylesheet" href="css/adjust.css" type="text/css">
</head>

<body>
<!--     <div class="diagnosisTitle"><label>123</label></div> -->
	<div id="splitContainer">
		<div>
			<form class="form-inline" style="margin-top: 3px; margin-left: 10px">
			<div class="form-group">
				<label> 条码号：</label> 
				<input style="display: none;" type="text" /> <input type='text'
					name='search_barcode' id='search_barcode' class="form-control"
					placeholder="条码号" />
					</div>
			</form>
		</div>
		<div>
			<div id="splitter">
				<div>
					<div id="jqxgrid"></div>
				</div>

				<div>
					<div id="spliter_sub" class="dazd_split_sub_warp">
						<div>
							<div class="dazd_title">病例信息</div>
						</div>
						<div id="DetailPanel" class="scroll_auto">
							<div class="row detail_row_style">
								<div class="col-md-4">
									<strong  style="width: 75px; display: inline; text-align: right">条码号：</strong> <span id="view_barcode"></span>
								</div>
								<div class="col-md-8">
									<strong>病理编号：</strong> <span id="view_folderno"></span>
								</div>
							</div>

							<div class="row detail_row_style">
								<div class="col-md-4">
									<strong>姓名：</strong> <span id="vieiw_patientname"></span>
								</div>
								<div class="col-md-4">
									<strong>性别：</strong> <span id="view_sex"></span>
								</div>
								<div class="col-md-4">
									<strong>年龄：</strong> <span id="view_age"></span>
								</div>
							</div>

							<div class="row detail_row_style">
								<div class="col-md-4">
									<strong>住院号：</strong> <span id="view_clinicid"></span>
								</div>
								<div class="col-md-4">
									<strong>科别：</strong> <span id="view_clinicnme"></span>
								</div>
								<div class="col-md-4">
									<strong>床号：</strong> <span id="view_bedno"></span>
								</div>
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>送检医院：</strong> <span id="view_samplefrom"></span>
								</div>
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>送检材料：</strong> <span id="view_sendstuff"></span>
								</div>
							</div>

							<div class="row detail_row_style">
								<div class="col-md-4">
									<strong>送检医师：</strong> <span id="view_doctor"></span>
								</div>
								<div class="col-md-8">
									<strong>送检日期：</strong> <span id="view_senddate"></span>
								</div>
<!-- 								<div class="col-md-4">
									<strong>报告方式：</strong> <span id="view_reportmethod"></span>
								</div> -->
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>切片列表：</strong>
									<div id="pics_table"></div>
								</div>
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>临床诊断：</strong>
									<div>
										<textarea readonly id="view_diagnosis" rows="1"></textarea>
									</div>
								</div>
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>大体所见：</strong>
									<div>
										<textarea readonly id="view_gross" rows="1"></textarea>
									</div>
								</div>
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>临床资料：</strong>
									<div>
										<textarea readonly id="view_clidata" rows="1"></textarea>
									</div>
								</div>
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>病史：</strong>
									<div>
										<textarea readonly id="view_clihistory" rows="1"></textarea>
									</div>
								</div>
							</div>
							<div class="row detail_row_style">
								<div class="col-md-12">
									<strong>初诊意见：</strong>
									<div>
										<textarea readonly id="view_first_option" rows="1"></textarea>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="logWindows" class="none">
		<div>
			<strong>操作日志</strong>
		</div>
		<div>
			<div id="logGrid"></div>
		</div>
	</div>

</body>
</html>
