/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headhunter.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class VacancySoap implements Serializable {

	public static VacancySoap toSoapModel(Vacancy model) {
		VacancySoap soapModel = new VacancySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setVacancyId(model.getVacancyId());
		soapModel.setVacancyName(model.getVacancyName());
		soapModel.setEmployerName(model.getEmployerName());
		soapModel.setCreatedAt(model.getCreatedAt());
		soapModel.setSalaryFrom(model.getSalaryFrom());
		soapModel.setSalaryTo(model.getSalaryTo());
		soapModel.setSalaryGross(model.isSalaryGross());
		soapModel.setSalaryCurrency(model.getSalaryCurrency());

		return soapModel;
	}

	public static VacancySoap[] toSoapModels(Vacancy[] models) {
		VacancySoap[] soapModels = new VacancySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VacancySoap[][] toSoapModels(Vacancy[][] models) {
		VacancySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VacancySoap[models.length][models[0].length];
		}
		else {
			soapModels = new VacancySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VacancySoap[] toSoapModels(List<Vacancy> models) {
		List<VacancySoap> soapModels = new ArrayList<VacancySoap>(
			models.size());

		for (Vacancy model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VacancySoap[soapModels.size()]);
	}

	public VacancySoap() {
	}

	public long getPrimaryKey() {
		return _vacancyId;
	}

	public void setPrimaryKey(long pk) {
		setVacancyId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getVacancyId() {
		return _vacancyId;
	}

	public void setVacancyId(long vacancyId) {
		_vacancyId = vacancyId;
	}

	public String getVacancyName() {
		return _vacancyName;
	}

	public void setVacancyName(String vacancyName) {
		_vacancyName = vacancyName;
	}

	public String getEmployerName() {
		return _employerName;
	}

	public void setEmployerName(String employerName) {
		_employerName = employerName;
	}

	public String getCreatedAt() {
		return _createdAt;
	}

	public void setCreatedAt(String createdAt) {
		_createdAt = createdAt;
	}

	public int getSalaryFrom() {
		return _salaryFrom;
	}

	public void setSalaryFrom(int salaryFrom) {
		_salaryFrom = salaryFrom;
	}

	public int getSalaryTo() {
		return _salaryTo;
	}

	public void setSalaryTo(int salaryTo) {
		_salaryTo = salaryTo;
	}

	public boolean getSalaryGross() {
		return _salaryGross;
	}

	public boolean isSalaryGross() {
		return _salaryGross;
	}

	public void setSalaryGross(boolean salaryGross) {
		_salaryGross = salaryGross;
	}

	public String getSalaryCurrency() {
		return _salaryCurrency;
	}

	public void setSalaryCurrency(String salaryCurrency) {
		_salaryCurrency = salaryCurrency;
	}

	private String _uuid;
	private long _vacancyId;
	private String _vacancyName;
	private String _employerName;
	private String _createdAt;
	private int _salaryFrom;
	private int _salaryTo;
	private boolean _salaryGross;
	private String _salaryCurrency;

}