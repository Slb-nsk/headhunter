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

package com.liferay.headhunter.model.impl;

import com.liferay.headhunter.model.Vacancy;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Vacancy in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class VacancyCacheModel implements CacheModel<Vacancy>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof VacancyCacheModel)) {
			return false;
		}

		VacancyCacheModel vacancyCacheModel = (VacancyCacheModel)object;

		if (vacancyId == vacancyCacheModel.vacancyId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, vacancyId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", vacancyId=");
		sb.append(vacancyId);
		sb.append(", vacancyName=");
		sb.append(vacancyName);
		sb.append(", employerName=");
		sb.append(employerName);
		sb.append(", createdAt=");
		sb.append(createdAt);
		sb.append(", salaryFrom=");
		sb.append(salaryFrom);
		sb.append(", salaryTo=");
		sb.append(salaryTo);
		sb.append(", salaryGross=");
		sb.append(salaryGross);
		sb.append(", salaryCurrency=");
		sb.append(salaryCurrency);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Vacancy toEntityModel() {
		VacancyImpl vacancyImpl = new VacancyImpl();

		if (uuid == null) {
			vacancyImpl.setUuid("");
		}
		else {
			vacancyImpl.setUuid(uuid);
		}

		vacancyImpl.setVacancyId(vacancyId);

		if (vacancyName == null) {
			vacancyImpl.setVacancyName("");
		}
		else {
			vacancyImpl.setVacancyName(vacancyName);
		}

		if (employerName == null) {
			vacancyImpl.setEmployerName("");
		}
		else {
			vacancyImpl.setEmployerName(employerName);
		}

		if (createdAt == null) {
			vacancyImpl.setCreatedAt("");
		}
		else {
			vacancyImpl.setCreatedAt(createdAt);
		}

		vacancyImpl.setSalaryFrom(salaryFrom);
		vacancyImpl.setSalaryTo(salaryTo);
		vacancyImpl.setSalaryGross(salaryGross);

		if (salaryCurrency == null) {
			vacancyImpl.setSalaryCurrency("");
		}
		else {
			vacancyImpl.setSalaryCurrency(salaryCurrency);
		}

		vacancyImpl.resetOriginalValues();

		return vacancyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		vacancyId = objectInput.readLong();
		vacancyName = objectInput.readUTF();
		employerName = objectInput.readUTF();
		createdAt = objectInput.readUTF();

		salaryFrom = objectInput.readInt();

		salaryTo = objectInput.readInt();

		salaryGross = objectInput.readBoolean();
		salaryCurrency = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(vacancyId);

		if (vacancyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(vacancyName);
		}

		if (employerName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(employerName);
		}

		if (createdAt == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(createdAt);
		}

		objectOutput.writeInt(salaryFrom);

		objectOutput.writeInt(salaryTo);

		objectOutput.writeBoolean(salaryGross);

		if (salaryCurrency == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(salaryCurrency);
		}
	}

	public String uuid;
	public long vacancyId;
	public String vacancyName;
	public String employerName;
	public String createdAt;
	public int salaryFrom;
	public int salaryTo;
	public boolean salaryGross;
	public String salaryCurrency;

}