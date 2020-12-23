<%@ include file="../init.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<p>Результаты поиска по параметрам: "регион" = "Новосибирск", "профессиональная область" = "Информационные технологии, интернет, телеком" </p>

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


