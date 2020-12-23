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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Vacancy}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Vacancy
 * @generated
 */
public class VacancyWrapper
	extends BaseModelWrapper<Vacancy>
	implements ModelWrapper<Vacancy>, Vacancy {

	public VacancyWrapper(Vacancy vacancy) {
		super(vacancy);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("vacancyId", getVacancyId());
		attributes.put("vacancyName", getVacancyName());
		attributes.put("employerName", getEmployerName());
		attributes.put("createdAt", getCreatedAt());
		attributes.put("salaryFrom", getSalaryFrom());
		attributes.put("salaryTo", getSalaryTo());
		attributes.put("salaryGross", isSalaryGross());
		attributes.put("salaryCurrency", getSalaryCurrency());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long vacancyId = (Long)attributes.get("vacancyId");

		if (vacancyId != null) {
			setVacancyId(vacancyId);
		}

		String vacancyName = (String)attributes.get("vacancyName");

		if (vacancyName != null) {
			setVacancyName(vacancyName);
		}

		String employerName = (String)attributes.get("employerName");

		if (employerName != null) {
			setEmployerName(employerName);
		}

		String createdAt = (String)attributes.get("createdAt");

		if (createdAt != null) {
			setCreatedAt(createdAt);
		}

		Integer salaryFrom = (Integer)attributes.get("salaryFrom");

		if (salaryFrom != null) {
			setSalaryFrom(salaryFrom);
		}

		Integer salaryTo = (Integer)attributes.get("salaryTo");

		if (salaryTo != null) {
			setSalaryTo(salaryTo);
		}

		Boolean salaryGross = (Boolean)attributes.get("salaryGross");

		if (salaryGross != null) {
			setSalaryGross(salaryGross);
		}

		String salaryCurrency = (String)attributes.get("salaryCurrency");

		if (salaryCurrency != null) {
			setSalaryCurrency(salaryCurrency);
		}
	}

	/**
	 * Returns the created at of this vacancy.
	 *
	 * @return the created at of this vacancy
	 */
	@Override
	public String getCreatedAt() {
		return model.getCreatedAt();
	}

	/**
	 * Returns the employer name of this vacancy.
	 *
	 * @return the employer name of this vacancy
	 */
	@Override
	public String getEmployerName() {
		return model.getEmployerName();
	}

	/**
	 * Returns the primary key of this vacancy.
	 *
	 * @return the primary key of this vacancy
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the salary currency of this vacancy.
	 *
	 * @return the salary currency of this vacancy
	 */
	@Override
	public String getSalaryCurrency() {
		return model.getSalaryCurrency();
	}

	/**
	 * Returns the salary from of this vacancy.
	 *
	 * @return the salary from of this vacancy
	 */
	@Override
	public int getSalaryFrom() {
		return model.getSalaryFrom();
	}

	/**
	 * Returns the salary gross of this vacancy.
	 *
	 * @return the salary gross of this vacancy
	 */
	@Override
	public boolean getSalaryGross() {
		return model.getSalaryGross();
	}

	/**
	 * Returns the salary to of this vacancy.
	 *
	 * @return the salary to of this vacancy
	 */
	@Override
	public int getSalaryTo() {
		return model.getSalaryTo();
	}

	/**
	 * Returns the uuid of this vacancy.
	 *
	 * @return the uuid of this vacancy
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the vacancy ID of this vacancy.
	 *
	 * @return the vacancy ID of this vacancy
	 */
	@Override
	public long getVacancyId() {
		return model.getVacancyId();
	}

	/**
	 * Returns the vacancy name of this vacancy.
	 *
	 * @return the vacancy name of this vacancy
	 */
	@Override
	public String getVacancyName() {
		return model.getVacancyName();
	}

	/**
	 * Returns <code>true</code> if this vacancy is salary gross.
	 *
	 * @return <code>true</code> if this vacancy is salary gross; <code>false</code> otherwise
	 */
	@Override
	public boolean isSalaryGross() {
		return model.isSalaryGross();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the created at of this vacancy.
	 *
	 * @param createdAt the created at of this vacancy
	 */
	@Override
	public void setCreatedAt(String createdAt) {
		model.setCreatedAt(createdAt);
	}

	/**
	 * Sets the employer name of this vacancy.
	 *
	 * @param employerName the employer name of this vacancy
	 */
	@Override
	public void setEmployerName(String employerName) {
		model.setEmployerName(employerName);
	}

	/**
	 * Sets the primary key of this vacancy.
	 *
	 * @param primaryKey the primary key of this vacancy
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the salary currency of this vacancy.
	 *
	 * @param salaryCurrency the salary currency of this vacancy
	 */
	@Override
	public void setSalaryCurrency(String salaryCurrency) {
		model.setSalaryCurrency(salaryCurrency);
	}

	/**
	 * Sets the salary from of this vacancy.
	 *
	 * @param salaryFrom the salary from of this vacancy
	 */
	@Override
	public void setSalaryFrom(int salaryFrom) {
		model.setSalaryFrom(salaryFrom);
	}

	/**
	 * Sets whether this vacancy is salary gross.
	 *
	 * @param salaryGross the salary gross of this vacancy
	 */
	@Override
	public void setSalaryGross(boolean salaryGross) {
		model.setSalaryGross(salaryGross);
	}

	/**
	 * Sets the salary to of this vacancy.
	 *
	 * @param salaryTo the salary to of this vacancy
	 */
	@Override
	public void setSalaryTo(int salaryTo) {
		model.setSalaryTo(salaryTo);
	}

	/**
	 * Sets the uuid of this vacancy.
	 *
	 * @param uuid the uuid of this vacancy
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the vacancy ID of this vacancy.
	 *
	 * @param vacancyId the vacancy ID of this vacancy
	 */
	@Override
	public void setVacancyId(long vacancyId) {
		model.setVacancyId(vacancyId);
	}

	/**
	 * Sets the vacancy name of this vacancy.
	 *
	 * @param vacancyName the vacancy name of this vacancy
	 */
	@Override
	public void setVacancyName(String vacancyName) {
		model.setVacancyName(vacancyName);
	}

	@Override
	protected VacancyWrapper wrap(Vacancy vacancy) {
		return new VacancyWrapper(vacancy);
	}

}