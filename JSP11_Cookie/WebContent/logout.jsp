<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cookieName = "id";
	String cookieValue = "";
	Cookie cookie = new Cookie(cookieName, cookieValue);
	cookie.setMaxAge(0);		// 쿠키 제거!
	response.addCookie(cookie);   // response 에 쿠키 정보 추가
%>

<script>
alert("로그아웃 되었습니다");
location.href = "login.jsp";
</script>


