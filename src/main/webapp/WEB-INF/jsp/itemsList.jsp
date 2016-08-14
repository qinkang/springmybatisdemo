<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>查询商品列表</title>
</head>

<body>
商品列表:
<table>
    <tr width="100%" border="1">
        <td>商品名称</td>
        <td>商品价格</td>
        <td>商品描述</td>
        <c:forEach items="${itemsList}" var="item">
    <tr>
        <td>${item.name}</td>
        <td>${item.price}</td>
        <td>${item.detail}</td>
    </tr>
    </c:forEach>
    </tr>
</table>

</body>

</html>
