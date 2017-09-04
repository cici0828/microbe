<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>霍普金斯项目管理中心</title>   
    
    
    <style type="text/css">
    	body{margin:0px auto; padding:0px auto;}
    	h2{margin:0px auto; padding:10px; font-size:17px; }
    	.focus_content{background:#ddd;}
    	.show{background:#ccffff;}
    	.list{border-bottom:1px dotted #ccc;}
    	.list .li_l{padding:5px 1%; width:48%; float:left;}
    	.list .li_r{padding:5px 1%; background:#eeeeee; width:48%; float:left;}
    	.list .line{margin:0px auto; clear:both;}
    	.list .text{width:95%; min-height:60px;}
    	.list .textarea{width:95%; min-height:370px;}
    	
    	
    </style>
    <script type="text/javascript">
    	var authCode=${authCode};
    </script>
        

 <link rel="stylesheet" href="js/jqwidgets/styles/jqx.base.css" type="text/css" /> 
    <link rel="stylesheet" href="css/adjust.css" type="text/css"></link>
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
    <script type="text/javascript" src="js/jqwidgets/globalization/globalize.js"></script>
    <script type="text/javascript" src="js/jqwidgets/globalization/localization.js"></script>
  	<script type="text/javascript" src="js/ViewJs/MainViews.js"></script>
  	
  </head>
  
  <body>	
<div id="loaderBox" style="width:100%; height:100%; position:absolute; z-index:9999; background:#000; display:none;"></div>
<div id="jqxLoader"></div>

	<div id="da_evf_container">
<!-- 		<div id="da_evf_head" style="margin:0px auto; min-width:1000px; width:90%; height:60px; overflow:hidden;">
			<iframe class="da_title" src="head.jsp" frameborder="0" style="width:99%"></iframe>			
		</div> -->
		<div style="margin:0px auto; min-width:1000px; width:99%;" id="jqxWidget">
			<div id="mainSplitter" style="min-height:500px; height: 100%">
	            <div class="splitter-panel">
					<div id='jqxTree'>
		                <ul>
                    		<!-- <li id='home'>Home</li> -->
		                    <li id="jqxTree10_11_17" whereData="10,11,17" item-selected='true'>基础资料准备(<span class="total_num">0</span>)
		                        <ul>
		                            <li id="jqxTreeItem10" whereData="10">未建(<span class="total_sub_num">0</span>)</li>
		                            <li id="jqxTreeItem11" whereData="11"> 新建(<span class="total_sub_num">0</span>)</li>
		                            <li id="jqxTreeItem17" whereData="17">JHH退回(<span class="total_sub_num">0</span>)</li>
		                        </ul>
		                    </li>
		                    <li id="jqxTree12_13" whereData="12,13">基础资料翻译(<span class="total_num">0</span>)
		                        <ul>
		                            <li id="jqxTreeItem12" whereData="12">翻译审核中(<span class="total_sub_num">0</span>)</li>
		                            <li id="jqxTreeItem13" whereData="13">待提交JHH(<span class="total_sub_num">0</span>)</li>
		                        </ul>
		                    </li>
		                    <li id="jqxTree15" whereData="15">JHH看片(<span class="total_num">0</span>)
		                        <ul>
		                            <li id="jqxTreeItem15" whereData="15">JHH看片(<span class="total_sub_num">0</span>)</li>
		                        </ul>
		                    </li>
		                    <li id="jqxTree16_18_19_21" whereData="16,18,19,21">报告结果翻译(<span class="total_num">0</span>)
		                        <ul>
		                            <li id="jqxTreeItem16" whereData="16">待翻译报告(<span class="total_sub_num">0</span>)</li>
		                            <li id="jqxTreeItem18" whereData="18">翻译审核中(<span class="total_sub_num">0</span>)</li>
		                            <li id="jqxTreeItem19" whereData="19">待发布报告(<span class="total_sub_num">0</span>)</li>
		                            <li id="jqxTreeItem21" whereData="21">提交发布(<span class="total_sub_num">0</span>)</li>
		                        </ul>
		                    </li>
		                </ul>
	                </div>
	            </div>
	            <div class="splitter-panel">
					<div id="jqxgrid"></div>
	            </div>
			</div>
		</div>
		
	</div>
	
		
	<div id="window">
		<div>详细信息</div>
		<div>
			<form id="form1" name="form1" action="?">
				<input type="hidden" name="ebid" />
				<input type="hidden" name="barcode" />
				<input type="hidden" name="bid" />
				<input type="hidden" name="state" />
				<input type="hidden" name="age" />
				<input type="hidden" name="picnum" />
				
				<div id="actionButtons" style="padding-bottom:10px;">
					<input type="button" style='margin-top: 5px;' value="保存数据" id="saveButton" />
					<input type="button" style='margin-top: 5px;' value="提交审核" id="auditButton" />
					<input type="button" style='margin-top: 5px;' value="提交JHH" id="jhhButton" />
					<input type="button" style='margin-top: 5px;' value="发布报告" id="releaseButton" />
				</div>
				<div id='jqxTabs'>
		            <ul>
		                <li style="margin-left: 30px;">基本信息</li>
		                <li>附言</li>
		                <li><span id="hos"></span>诊断意见</li>
		                <li>诊断截图</li>
		                <li>操作日志</li>
		            </ul>
					<div>
						<div id="mail_detail">
							<div class="list">
								<div class="li_l"><h2>中文信息</h2></div>
								<div class="li_r"><h2>英文信息</h2></div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>姓　　名：</strong>
									<span name="patientname"></span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
									<strong>性　　别：</strong>
									<span name="sex"></span>
								</div>
								<div class="li_r">
									<strong>姓　　名：</strong>
									<input type="text" name="patientname" /> &nbsp; &nbsp; &nbsp;
									<strong>性　　别：</strong>
									<!--<input type="text" name="sex" /> -->
									 <select name="sex">
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
									<span name="sendstuff"></span>
								</div>
								<div class="li_r">
									<strong>取材部位：</strong>
									<input type="text" name="sendstuff" />
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>临床资料 ：</strong>
									<div>
										<textarea class="text" name="clidata_cn"></textarea>
									</div>
								</div>
								<div class="li_r">
									<strong>临床资料 ：</strong>
									<div>
										<textarea class="text" name="clidata"></textarea>
									</div>
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>病史：</strong>
									<div>
										<textarea class="text"  name="clihistory_cn"></textarea>	
									</div>
								</div>
								<div class="li_r">
									<strong>病史：</strong>
									<div>
										<textarea class="text"  name="clihistory"></textarea>								
									</div>
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>初诊意见：</strong>
									<div>
										<textarea class="text" name="first_option_cn"></textarea>
									</div>
								</div>
								<div class="li_r">
									<strong>初诊意见：</strong>
									<div>
										<textarea class="text" name="first_option"></textarea>
									</div>
								</div>
								<div class="line"></div>
							</div>
							<div class="list">
								<div class="li_l">
									<strong>大体所见：</strong>
									<div>
										<textarea class="text" name="gross_cn"></textarea>		
									</div>
								</div>
								<div class="li_r">
									<strong>大体所见：</strong>
									<div>
										<textarea class="text" name="gross"></textarea>								
									</div>
								</div>
								<div class="line"></div>
							</div>	
							<div class="list">
								<div style="padding:5px 1%;">
									<strong>备注：</strong>
									<div>
										<textarea class="text" name="remark" style="width:98%;"></textarea>
									</div>
								</div> 
<!-- 								<div class="li_l">
									<strong>备注：</strong>
									<div>
										<textarea class="text" name="remark_cn"></textarea>
									</div>
								</div>
								<div class="li_r">
									<strong>备注：</strong>
									<div>
										<textarea class="text" name="remark"></textarea>
									</div>
								</div> -->
								<div class="line"></div>
							</div>	
						</div>
					</div>
					<div>		
						<div class="list">
							<div class="li_l"><h2>附言（中文）</h2></div>
							<div class="li_r"><h2>附言（英文）</h2></div>
							<div class="line"></div>
						</div>
						<div class="list">
							<div class="li_l">
								<div>
									<textarea class="textarea" name="letter_cn"></textarea>
								</div>
							</div>
							<div class="li_r">
								<div>
									<textarea class="textarea" name="letter"></textarea>
								</div>
							</div>
							<div class="line"></div>
						</div>
					</div>
					<div>					
						<div class="list">
							<div class="li_l"><h2>诊断意见（中文）</h2></div>
							<div class="li_r"><h2>诊断意见（英文）</h2></div>
							<div class="line"></div>
						</div>
						<div class="list">
							<div class="li_l">
								<div>
									<div>
										<textarea class="textarea"  name="cliresult_cn"></textarea>
									</div>
								</div>
							</div>
							<div class="li_r">
								<div>
									<textarea class="textarea"  name="cliresult_en"></textarea>
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
					<div>		
						<div id="logGrid"></div>
						<div class="line"></div>
					</div>
				</div>
				</form>
			</div>
		</div>		
  </body>
</html>

