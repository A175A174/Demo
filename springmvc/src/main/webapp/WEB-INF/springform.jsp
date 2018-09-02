<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="formTag" method="post">
    <table>
        <tr>
            <td>Name:</td><td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Age:</td><td><form:input path="age"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
