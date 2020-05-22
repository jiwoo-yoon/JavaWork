<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/> <%-- DAO bean 생성 --%>


<% // parameter 받아오기
	request.setCharacterEncoding("utf-8");  // 한글 인코딩 받아올때 꼭!
	int uid = Integer.parseInt(request.getParameter("uid"));
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	// ※ 사실 이단계에서 parameter 검증 필요
%>

<% // dao 사용한 트랜잭션
	int cnt = dao.update(uid, subject, content);
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
	location.href = "view.jsp?uid=<%= uid %>"; <%-- 수정 성공하면 view 로 이동하여 제대로 수정되었는지 보여주는게 좋다--%>
</script>
<% } %>  
