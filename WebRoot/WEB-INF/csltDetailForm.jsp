<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
</head>
<body>
	<div id="da_csltcontext">
		<div id="da_csltdetailform_left" class="da_csltdetailform">
			<table id="table_baseinfo_${pid}" class="table_baseinfo">
				<tr>
					<td class="da_label">患者姓名:</td>
					<td id="vpatientname"></td>
					<td class="da_label">性别：</td>
					<td id="vsex"></td>
					<td class="da_label">年龄:</td>
					<td id="vagex"></td>
				</tr>
				<tr>
					<td class="da_label">送检医院:</td>
					<td id="vsamplefrom" colspan="3"></td>
					<td class="da_label">病理编号:</td>
					<td id="vfolderno"></td>
				</tr>
				<tr>
					<td class="da_label">取材部位:</td>
					<td id="vsendstuff" colspan="5"></td>
				</tr>
			</table>

			<table class="table_baseinfo table_baseinfo2">
				<tr>
					<td class="da_label">临床资料:</td>
				</tr>
				<tr>
					<td id="vclidata${pid}">
				</tr>
			</table>

			<table class="table_baseinfo table_baseinfo2">
				<tr>
					<td class="da_label">病史:</td>
				</tr>
				<tr>
					<td id="vclihistory${pid}"></td>
				</tr>
			</table>

			<table class="table_baseinfo table_baseinfo2">
				<tr>
					<td class="da_label">大体所见:</td>
				</tr>
				<tr>
					<td id="vgross${pid}"></td>
				</tr>
			</table>
			<table class="table_baseinfo table_baseinfo2">
				<tr>
					<td class="da_label">初诊意见:</td>
				</tr>
				<tr>
					<td id="vfirst_option${pid}">
				</tr>
			</table>
			<table class="table_baseinfo table_baseinfo2">
				<tr>
					<td class="da_label">备注:</td>
				</tr>
				<tr>
					<td id="vcsltmemo${pid}">
				</tr>
			</table>
			<table class="table_baseinfo table_baseinfo2">
				<tr>
					<td class="da_label">切片:</td>
				</tr>
				<tr>
					<td>
						<table id="table_short_slicepic_list${pid}"
							class="table_baseinfo table_short_slicepic_list">
							<tr>
								<th id="">#</th>
								<th>缩略图</th>
								<th>名称</th>
								<th>染色</th>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
		<div id="da_csltdetailform_right" class="da_csltdetailform"
			onkeydown="resizeTextArea(this);">
			<div class="da_clst">
				<div id="da_reportArea">
					<div class="da_label da_label1">填写报告</div>
					<div id="da_reportPicAreaContainer${pid}"
						class="da_reportPicAreaContainer">
						<!-- <div class="da_reportPicAreaScroll_left"><div class='ui-icon ui-icon-circle-arrow-w'></div></div> -->
						<div class="da_reportPicAreaScroll_left">
							<div>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									onclick="PicScroll(-1,'#da_reportPicAreaContainer${pid}')">&lt&lt</a>
							</div>
						</div>
						<div class="da_reportPicAreaScroll"></div>
						<div class="da_reportPicAreaScroll_right">
							<div>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									onclick="PicScroll(1,'#da_reportPicAreaContainer${pid}')">&gt&gt</a>
							</div>

						</div>
					</div>
					<div>
						<textarea id="da_csltdetail_result_input${pid}"
							class="da_csltdetail_result_input" data-autosize-on="true"
							placeholder="在此输入诊断意见......" rows="4"></textarea>
					</div>
					<div id="da_reportToolBar">
						<span class="da_reportToolBar_left">
							<a id="cancelReport${pid}" href="javascript:void(0)" class="easyui-linkbutton cancelReport">撤消修改</a>
						</span>
						<span class="da_reportToolBar_right">
							<a id="editReport${pid}" class="easyui-linkbutton editReport" href="javascript:void(0)"
								>修改报告</a> 
							<a id="backCase${pid}" class="easyui-linkbutton backCase" href="javascript:void(0)"
								>退回病例</a> 
							<a id="saveReport${pid}" class=" easyui-linkbutton saveReport"
								href="javascript:void(0)">保存报告</a>
						</span>
					</div>

				</div>


			</div>
		</div>

		<!-- <table class="table_baseinfo table_baseinfo3">
				<tr>
					<td class="da_label">病理图像:</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
			<table class="table_baseinfo table_baseinfo3">
				<tr>
					<td class="da_label">病理诊断:</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table> -->


		<!-- 			<form action=""> -->
		<!-- 				<textarea class="da_csltdetail_result_input" rows="2" -->
		<!-- 					placeholder="在此输入诊断意见"></textarea> -->

		<!-- 			</form> -->
		<!--  </div>-->
	</div>
	</div>
	<!-- <div id="da-csltreport-accordion" class="easyui-accordion">
			<div title="填写报告" data-options="selected:true"
				class="da_csltdetailfrom_right_context">
				iconCls:'icon-ok' 图标
				<div class="da_csltdetail_result">
					<form action="">
						<textarea class="da_csltdetail_result_input"
							placeholder="在此输入诊断意见" data-required="true"></textarea>

					</form>
				</div>
			</div>
			<div title="诊断历史" data-options=""
				class="da_csltdetailfrom_right_context"></div>
			<div title="流程信息" data-options=""
				class="da_csltdetailfrom_right_context"></div>
			<div title="附件信息" data-options=""
				class="da_csltdetailfrom_right_context"></div>
		</div> -->
	<!--  </div>-->
	<%-- <div id="da_csltdetailfrom_left">
			<div class="csltdetailfrom_left_context">
				<table id="table_baseinfo_${pid}" class="table_baseinfo">
					<tr>
						<td class="da_label" width="15%">患者姓名:</td>
						<td id="vpatientname" width="20%"></td>
						<td class="da_label" width="10%">性别：</td>
						<td id="vsex"></td>
						<td class="da_label" width="15%">年龄:</td>
						<td id="vagex" width="15%"></td>
					</tr>
					<tr>
						<td class="da_label">送检医院:</td>
						<td id="vsamplefrom" colspan="3"></td>
						<td class="da_label">病理编号:</td>
						<td id="vfolderno"></td>
					</tr>
					<tr>
						<td class="da_label">取材部位:</td>
						<td id="vsendstuff" colspan="5"></td>
					</tr>
				</table>
			</div>
			<div class="csltdetailfrom_left_context">
				<table class="table_baseinfo">
					<tr>
						<td class="da_label" height="10px">临床资料:</td>
					</tr>
					<tr>
						<td id="vclidata${pid}" valign="top"></td>
					</tr>
				</table>
			</div>
			<div class="csltdetailfrom_left_context">
				<table class="table_baseinfo">
					<tr>
						<td class="da_label" height="10px">病史:</td>
					</tr>
					<tr>
						<td id="vclihistory${pid}" valign="top"></td>
					</tr>
				</table>
			</div>
			<div class="csltdetailfrom_left_context">
				<table class="table_baseinfo">
					<tr>
						<td class="da_label" height="10px">大体所见:</td>
					</tr>
					<tr>
						<td id="vgross${pid}" valign="top"></td>
					</tr>
				</table>
			</div>
			<div class="csltdetailfrom_left_context">
				<table class="table_baseinfo">
					<tr>
						<td class="da_label" height="10px">初诊意见:</td>
					</tr>
					<tr>
						<td id="vfirst_option" valign="top"></td>
					</tr>
				</table>
			</div>
			<div class="csltdetailfrom_left_context">
				<!--  <table class="table_baseinfo">
				<tr>
					<td class="da_label" height="10px">切片:</td>
				</tr>
			</table>-->
				<table id="table_short_slicepic_list${pid}"
					class="table_short_slicepic_list">
					<tr>
						<th id="">#</th>
						<th>缩略图</th>
						<th>名称</th>
						<th>染色</th>
					</tr>
				</table>
			</div>
		</div>
		<div id="da_csltdetailfrom_right">
			<!-- "collapsed:false,collapsible:false" -->
			<div id="report-accordion" class="easyui-accordion"
				style="width:600px;height:600px;">
				<div title="填写报告" data-options="selected:true
					"
					style="overflow:auto;padding:10px;">
					<!-- iconCls:'icon-ok' 图标 -->
					<form action=""></form>

				</div>
				<div title="诊断历史" data-options="" style="padding:10px;"></div>
				<div title="流程信息" data-options="" style="padding:10px;"></div>
			</div>

		</div> --%>

</body>
</html>


