<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored ="false"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div class="studentlist">

	<table class="table table-hover table-bordered">
			<tr>
				<th><input type="checkbox" id="checkedAll"/></th>
				<th>序号</th>
				<th>学生ID</th>
				<th width="60px">姓名</th>
				<th width="100px">出生日期</th>
				<th width="120px">备注</th>
				<th width="80px">平均数</th>&nbsp
				<th width="110px">操作</th>
				<th width="80px"><button class="btn" type="button" onclick="javascript:window.location.href='student?action=preadd'">添加</button>&nbsp;</th>
			</tr>
			 <c:forEach items="${studentList }" var="sl" varStatus="status">
				<tr>
					<td><input type="checkbox" name="is" value="${sl.id}"/></td>
					<td align="center">${status.index+1 }</td>
					<td align="center"><c:out value="${sl.id}"/></td>
					<td align="center"><c:out value="${sl.name}"/></td>
					<td align="center"><c:out value="${sl.birthday}"/></td>
					<td align="center">${fn:substring(sl.description, 0, 5)}</td>
					<td align="center"><c:out value="${sl.score}"/></td>
					<td>
						<button class="btn" type="button" onclick="javascript:window.location.href='student?action=preadd&id=${sl.id}&score=${sl.score}'">修改</button>&nbsp;
						<button class="btn" type="button" onclick="javascript:window.location.href='${pageContext.request.contextPath }/student?action=rem&data=${sl.id },${sl.name },${sl.birthday },${sl.description }'">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
</div>
<div>
	<a href="${pageContext.request.contextPath} /student?action=list&currentPage=${currentPage}"></a>
</div>
</body>
</html>