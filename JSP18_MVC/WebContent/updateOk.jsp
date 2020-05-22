<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% // parameter 받아오기
	int uid = Integer.parseInt(request.getParameter("uid"));
%>

<% // dao 사용한 트랜잭션
	int cnt = (Integer)request.getAttribute("update");
%>

<%-- 위 트랜잭션이 마무리 되면 페이지 보여주기 --%>
<% if(cnt == 0){ %>
<script>
	alert("수정 실패");
	hisotry.back();
</script>
<% } else { %>
<script>
	alert("수정 성공");
	location.href = "view.do?uid=<%= uid %>"; <%-- 수정 성공하면 view 로 이동하여 제대로 수정되었는지 보여주는게 좋다--%>
</script>
<% } %>  
