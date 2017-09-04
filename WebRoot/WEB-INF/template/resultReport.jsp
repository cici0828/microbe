<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
     <div id="reportConfirmWaittig" style="top: 0;left: 0;right: 0;bottom: 0
     ;margin: auto;position: absolute; width: 75px; height: 75px; display: none">
     <img src="pic/loading.gif">
     </div>
     <div style="margin: 20px;">
        <div style="margin-bottom: 15px">
          <label>诊断结果：</label>
          <div>
             ${csltModel.expertcslt}
          </div>
        </div>

        <label>镜下所见：</label>  
        <table border="1" cellpadding="0">
        <tr>
            <th style="text-align: center">图片</th>
            <th style="width: 500px; text-align: center">图片描述</th>
            <th style="width: 120px; text-align: center">显示</th>
        </tr>
        <c:forEach items="${picList}" var="picList">
            <tr>
                <td><img src=${picURL}${picList.localdir} style="
                width: 150px;height: 150px; margin: 5px"></td>
                <td style="padding: 5px">${picList.describe}</td>
                
                <c:if test="${picList.display==1}">
                    <td style="text-align: center;">报告显示</td>           
                </c:if>
                <c:if test="${picList.display!=1}">
                    <td style="text-align: center;">参考截图</td>           
                </c:if>
            </tr>
        </c:forEach>
    </table>
     </div>
  </body>
</html>
