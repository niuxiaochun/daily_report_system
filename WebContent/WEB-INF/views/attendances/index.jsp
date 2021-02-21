<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>${login_employee.name}さんの勤怠情報</h2>




        <table id="attendance_list">
            <tbody>
                <tr>
                    <th class="attendance_name">氏名</th>
                    <th class="attendance_in">出勤時間</th>
                    <th class="attendance_out">退勤時間</th>
                </tr>
                <c:forEach var="attendance" items="${attendances}"
                    varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="attendance_name"><c:out value="${attendance.employee.name}" /></td>
                        <td class="attendance_in">${attendance.in_time}</td>
                        <td class="attendance_out">${attendance.out_time}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${attendances_count} 件）<br />
            <c:forEach var="i" begin="1"
                end="${((attendances_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/attendances/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <form method="POST" action="<c:url value='/attendances/in_time' />">
            <button type="submit">出勤する</button>
        </form>

        <form method="POST" action="<c:url value='/attendances/out_time' />">
            <button type="submit">退勤する</button>
        </form>
    </c:param>
</c:import>