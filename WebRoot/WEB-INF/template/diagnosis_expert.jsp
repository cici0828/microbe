<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'diagnosis_expert.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<div id="expertGrid"></div>
	<div class="tips">
		<label for="vreporttype">报告方式：</label>
		<select id="vreporttype" name="vreporttype">
		    <option value="诊断报告">诊断报告</option>
			<option value="诊断意见">诊断意见</option>			
		</select> 
		<label for="diagnosis_fee">看片费用：</label>
		<input id="diagnosis_fee" name="diagnosis_fee" type="text" value="0" />
	</div>
  </body>
</html>
