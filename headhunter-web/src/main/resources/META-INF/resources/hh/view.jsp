<%@ include file="../init.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%
  String area = (String) renderRequest.getAttribute("area");
  String specialization = (String) renderRequest.getAttribute("specialization");
  String keywords = ParamUtil.getString(request, "keywords");
  List<Vacancy> vac = new ArrayList<Vacancy>();
%>


<p>Результаты поиска по параметрам: "регион" = <%= area %>, "профессиональная_область" = <%= specialization %> </p>

<liferay-ui:search-container emptyResultsMessage="ничего не найдено">

<liferay-ui:search-container-results />

<liferay-portlet:renderURL varImpl="searchURL">
        <portlet:param name="mvcPath"
        value="/hh/view.jsp" />
</liferay-portlet:renderURL>

  <aui:form action="<%= searchURL %>" method="get" name="fm">
    <liferay-portlet:renderURLParams varImpl="searchURL" />
    <div class="search-form">
            <span class="aui-search-bar">
                <aui:input inlineField="<%= true %>" label="" name="keywords"
                size="30" title="search-entries" type="text" />

                <aui:button type="submit" value="Поиск по ключевому слову" />
            </span>
        </div>
  </aui:form>

<%
vac = VacancyLocalServiceUtil.getVacancies(searchContainer.getStart(), searchContainer.getEnd());
 if ("".equals(keywords)) {
  total = VacancyLocalServiceUtil.getVacanciesCount();
  searchContainer.setResults(vac);
  } else {
  List<Vacancy> resultList = new ArrayList<Vacancy>();
      for(int i = 0; i < vac.size(); i++){
         if (vac.get(i).getVacancyName().toLowerCase().contains(keywords.toLowerCase())) {
           resultList.add(vac.get(i));
         }
      }
      total = resultList.size();
      searchContainer.setResults(resultList);
  }
 searchContainer.setTotal(total);

%>

<liferay-ui:search-container-row
    className="com.liferay.headhunter.model.Vacancy" modelVar="vacancy">

    <portlet:renderURL var="displayDataURL">
        <portlet:param name="mvcPath" value="/hh/displaydata.jsp"></portlet:param>
        <portlet:param name="vacancyName" value="<%= String.valueOf(vacancy.getVacancyName())%>"></portlet:param>
        <portlet:param name="employerName" value="<%= String.valueOf(vacancy.getEmployerName())%>"></portlet:param>
    </portlet:renderURL>

    <liferay-ui:search-container-column-text orderable="true" property="vacancyId" name="номер вакансии" />

    <liferay-ui:search-container-column-text orderable="true" property="vacancyName" name="вакансия"
    href="<%= displayDataURL.toString() %>" />

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
    <portlet:param name="mvcPath" value="/hh/view.jsp" />
</portlet:actionURL>

<aui:form action="<%= additionalLoadDataURL.toString() %>" name="<portlet:namespace />form">

	<aui:fieldset column="true">

		        <aui:select label="регион" name="area" id="area">
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
		<aui:button type="submit" value="Загрузить новые данные"></aui:button>
</aui:button-row>
</aui:form>
