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

package com.liferay.headhunter.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VacancyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see VacancyLocalService
 * @generated
 */
public class VacancyLocalServiceWrapper
	implements ServiceWrapper<VacancyLocalService>, VacancyLocalService {

	public VacancyLocalServiceWrapper(VacancyLocalService vacancyLocalService) {
		_vacancyLocalService = vacancyLocalService;
	}

	/**
	 * Adds the vacancy to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VacancyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vacancy the vacancy
	 * @return the vacancy that was added
	 */
	@Override
	public com.liferay.headhunter.model.Vacancy addVacancy(
		com.liferay.headhunter.model.Vacancy vacancy) {

		return _vacancyLocalService.addVacancy(vacancy);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vacancyLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new vacancy with the primary key. Does not add the vacancy to the database.
	 *
	 * @param vacancyId the primary key for the new vacancy
	 * @return the new vacancy
	 */
	@Override
	public com.liferay.headhunter.model.Vacancy createVacancy(long vacancyId) {
		return _vacancyLocalService.createVacancy(vacancyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vacancyLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the vacancy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VacancyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy that was removed
	 * @throws PortalException if a vacancy with the primary key could not be found
	 */
	@Override
	public com.liferay.headhunter.model.Vacancy deleteVacancy(long vacancyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vacancyLocalService.deleteVacancy(vacancyId);
	}

	/**
	 * Deletes the vacancy from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VacancyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vacancy the vacancy
	 * @return the vacancy that was removed
	 */
	@Override
	public com.liferay.headhunter.model.Vacancy deleteVacancy(
		com.liferay.headhunter.model.Vacancy vacancy) {

		return _vacancyLocalService.deleteVacancy(vacancy);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vacancyLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _vacancyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.headhunter.model.impl.VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _vacancyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.headhunter.model.impl.VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _vacancyLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _vacancyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _vacancyLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.headhunter.model.Vacancy fetchVacancy(long vacancyId) {
		return _vacancyLocalService.fetchVacancy(vacancyId);
	}

	@Override
	public java.util.List<com.liferay.headhunter.model.Vacancy> findAll() {
		return _vacancyLocalService.findAll();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _vacancyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _vacancyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _vacancyLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vacancyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the vacancies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.headhunter.model.impl.VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of vacancies
	 * @param end the upper bound of the range of vacancies (not inclusive)
	 * @return the range of vacancies
	 */
	@Override
	public java.util.List<com.liferay.headhunter.model.Vacancy> getVacancies(
		int start, int end) {

		return _vacancyLocalService.getVacancies(start, end);
	}

	/**
	 * Returns the number of vacancies.
	 *
	 * @return the number of vacancies
	 */
	@Override
	public int getVacanciesCount() {
		return _vacancyLocalService.getVacanciesCount();
	}

	/**
	 * Returns the vacancy with the primary key.
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy
	 * @throws PortalException if a vacancy with the primary key could not be found
	 */
	@Override
	public com.liferay.headhunter.model.Vacancy getVacancy(long vacancyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vacancyLocalService.getVacancy(vacancyId);
	}

	@Override
	public void removeAll() {
		_vacancyLocalService.removeAll();
	}

	/**
	 * Updates the vacancy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect VacancyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param vacancy the vacancy
	 * @return the vacancy that was updated
	 */
	@Override
	public com.liferay.headhunter.model.Vacancy updateVacancy(
		com.liferay.headhunter.model.Vacancy vacancy) {

		return _vacancyLocalService.updateVacancy(vacancy);
	}

	@Override
	public VacancyLocalService getWrappedService() {
		return _vacancyLocalService;
	}

	@Override
	public void setWrappedService(VacancyLocalService vacancyLocalService) {
		_vacancyLocalService = vacancyLocalService;
	}

	private VacancyLocalService _vacancyLocalService;

}