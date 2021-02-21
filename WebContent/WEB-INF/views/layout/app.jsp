<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>日報管理システム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<link rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1>
                    <a href="<c:url value='/' />">日報管理システム</a>
                </h1>
                &nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_employee !=null}">
                    <c:if test="${sessionScope.login_employee.admin_flag == 1}">
                        <a href="<c:url value='/employees/index' />"><i
                            class="fas fa-users"></i> 従業員管理</a>&nbsp;&nbsp;&nbsp;
                    </c:if>
                    <a href="<c:url value='/reports/index' />"><i
                        class="fas fa-clipboard-list"></i> 日報管理</a>&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='/attendances/index' />"><i
                        class="fas fa-window-restore"></i> 勤怠管理</a>&nbsp;&nbsp;&nbsp;
                </c:if>
            </div>
            <c:if test="${sessionScope.login_employee != null}">
                <div id="employee_name">
                    <i class="fas fa-user-circle"></i>
                    <c:out value="${sessionScope.login_employee.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp; <a href="<c:url value='/logout' />">ログアウト</a>
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by shaochun.nyuu</div>
    </div>
</body>
</html>