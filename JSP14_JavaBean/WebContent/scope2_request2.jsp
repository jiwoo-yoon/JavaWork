<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="cnt" class="com.lec.beans.CountBean" scope="request"/>

<b>cnt 의 getCount 호출</b><br>

<jsp:getProperty name="cnt" property="count"/><br>

<a href="scope2_request1.jsp">request1 으로..</a>

