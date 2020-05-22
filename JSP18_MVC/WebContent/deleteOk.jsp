<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/> <%-- DAO bean 생성 --%>


<% // parameter 받아오기
	int uid = Integer.parseInt(request.getParameter("uid"));
	
%>

<% // dao 사용한 트랜잭션
	int cnt = (Integer)request.getAttribute("delete");
%>
<%-- 위 트랜잭션이 마무리 되면 페이지 보여주기 --%>

<% if(cnt == 0){ %>
<script>
	alert("삭제 실패");
	hisotry.back();
</script>
<% } else { %>
<script>
	alert("삭제 성공");
	location.href = "list.do";
</script>
<% } %>  
