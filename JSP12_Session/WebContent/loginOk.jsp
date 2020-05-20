<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
%>    
<% 
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	String sessionName = "id";
	String sessionValue = id;
	
	// id / pw 일치하면  로그인 성공 + 쿠키 생성
	if(ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)){
		out.println("<script>");
		out.println("alert('로그인 성공');");
		out.println("</script>");
		
		session.setAttribute(sessionName, sessionValue);
		
		
	}else{
		out.println("<script>");
		out.println("alert('로그인 실패');");
		out.println("</script>");
		
		session.removeAttribute(sessionName);
	}
%>

<script>
location.href = "login.jsp";
</script>

