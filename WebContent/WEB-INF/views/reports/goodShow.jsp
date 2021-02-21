<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>いいね 一覧</h2>
        <table id="good_list">
            <tbody>
                <tr>
                    <th class="employee_name">氏名</th>
                    <th class="good_date">日付</th>
                </tr>
                <c:forEach var="gooddetails" items="${gooddetails}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="employeet_name"><c:out value="${gooddetails.employee.name}" /></td>
                        <td class="good_date"><fmt:formatDate value='${gooddetails.created_at}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${good_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((gooddetails_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value="/reports/index" />">日報一覧へ</a></p>
    </c:param>
</c:import>