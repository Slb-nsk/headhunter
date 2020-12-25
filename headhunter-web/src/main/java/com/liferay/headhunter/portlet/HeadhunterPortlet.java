package com.liferay.headhunter.portlet;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.liferay.headhunter.constants.HeadhunterPortletKeys;

import com.liferay.headhunter.model.Vacancy;
import com.liferay.headhunter.service.VacancyLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @author тс
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=Headhunter",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/hh/view.jsp",
                "javax.portlet.name=" + HeadhunterPortletKeys.HEADHUNTER,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class HeadhunterPortlet extends MVCPortlet {
    List<AreaWithId> areas = new ArrayList<>();
    List<SpecializationWithId> specializations = new ArrayList<>();

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        renderRequest.setAttribute("area", ParamUtil.getString(renderRequest, "area"));
        renderRequest.setAttribute("specialization", ParamUtil.getString(renderRequest, "specialization"));
        renderRequest.setAttribute("areas", areas);
        renderRequest.setAttribute("specializations", specializations);
        super.render(renderRequest, renderResponse);
    }

    public void initialLoadData(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
        String areaUrl = "https://api.hh.ru/areas";
        String specializationUrl = "https://api.hh.ru/specializations";

        URL url = new URL(areaUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        try (JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            _readAreas(reader);
        }

        url = new URL(specializationUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        try (JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            _readSpecializations(reader);
        }

        actionResponse.getRenderParameters().setValue("area", "Новосибирск");
        actionResponse.getRenderParameters().setValue("specialization", "Информационные технологии, интернет, телеком");
        _loadData(4, 1);
    }

    public void additionalLoadData(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
        int area = ParamUtil.getInteger(actionRequest, "area");
        int specialization = ParamUtil.getInteger(actionRequest, "specialization");

        _loadData(area, specialization);

        for (AreaWithId a : areas) {
            if (a.getId() == area) {
                actionResponse.getRenderParameters().setValue("area", a.getName());
            }
        }
        for (SpecializationWithId s : specializations) {
            if (s.getId() == specialization) {
                actionResponse.getRenderParameters().setValue("specialization", s.getName());
            }
        }
    }

    private void _readAreas(JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            _readArea(reader);
        }
        reader.endArray();
    }

    private void _readArea(JsonReader reader) throws IOException {
        int areaId = 0;
        String areaName = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case ("id"):
                    areaId = reader.nextInt();
                    break;
                case ("name"):
                    areaName = reader.nextString();
                    break;
                case ("areas"):
                    _readAreas(reader);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        areas.add(new AreaWithId(areaId, areaName));
    }

    public class AreaWithId {
        int id;
        String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public AreaWithId() {
        }

        public AreaWithId(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private void _readSpecializations(JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            _readSpecialization(reader);
        }
        reader.endArray();
    }

    private void _readSpecialization(JsonReader reader) throws IOException {
        int specializationId = 0;
        String specializationName = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                specializationId = reader.nextInt();
            } else if (name.equals("name")) {
                specializationName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        specializations.add(new SpecializationWithId(specializationId, specializationName));
    }

    public class SpecializationWithId {
        int id;
        String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SpecializationWithId() {
        }

        public SpecializationWithId(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private void _loadData(int area, int specialization) throws IOException {
        String urlString = "https://api.hh.ru/vacancies?area=" + area + "&specialization=" + specialization;

        _vacancyLocalService.removeAll();

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        try (JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            _readCommonInfo(reader);
        }

    }

    private void _readCommonInfo(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("items")) {
                _readVacanciesList(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    private void _readVacanciesList(JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            _readVacancy(reader);
        }
        reader.endArray();
    }

    private void _readVacancy(JsonReader reader) throws IOException {
        long vacancyId = -1;
        String vacancyName = null;
        String employerName = null;
        String createdAt = null;
        Salary salary = new Salary(0, 0, false, "");

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case ("id"):
                    vacancyId = reader.nextLong();
                    break;
                case ("name"):
                    vacancyName = reader.nextString();
                    break;
                case ("employer"):
                    employerName = _readEmployer(reader);
                    break;
                case ("createdAt"):
                    createdAt = reader.nextString();
                    break;
                case ("salary"):
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                    } else {
                        salary = _readSalary(reader);
                    }
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        if (vacancyId > -1) {
            Vacancy newVacancy = _vacancyLocalService.createVacancy(vacancyId);
            newVacancy.setVacancyName(vacancyName);
            newVacancy.setEmployerName(employerName);
            newVacancy.setCreatedAt(createdAt);
            newVacancy.setSalaryFrom(salary.getSalaryFrom());
            newVacancy.setSalaryTo(salary.getSalaryTo());
            newVacancy.setSalaryCurrency(salary.getSalaryCurrency());
            newVacancy.setSalaryGross(salary.isSalaryGross());
            _vacancyLocalService.addVacancy(newVacancy);
        }

    }

    private Salary _readSalary(JsonReader reader) throws IOException {
        int salaryFrom = 0;
        int salaryTo = 0;
        boolean salaryGross = false;
        String salaryCurrency = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case ("from"):
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        salaryFrom = 0;
                    } else {
                        salaryFrom = reader.nextInt();
                    }
                    break;
                case ("to"):
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        salaryTo = 0;
                    } else {
                        salaryTo = reader.nextInt();
                    }
                    break;
                case ("currency"):
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        salaryCurrency = "";
                    } else {
                        salaryCurrency = reader.nextString();
                    }
                    break;
                case ("gross"):
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        salaryGross = false;
                    } else {
                        salaryGross = reader.nextBoolean();
                    }
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return new Salary(salaryFrom, salaryTo, salaryGross, salaryCurrency);
    }

    class Salary {
        int salaryFrom;
        int salaryTo;
        boolean salaryGross;
        String salaryCurrency;

        public int getSalaryFrom() {
            return salaryFrom;
        }

        public void setSalaryFrom(int salaryFrom) {
            this.salaryFrom = salaryFrom;
        }

        public int getSalaryTo() {
            return salaryTo;
        }

        public void setSalaryTo(int salaryTo) {
            this.salaryTo = salaryTo;
        }

        public boolean isSalaryGross() {
            return salaryGross;
        }

        public void setSalaryGross(boolean salaryGross) {
            this.salaryGross = salaryGross;
        }

        public String getSalaryCurrency() {
            return salaryCurrency;
        }

        public void setSalaryCurrency(String salaryCurrency) {
            this.salaryCurrency = salaryCurrency;
        }

        public Salary() {
        }

        public Salary(int salaryFrom, int salaryTo, boolean salaryGross, String salaryCurrency) {
            this.salaryFrom = salaryFrom;
            this.salaryTo = salaryTo;
            this.salaryGross = salaryGross;
            this.salaryCurrency = salaryCurrency;
        }
    }

    private String _readEmployer(JsonReader reader) throws IOException {
        String employerName = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                employerName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return employerName;
    }

    @Reference
    private VacancyLocalService _vacancyLocalService;

}
