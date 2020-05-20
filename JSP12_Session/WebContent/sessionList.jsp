<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Session List</title>
</head>
<body>
<%
	if(request.isRequestedSessionIdValid()){
		out.println("유효한 세션이 있습니다");
	}else{
		out.println("유효한 세션이 없습니다");
	}

	String sessionName, sessionValue;
	Enumeration<String> enumeration = session.getAttributeNames();
	
	int i = 0;
	while(enumeration.hasMoreElements()){
		sessionName = enumeration.nextElement();
		sessionValue = session.getAttribute(sessionName).toString();
		out.println((i + 1) + "] " + sessionName + " : " + sessionValue + "<br>");
		i++;
	}
	if(i == 0){
		out.println("세션이 없습니다 <br>");
	}
%>

<br>
<form action="sessionCreate.jsp" method="get">
<input type="submit" value="세선생성">
</form>


<br>
<form action="sessionDelete.jsp" method="get">
<input type="submit" value="세션삭제">
</form>

<br>
<%
	String sessionId = session.getId();
	int sessionInterval = session.getMaxInactiveInterval();
	
	out.println("세션 ID: " + sessionId + "<br>");
	out.println("세션 유효시간: " + sessionInterval + "<br>");
%>
<br><br>

<%
	out.println("--- session.invalidate() 후 ---<br>");
	//세션 무효화 invalidate
	//기존 세션테이블 삭제(session id 무효화)
	//	- 그 안의 모든 attribute(이름)도 삭제
	//새로운 세션테이블 생성
	//정말 특수한 경우에만 씀
	//session.invalidate();
	
	sessionId = session.getId();
	sessionInterval = session.getMaxInactiveInterval();
	
	out.println("세션 ID: " + sessionId + "<br>");
	out.println("세션 유효시간: " + sessionInterval + "<br>");	
	// '새로고침 하면서'  어떻게 바뀌는지 보자
	
	if(request.isRequestedSessionIdValid()){
    	out.println("유효한 세션 있습니다...");    	
    }else{
    	out.println("유효한 세션이 없습니다...");
    }
%>


</body>
</html>