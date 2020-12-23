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

package com.liferay.headhunter.service.persistence.impl;

import com.liferay.headhunter.exception.NoSuchVacancyException;
import com.liferay.headhunter.model.Vacancy;
import com.liferay.headhunter.model.impl.VacancyImpl;
import com.liferay.headhunter.model.impl.VacancyModelImpl;
import com.liferay.headhunter.service.persistence.VacancyPersistence;
import com.liferay.headhunter.service.persistence.impl.constants.HHPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the vacancy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = VacancyPersistence.class)
public class VacancyPersistenceImpl
	extends BasePersistenceImpl<Vacancy> implements VacancyPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>VacancyUtil</code> to access the vacancy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		VacancyImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the vacancies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching vacancies
	 */
	@Override
	public List<Vacancy> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Vacancy> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Vacancy> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Vacancy> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<Vacancy> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Vacancy> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Vacancy> list = null;

		if (useFinderCache) {
			list = (List<Vacancy>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Vacancy vacancy : list) {
					if (!uuid.equals(vacancy.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_VACANCY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(VacancyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<Vacancy>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vacancy
	 * @throws NoSuchVacancyException if a matching vacancy could not be found
	 */
	@Override
	public Vacancy findByUuid_First(
			String uuid, OrderByComparator<Vacancy> orderByComparator)
		throws NoSuchVacancyException {

		Vacancy vacancy = fetchByUuid_First(uuid, orderByComparator);

		if (vacancy != null) {
			return vacancy;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchVacancyException(sb.toString());
	}

	/**
	 * Returns the first vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vacancy, or <code>null</code> if a matching vacancy could not be found
	 */
	@Override
	public Vacancy fetchByUuid_First(
		String uuid, OrderByComparator<Vacancy> orderByComparator) {

		List<Vacancy> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vacancy
	 * @throws NoSuchVacancyException if a matching vacancy could not be found
	 */
	@Override
	public Vacancy findByUuid_Last(
			String uuid, OrderByComparator<Vacancy> orderByComparator)
		throws NoSuchVacancyException {

		Vacancy vacancy = fetchByUuid_Last(uuid, orderByComparator);

		if (vacancy != null) {
			return vacancy;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchVacancyException(sb.toString());
	}

	/**
	 * Returns the last vacancy in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vacancy, or <code>null</code> if a matching vacancy could not be found
	 */
	@Override
	public Vacancy fetchByUuid_Last(
		String uuid, OrderByComparator<Vacancy> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Vacancy> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Vacancy[] findByUuid_PrevAndNext(
			long vacancyId, String uuid,
			OrderByComparator<Vacancy> orderByComparator)
		throws NoSuchVacancyException {

		uuid = Objects.toString(uuid, "");

		Vacancy vacancy = findByPrimaryKey(vacancyId);

		Session session = null;

		try {
			session = openSession();

			Vacancy[] array = new VacancyImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, vacancy, uuid, orderByComparator, true);

			array[1] = vacancy;

			array[2] = getByUuid_PrevAndNext(
				session, vacancy, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Vacancy getByUuid_PrevAndNext(
		Session session, Vacancy vacancy, String uuid,
		OrderByComparator<Vacancy> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_VACANCY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(VacancyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(vacancy)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Vacancy> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the vacancies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Vacancy vacancy :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(vacancy);
		}
	}

	/**
	 * Returns the number of vacancies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching vacancies
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_VACANCY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "vacancy.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(vacancy.uuid IS NULL OR vacancy.uuid = '')";

	public VacancyPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Vacancy.class);

		setModelImplClass(VacancyImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the vacancy in the entity cache if it is enabled.
	 *
	 * @param vacancy the vacancy
	 */
	@Override
	public void cacheResult(Vacancy vacancy) {
		entityCache.putResult(
			VacancyImpl.class, vacancy.getPrimaryKey(), vacancy);
	}

	/**
	 * Caches the vacancies in the entity cache if it is enabled.
	 *
	 * @param vacancies the vacancies
	 */
	@Override
	public void cacheResult(List<Vacancy> vacancies) {
		for (Vacancy vacancy : vacancies) {
			if (entityCache.getResult(
					VacancyImpl.class, vacancy.getPrimaryKey()) == null) {

				cacheResult(vacancy);
			}
		}
	}

	/**
	 * Clears the cache for all vacancies.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VacancyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vacancy.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Vacancy vacancy) {
		entityCache.removeResult(VacancyImpl.class, vacancy);
	}

	@Override
	public void clearCache(List<Vacancy> vacancies) {
		for (Vacancy vacancy : vacancies) {
			entityCache.removeResult(VacancyImpl.class, vacancy);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(VacancyImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new vacancy with the primary key. Does not add the vacancy to the database.
	 *
	 * @param vacancyId the primary key for the new vacancy
	 * @return the new vacancy
	 */
	@Override
	public Vacancy create(long vacancyId) {
		Vacancy vacancy = new VacancyImpl();

		vacancy.setNew(true);
		vacancy.setPrimaryKey(vacancyId);

		String uuid = PortalUUIDUtil.generate();

		vacancy.setUuid(uuid);

		return vacancy;
	}

	/**
	 * Removes the vacancy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy that was removed
	 * @throws NoSuchVacancyException if a vacancy with the primary key could not be found
	 */
	@Override
	public Vacancy remove(long vacancyId) throws NoSuchVacancyException {
		return remove((Serializable)vacancyId);
	}

	/**
	 * Removes the vacancy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vacancy
	 * @return the vacancy that was removed
	 * @throws NoSuchVacancyException if a vacancy with the primary key could not be found
	 */
	@Override
	public Vacancy remove(Serializable primaryKey)
		throws NoSuchVacancyException {

		Session session = null;

		try {
			session = openSession();

			Vacancy vacancy = (Vacancy)session.get(
				VacancyImpl.class, primaryKey);

			if (vacancy == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVacancyException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(vacancy);
		}
		catch (NoSuchVacancyException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Vacancy removeImpl(Vacancy vacancy) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vacancy)) {
				vacancy = (Vacancy)session.get(
					VacancyImpl.class, vacancy.getPrimaryKeyObj());
			}

			if (vacancy != null) {
				session.delete(vacancy);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (vacancy != null) {
			clearCache(vacancy);
		}

		return vacancy;
	}

	@Override
	public Vacancy updateImpl(Vacancy vacancy) {
		boolean isNew = vacancy.isNew();

		if (!(vacancy instanceof VacancyModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(vacancy.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(vacancy);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in vacancy proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Vacancy implementation " +
					vacancy.getClass());
		}

		VacancyModelImpl vacancyModelImpl = (VacancyModelImpl)vacancy;

		if (Validator.isNull(vacancy.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			vacancy.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(vacancy);
			}
			else {
				vacancy = (Vacancy)session.merge(vacancy);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(VacancyImpl.class, vacancyModelImpl, false, true);

		if (isNew) {
			vacancy.setNew(false);
		}

		vacancy.resetOriginalValues();

		return vacancy;
	}

	/**
	 * Returns the vacancy with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vacancy
	 * @return the vacancy
	 * @throws NoSuchVacancyException if a vacancy with the primary key could not be found
	 */
	@Override
	public Vacancy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVacancyException {

		Vacancy vacancy = fetchByPrimaryKey(primaryKey);

		if (vacancy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVacancyException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return vacancy;
	}

	/**
	 * Returns the vacancy with the primary key or throws a <code>NoSuchVacancyException</code> if it could not be found.
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy
	 * @throws NoSuchVacancyException if a vacancy with the primary key could not be found
	 */
	@Override
	public Vacancy findByPrimaryKey(long vacancyId)
		throws NoSuchVacancyException {

		return findByPrimaryKey((Serializable)vacancyId);
	}

	/**
	 * Returns the vacancy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param vacancyId the primary key of the vacancy
	 * @return the vacancy, or <code>null</code> if a vacancy with the primary key could not be found
	 */
	@Override
	public Vacancy fetchByPrimaryKey(long vacancyId) {
		return fetchByPrimaryKey((Serializable)vacancyId);
	}

	/**
	 * Returns all the vacancies.
	 *
	 * @return the vacancies
	 */
	@Override
	public List<Vacancy> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Vacancy> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Vacancy> findAll(
		int start, int end, OrderByComparator<Vacancy> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Vacancy> findAll(
		int start, int end, OrderByComparator<Vacancy> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Vacancy> list = null;

		if (useFinderCache) {
			list = (List<Vacancy>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_VACANCY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_VACANCY;

				sql = sql.concat(VacancyModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Vacancy>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the vacancies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Vacancy vacancy : findAll()) {
			remove(vacancy);
		}
	}

	/**
	 * Returns the number of vacancies.
	 *
	 * @return the number of vacancies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_VACANCY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "vacancyId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_VACANCY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VacancyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vacancy persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new VacancyModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", Vacancy.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(VacancyImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = HHPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = HHPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = HHPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_VACANCY =
		"SELECT vacancy FROM Vacancy vacancy";

	private static final String _SQL_SELECT_VACANCY_WHERE =
		"SELECT vacancy FROM Vacancy vacancy WHERE ";

	private static final String _SQL_COUNT_VACANCY =
		"SELECT COUNT(vacancy) FROM Vacancy vacancy";

	private static final String _SQL_COUNT_VACANCY_WHERE =
		"SELECT COUNT(vacancy) FROM Vacancy vacancy WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "vacancy.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Vacancy exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Vacancy exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		VacancyPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(HHPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;
	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();

	private static class VacancyModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			VacancyModelImpl vacancyModelImpl = (VacancyModelImpl)baseModel;

			long columnBitmask = vacancyModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(vacancyModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						vacancyModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(vacancyModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			VacancyModelImpl vacancyModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = vacancyModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = vacancyModelImpl.getColumnValue(columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}