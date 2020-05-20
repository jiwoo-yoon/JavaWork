<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>include</title>
</head>
<body>
<!-- Action Tag -->
<p> 지금 현재 페이지는 include 페이지 입니다...</p>  <!--  과연 표시 될까? -->
<jsp:include page="sub.jsp"/>
<%-- <%@ include file="sub.jsp" %> --%> <%--결과는 똑같지만 내부적으로 동작이 살짝 다르다 --%>
<p> 위 라인의 내용은 sub 페이지 의 내용입니다 </p> <!--  과연 표시 될까? -->

<hr>
<!-- include 지시자 vs include page -->
<hr>
<!-- include directive -->
<p> 지금 현재 페이지는 include 페이지 입니다...</p>
<%@ include file = "sub.jsp" %>
<p> 위 라인의 내용은 sub 페이지의 내용입니다</p>

<hr>
<%!
	//변수선언
	String name = "홍길동";
	int age = 33;
%>
<%@ include file = "sub2.jsp" %>
<%-- <jsp:include page="sub2.jsp"/> 컴파일하지를 못한당--%>


<!-- 파라미터를 사용해서 넘겨주기  -->
<jsp:include page="sub3.jsp">
	<jsp:param value='<%= URLEncoder.encode(name, "utf-8") %>' name="name"/>
	<jsp:param value="<%= age %>" name="age"/>
</jsp:include>
</body>
</html>