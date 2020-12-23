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

package com.liferay.headhunter.service.persistence;

import com.liferay.headhunter.model.Vacancy;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the vacancy service. This utility wraps <code>com.liferay.headhunter.service.persistence.impl.VacancyPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VacancyPersistence
 * @generated
 */
public class VacancyUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Vacancy vacancy) {
		getPersistence().clearCache(vacancy);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Vacancy> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Vacancy> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Vacancy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Vacancy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Vacancy> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Vacancy update(Vacancy vacancy) {
		return getPersistence().update(vacancy);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Vacancy update(
		Vacancy vacancy, ServiceContext serviceContext) {

		return getPersistence().update(vacancy, serviceContext);
	}

	/**
	 * Returns all the vacancies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching vacancies
	 */
	public static List<Vacancy> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the vacancies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of vacancies
	 * @param end the upper bound of the range of vacancies (not inclusive)
	 * @return the range of matching vacancies
	 */
	public static List<Vacancy> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the vacancies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of vacancies
	 * @param end the upper bound of the range of vacancies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vacancies
	 */
	public static List<Vacancy> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Vacancy> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the vacancies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of vacancies
	 * @param end the upper bound of the range of vacancies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching vacancies
	 */
	public static List<Vacancy> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Vacancy> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vacancy
	 * @throws NoSuchVacancyException if a matching vacancy could not be found
	 */
	public static Vacancy findByUuid_First(
			String uuid, OrderByComparator<Vacancy> orderByComparator)
		throws com.liferay.headhunter.exception.NoSuchVacancyException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vacancy, or <code>null</code> if a matching vacancy could not be found
	 */
	public static Vacancy fetchByUuid_First(
		String uuid, OrderByComparator<Vacancy> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vacancy
	 * @throws NoSuchVacancyException if a matching vacancy could not be found
	 */
	public static Vacancy findByUuid_Last(
			String uuid, OrderByComparator<Vacancy> orderByComparator)
		throws com.liferay.headhunter.exception.NoSuchVacancyException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vacancy, or <code>null</code> if a matching vacancy could not be found
	 */
	public static Vacancy fetchByUuid_Last(
		String uuid, OrderByComparator<Vacancy> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the vacancies before and after the current vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param vacancyId the primary key of the current vacancy
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vacancy
	 * @throws NoSuchVacancyException if a vacancy with the primary key could not be found
	 */
	public static Vacancy[] findByUuid_PrevAndNext(
			long vacancyId, String uuid,
			OrderByComparator<Vacancy> orderByComparator)
		throws com.liferay.headhunter.exception.NoSuchVacancyException {

		return getPersistence().findByUuid_PrevAndNext(
			vacancyId, uuid, orderByComparator);
	}

	/**
	 * Removes all the vacancies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of vacancies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching vacancies
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Caches the vacancy in the entity cache if it is enabled.
	 *
	 * @param vacancy the vacancy
	 */
	public static void cacheResult(Vacancy vacancy) {
		getPersistence().cacheResult(vacancy);
	}

	/**
	 * Caches the vacancies in the entity cache if it is enabled.
	 *
	 * @param vacancies the vacancies
	 */
	public static void cacheResult(List<Vacancy> vacancies) {
		getPersistence().cacheResult(vacancies);
	}

	/**
	 * Creates a new vacancy with the primary key. Does not add the vacancy to the database.
	 *
	 * @param vacancyId the primary key for the new vacancy
	 * @return the new vacancy
	 */
	public static Vacancy create(long vacancyId) {
		return getPersistence().create(vacancyId);
	}

	/**
	 * Removes the vacancy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy that was removed
	 * @throws NoSuchVacancyException if a vacancy with the primary key could not be found
	 */
	public static Vacancy remove(long vacancyId)
		throws com.liferay.headhunter.exception.NoSuchVacancyException {

		return getPersistence().remove(vacancyId);
	}

	public static Vacancy updateImpl(Vacancy vacancy) {
		return getPersistence().updateImpl(vacancy);
	}

	/**
	 * Returns the vacancy with the primary key or throws a <code>NoSuchVacancyException</code> if it could not be found.
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy
	 * @throws NoSuchVacancyException if a vacancy with the primary key could not be found
	 */
	public static Vacancy findByPrimaryKey(long vacancyId)
		throws com.liferay.headhunter.exception.NoSuchVacancyException {

		return getPersistence().findByPrimaryKey(vacancyId);
	}

	/**
	 * Returns the vacancy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy, or <code>null</code> if a vacancy with the primary key could not be found
	 */
	public static Vacancy fetchByPrimaryKey(long vacancyId) {
		return getPersistence().fetchByPrimaryKey(vacancyId);
	}

	/**
	 * Returns all the vacancies.
	 *
	 * @return the vacancies
	 */
	public static List<Vacancy> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the vacancies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of vacancies
	 * @param end the upper bound of the range of vacancies (not inclusive)
	 * @return the range of vacancies
	 */
	public static List<Vacancy> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the vacancies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of vacancies
	 * @param end the upper bound of the range of vacancies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vacancies
	 */
	public static List<Vacancy> findAll(
		int start, int end, OrderByComparator<Vacancy> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the vacancies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VacancyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of vacancies
	 * @param end the upper bound of the range of vacancies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of vacancies
	 */
	public static List<Vacancy> findAll(
		int start, int end, OrderByComparator<Vacancy> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the vacancies from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of vacancies.
	 *
	 * @return the number of vacancies
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static VacancyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VacancyPersistence, VacancyPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VacancyPersistence.class);

		ServiceTracker<VacancyPersistence, VacancyPersistence> serviceTracker =
			new ServiceTracker<VacancyPersistence, VacancyPersistence>(
				bundle.getBundleContext(), VacancyPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}