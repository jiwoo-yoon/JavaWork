<%--
  Created by IntelliJ IDEA.
  User: yjiwo
  Date: 2020-07-02
  Time: 오후 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    alert("로그아웃 됨")
    location.href = "${pageContext.request.contextPath}/board/list.do";
</script>
