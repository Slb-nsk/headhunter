package com.liferay.headhunter.portlet;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.liferay.headhunter.constants.HeadhunterPortletKeys;

import com.liferay.headhunter.model.Vacancy;
import com.liferay.headhunter.service.VacancyLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


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

    public void initialLoadData(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
        _loadData(4,1);
    }

    private void _loadData(int area, int specialization) throws IOException {
        String urlString = "https://api.hh.ru/vacancies?area=" + area + "&specialization=" + specialization;

        _vacancyLocalService.removeAll();

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        try {
            _readCommonInfo(reader);
        } finally {
            reader.close();
        }

    }

    private void _readCommonInfo(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("items")) {
                _readVacansiesList(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    private void _readVacansiesList(JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            _readVacansy(reader);
        }
        reader.endArray();
    }

    private void _readVacansy(JsonReader reader) throws IOException {
        long vacancyId = -1;
        String vacancyName = null;
        String employerName = null;
        String createdAt = null;
        Salary salary = new Salary(0, 0, false, "");

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                vacancyId = reader.nextLong();
            } else if (name.equals("name")) {
                vacancyName = reader.nextString();
            } else if (name.equals("employer")) {
                employerName = _readEmployer(reader);
            } else if (name.equals("createdAt")) {
                createdAt = reader.nextString();
            } else if (name.equals("salary")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                } else {
                    salary = _readSalary(reader);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (vacancyId > -1) {
            Vacancy newVacansy = _vacancyLocalService.createVacancy(vacancyId);
            newVacansy.setVacancyName(vacancyName);
            newVacansy.setEmployerName(employerName);
            newVacansy.setCreatedAt(createdAt);
            newVacansy.setSalaryFrom(salary.getSalaryFrom());
            newVacansy.setSalaryTo(salary.getSalaryTo());
            newVacansy.setSalaryCurrency(salary.getSalaryCurrency());
            newVacansy.setSalaryGross(salary.isSalaryGross());
            _vacancyLocalService.addVacancy(newVacansy);
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
            if (name.equals("from")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    salaryFrom = 0;
                } else {
                    salaryFrom = reader.nextInt();
                }
            } else if (name.equals("to")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    salaryTo = 0;
                } else {
                    salaryTo = reader.nextInt();
                }
            } else if (name.equals("currency")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    salaryCurrency = "";
                } else {
                    salaryCurrency = reader.nextString();
                }
            } else if (name.equals("gross")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    salaryGross = false;
                } else {
                    salaryGross = reader.nextBoolean();
                }
            } else {
                reader.skipValue();
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
