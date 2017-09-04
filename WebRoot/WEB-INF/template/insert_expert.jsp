<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'insert_expert.jsp' starting page</title>

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
				<label for="expertname">专家姓名</label>
				<input type="text" class="form-control" id="expertname" placeholder="专家姓名" />
			</div>
			<div class="form-group">
				<label for="sex">性别</label>
				<select class="form-control" id="sex" placeholder="性别">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>
			<div class="form-group">
				<label for="tel">专家电话</label>
				<input type="text" class="form-control" id="tel" placeholder="电话" />
			</div>
			<div class="form-group">
				<label for="nature">工作性质</label>
				<select class="form-control" id="nature" placeholder="工作性质">
					<option value="内部">内部</option>
					<option value="外部">外部</option>
				</select>
			</div> 
			<div class="form-group">
				<label for="iswork">是否工作</label>
				<select class="form-control" id="iswork" placeholder="是否工作">
					<option value="是">是</option>
					<option value="否">否</option>
				</select>
			</div>
			<div class="form-group">
				<label for="fee">看片收费</label>
				<input type="text" class="form-control" id="fee" placeholder="看片收费" />
			</div>
		
			<div class="form-group">
				<label for="specialty">专业领域</label>
				<textarea class="form-control" id="specialty" rows="2" placeholder="专业领域" /></textarea>
			</div>
		</div>
  </body>
</html>
