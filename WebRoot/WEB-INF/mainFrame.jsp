<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
    <title>迪安诊断病理诊断中心</title>
    
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/mainframe.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap_adjust.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/mainframe.js"></script>
</head>

<body>
    <div class="mainContainer">
        <div class="mainTop">
            <div class="mainTop_head">
                <img class="mainTop_head_logo" src="pic/dian_logo.png">
                <div class="mainTop_head_setting">
                    <span class="info">
                        <span>登录中心：</span><span>${logindept}</span>
                    <span class="pipe">|</span>
                    <span>  用户名：</span><span>${username}【${fullname}】</span>
                    </span>
                    <span class="setting">
                        <a href="javascript:void(0)">个人资料</a>
                        <span class="pipe">|</span>
                    <a href="javascript:void(0)">消息<span class="badge">0</span></a>
                    <span class="pipe">|</span>
                    <a href="/pmis/system/logout.action">退出</a>
                    </span>
                </div>
                <div class="mainTop_head_avatar">
                    <img src="pic/default_avatar.jpg">
                </div>
            </div>
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="homePage.html" class="link" target="context" onclick="a()">首页</a></li>

                           <!--  <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">远程病理<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="cslt/diagnosisRequest.action?authCode=1" target="context">会诊申请</a></li>
                                    <li><a href="cslt/diagnosisRequest.action?authCode=2" target="context">会诊审核</a></li>
                                    <li><a href="cslt/diagnosisRequest.action?authCode=3" target="context">会诊结果确认</a></li>
                                    <li><a href="cslt/diagnosisRequest.action?authCode=4" target="context">读片会</a></li>
                                    <li><a href="cslt/diagnosisRequest.action?authCode=5" target="context">会诊样本列</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="cslt/jhhViews.action" target="context">霍普金斯项目管理中心</a></li>
                                    <li><a href="cslt/jhhAuditView.action" target="context">翻译审核</a></li>
                                </ul>
                            </li>--> 
                                                      
                            <c:forEach items="${modules}" var="modules">
                              <li class="dropdown">
                                 <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${modules.modulename}<span class="caret"></span></a>
                                 <ul class="dropdown-menu">
                                    <c:forEach items="${modules.menuList}" var="menuList">
                                       <li><a href=${menuList.url} target="context">${menuList.billname}</a></li>
                                    </c:forEach>
                                 </ul>
                              </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
<!--         <div class="mainNav">
           <label>会诊申请</label>
        </div> -->
        <div id="mainMiddle" class="mainMiddle">
            <!--  <iframe  id="context" name="context" frameborder="0" scrolling="no"
            ></iframe>-->
            <div>
              <input type="text" name="input_barcode" id="input_barcode" />
              <input type="text" name="input_microbecode" id="input_microbecode" />
              <input type="text" name="test" id="test" />
              <input type="button" name="btn_query" id="btn_query" value="查询"/>
              <input type="button" name="btn_query" id="btn_reset" value="重置"/>
            </div>
            <div id="mic_rslt">
            </div>
        </div>
    </div>
    <div class="mainBottom">
    </div>
</body>


</html>
