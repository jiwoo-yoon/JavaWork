<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="com.lec.beans.WriteDTO" scope="page" />
<jsp:setProperty name="member" property="name" value="홍길동"/>
<jsp:setProperty name="member" property="uid" value="123"/>
<jsp:setProperty name="member" property="content" value="abc"/>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름 : <%= member.getName() %><br>
	아이디 : <%= member.getUid() %><br>
	비밀번호 : <%= member.getContent() %><br>
	<hr>

	이름 : <jsp:getProperty name="member" property="name"/><br>
	아이디 : <jsp:getProperty name="member" property="uid"/><br>
	비밀번호 : <jsp:getProperty name="member" property="content"/><br>
	<hr>
	
	이름 : ${ member.name }<br>
	아이디 : ${ member.uid }<br>
	비밀번호 : ${ member.content }<br>

</body>
</html>