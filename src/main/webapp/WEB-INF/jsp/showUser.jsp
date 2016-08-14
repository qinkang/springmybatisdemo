<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    request.setAttribute("app", request.getContextPath());
%>

<html>
<head>
    <title>测试</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="${app}/static/js/jquery-1.9.0.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${app}/static/js/common.js" charset="UTF-8"></script>
    <script type="text/javascript">
        function submitForm() {
            console.log(serializeObject($('#form1')));
            $.ajax({
                type: "post",
                dataType: "json",
                url: "${app}/user/showUsersByCre",
                data: serializeObject($('#form1')),// 你的formid
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (data) {
                    $("#commonLayout_appcreshi").html(data[0].password);
                    alert(data[0].password);
                }
            });
        }
        $(document).ready(function () {
            $("#button_submit").click(function () {
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "${app}/user/showUsersByCre1",
                    data: serializeObject($('#form1')),// 你的formid
                    async: false,
                    error: function (request) {
                        alert("Connection error");
                    },
                    success: function (data) {
                        $("#commonLayout_appcreshi").html(data[0].user_name);
                        alert(data[0].user_name);
                        alert(data[0].userName);
                    }
                });
            });
        });

        //此处会刷新页面 url也会改变
        $(document).ready(function () {
            $("#fileUploadSubmit1").submit(function (e) {
                //alert(${uploadFlag});
            });
        });
        //此处不刷新页面,url不变
        $(document).ready(function () {
            $("form#fileUploadSubmit").submit(function (event) {

                //disable the default form submission
                event.preventDefault();

                //grab all form data
                var formData = new FormData($(this)[0]);

                $.ajax({
                    url: '${app}/user/upload',
                    type: 'POST',
                    data: formData,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (returndata) {
                        alert(returndata);
                    }
                });

                return false;
            });
        });


    </script>

</head>

<body>
<div>
    <form id="form1">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>姓名:</td>
                <td><input name="userName"></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td>
                    <input name="age">
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td>
                    <input name="password">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" id="button_submit" value="提交"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div>
    <table>
        <tr>
            <td id="commonLayout_appcreshi">你在哪里呀</td>
        </tr>
    </table>
</div>
<div>
    <a href="${app}/user/testRequestParam1?userName=qink&age=108&password=test">测试RequestParam注解</a>
    <a href="${app}/user/testRequestParam1?userName=qink&age=108">测试RequestParam注解2</a>
</div>
<div>
    <form action="${app}/user/upload" method="post" enctype="multipart/form-data" id="fileUploadSubmit1">
        <input type="file" name="file"><br>
        <input type="submit" value="submit">
    </form>
</div>
<div>
    <form id="fileUploadSubmit">
        <input type="file" name="file"><br>
        <input type="submit" value="submit">
    </form>
</div>
<div>
    <form>
        <table>
            <tr>
                <td>modelMap测试</td>
                <td>${uploadFlag}</td>
            </tr>
            <tr>
                <td>modelMap测试</td>
                <td>${user.userName}</td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
