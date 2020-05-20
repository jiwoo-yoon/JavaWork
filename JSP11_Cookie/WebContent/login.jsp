<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login 페이지</title>
</head>
<body>
	<%!
		int i = 0;
	%>
	<%
		Cookie[] cookies = request.getCookies(); // Cookie[] 배열을 리턴
		String cookieName = "id";

		if (cookies != null) { // 만약 쿠키가 하나도 없다면 null 이 리턴된다
			for (i = 0; i < cookies.length; i++) {
				if (cookieName.equals(cookies[i].getName())) {
	%>
		<h2>로그인 상태입니다 </h2>
		<form action="logout.jsp">
			<input type="submit" value="로그아웃"><br>
		</form>
	<%
					break;  // 쿠키 있으면 종료
				} // end if
			} // end for
		} // end if
	%>
	
	
	<%if(cookies == null || i == cookies.length){%>
		<h2>로그인 상태가 아닙니다</h2>
		<form action="loginOk.jsp">
			id: <input type="text" name="id"><br>
			pw: <input type="password" name="pw"><br>
			<input type="submit" value="로그인"><br>
		</form>		
	<%}%>
</body>
</html>

