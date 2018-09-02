<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <fmt:bundle basename="i18n">
        <fmt:message key="i18n.username"></fmt:message>
        <br/>
        <fmt:message key="i18n.password"></fmt:message>
    </fmt:bundle>

<br/>
<form:errors path="*"/>
<form action="/world30" method="post">
    <input type="text" name="user">
    <input type="submit" name="提交">
</form>
</body>
<form action="/up" method="post"  enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
</html>
