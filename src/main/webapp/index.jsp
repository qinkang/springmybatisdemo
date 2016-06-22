<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    request.setAttribute("app", request.getContextPath());
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="${app}/static/js/jquery-1.9.0.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${app}/static/js/common.js" charset="UTF-8"></script>
</head>
<body>
<h2>Hello World!</h2>
<a href="${app}/user/index">haha</a>
</body>
</html>
