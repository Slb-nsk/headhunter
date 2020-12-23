<%@ include file="../init.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<portlet:actionURL name="initialLoadData" var="initialLoadDataURL">
    <portlet:param name="mvcPath" value="/hh/displaydata.jsp" />
</portlet:actionURL>

<aui:button-row>
    <aui:button onClick="<%= initialLoadDataURL %>" value="Загрузить данные"></aui:button>
</aui:button-row>



