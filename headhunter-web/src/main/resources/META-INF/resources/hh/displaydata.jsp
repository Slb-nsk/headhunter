<%@ include file="../init.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%
  String area = (String) renderRequest.getAttribute("area");
  String specialization = (String) renderRequest.getAttribute("specialization");
%>

<p>Результаты поиска по параметрам: "регион" = <%= area %>, "профессиональная_область" = <%= specialization %> </p>

<liferay-ui:search-container total="<%=VacancyLocalServiceUtil.getVacanciesCount()%>">
<liferay-ui:search-container-results
    results="<%=VacancyLocalServiceUtil.getVacancies(searchContainer.getStart(), searchContainer.getEnd())%>" />

<liferay-ui:search-container-row
    className="com.liferay.headhunter.model.Vacancy" modelVar="vacancy">

    <liferay-ui:search-container-column-text orderable="true" property="vacancyId" name="номер вакансии" />

    <liferay-ui:search-container-column-text orderable="true" property="vacancyName" name="вакансия" />

    <liferay-ui:search-container-column-text orderable="true" property="employerName" name="работодатель" />

    <liferay-ui:search-container-column-text orderable="true" property="createdAt" name="дата создания" />

    <liferay-ui:search-container-column-text orderable="true" property="salaryFrom" name="зарплата от"/>

    <liferay-ui:search-container-column-text orderable="true" property="salaryTo" name="зарплата до" />

    <liferay-ui:search-container-column-text orderable="true" property="salaryGross" name="вычтен ли налог" />

    <liferay-ui:search-container-column-text orderable="true" property="salaryCurrency" name="валюта" />


</liferay-ui:search-container-row>


<liferay-ui:search-iterator />
</liferay-ui:search-container>


<p>Если хотите сделать поиск с другими параметрами - задайте эти параметры:</p>

<portlet:actionURL name="additionalLoadData" var="additionalLoadDataURL">
    <portlet:param name="mvcPath" value="/hh/displaydata.jsp" />
</portlet:actionURL>

<aui:form action="<%= additionalLoadDataURL %>" name="<portlet:namespace />fm">

	<aui:fieldset column="true">

		        <aui:select label="регион" name="area">
                    <c:forEach var="a" items="${areas}">
                       <aui:option value="${a.id}">${a.name}</aui:option>
                    </c:forEach>
                </aui:select>

                <aui:select label="профессиональная область" name="specialization">
                     <c:forEach var="s" items="${specializations}">
                        <aui:option value="${s.id}">${s.name}</aui:option>
                     </c:forEach>
                </aui:select>

	</aui:fieldset>

<aui:button-row>
		<aui:button type="submit" onClick="<%= additionalLoadDataURL %>" value="Загрузить новые данные"></aui:button>
</aui:button-row>
</aui:form>