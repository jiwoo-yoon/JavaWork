<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
%>    
<% 
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	String cookieName = "id";
	String cookieValue = id;
	
	// id / pw 일치하면  로그인 성공 + 쿠키 생성
	if(ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)){
		out.println("<script>");
		out.println("alert('로그인 성공');");
		out.println("</script>");
		
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(2 * 30);  // 쿠키 파기(expiry) 시간 설정 (생성시점으로부터 2 * 30 초 후)
		response.addCookie(cookie);   // response 에 쿠키 정보 추가
		
	}else{
		out.println("<script>");
		out.println("alert('로그인 실패');");
		out.println("</script>");
		
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(0);		// 기존에 있었더라도 죽인다
		response.addCookie(cookie);   // response 에 쿠키 정보 추가
	}
%>

<script>
location.href = "login.jsp";
</script>

