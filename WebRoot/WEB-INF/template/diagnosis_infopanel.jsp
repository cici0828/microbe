<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'diagnosis_infoPanel' starting page</title>

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
		<div id="infoPanel">
			<div class="form-group">
				<label for="clidata">临床资料:</label>
				<textarea class="form-control" id="clidata" rows="2" placeholder="临床资料" /></textarea>
			</div>
			<div class="form-group">
				<label for="clihistory">病史 :</label>
				<textarea class="form-control" id="clihistory" rows="4" type="text" placeholder="病史" ></textarea>
			</div>
			<div class="form-group">
				<label for="first_option">初诊意见:</label>
				<textarea class="form-control" id="first_option" rows="2" type="text" placeholder="初诊意见" ></textarea>
			</div>
			<div class="form-group">
				<label for="gross">大体所见:</label>
				<textarea class="form-control" id="gross" rows="2" type="text" placeholder="大体所见" ></textarea>
			</div>
		</div>
  </body>
</html>
