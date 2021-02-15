<%@ include file="../init.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%
String vacancyName = ParamUtil.getString(renderRequest, "vacancyName");
String employerName = ParamUtil.getString(renderRequest, "employerName");
%>

<p>Вакансия "<%= vacancyName %>" от работодателя "<%= employerName%>"</p>