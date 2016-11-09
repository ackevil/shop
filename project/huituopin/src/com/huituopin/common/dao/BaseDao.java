package com.huituopin.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;

import com.huituopin.common.dao.support.HibernateGenericDao;
import com.huituopin.common.dao.support.QueryResult;

/**
 * 提供hibernate dao的所有操作,<br>
 * 实现类由spring注入HibernateEntityDao和HibernateGenericDao来实现
 * 
 */
public interface BaseDao<T, PK extends Serializable> {
    public T get(PK id);

    public void delete(T o);

    /**
     * 根据ID移除对象.
     */
    public void deleteById(PK id);

    /**
     * 保存对象.
     */
    public void save(T o);

    /**
     * 根据属性名和属性值查询单个对象.
     * 
     * @return 符合条件的唯一对象 or null
     */
    public T getUniqueBy(String propertyName, Object value);

    /**
     * 获取全部对象
     * 
     * @see HibernateGenericDao#getAll(Class)
     */
    public List<T> findAll();

    /**
     * 获取全部对象,带排序字段与升降序参数.
     */
    public List<T> findAll(String orderBy, boolean isAsc);

    /**
     * 取得Entity的Criteria.
     */
    public Criteria createCriteria(Criterion... criterions);

    /**
     * 根据属性名和属性值查询对象.
     * 
     * @return 符合条件的对象列表
     */
    public List<T> findBy(String propertyName, Object value);

    /**
     * 判断对象某些属性的值在数据库中唯一.
     * 
     * @param uniquePropertyNames
     *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
     * @see HibernateGenericDao#isUnique(Class,Object,String)
     */
    public boolean isUnique(T entity, String... uniquePropertyNames);

    /**
     * 消除与 Hibernate Session 的关联
     * 
     */
    public void evit(T entity);

    /**
     * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
     */
    public T load(PK id);

    /**
     * 删除对象.
     */

    public void flush();

    public void clear();

    /**
     * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>.
     * 
     * @param pageNo
     *            页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    public QueryResult pagedQuery(Criteria criteria, int pageNo, int pageSize);

    /**
     * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>.
     * 
     * @param pageNo
     *            页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    public QueryResult pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc, Criterion... criterions);

    /**
     * 创建Criteria对象，带排序字段与升降序字段.
     */
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions);

    /**
     * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>.
     * 
     * @param pageNo
     *            页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    public QueryResult pagedQuery(int pageNo, int pageSize, Criterion... criterions);

    /**
     * 在不同的session中关联修改过的托管对象
     */
    public void update(T entity);

    /**
     * 
     * @param propertyName
     *            属性名
     * @param value
     *            属性值
     * @return 对应属性的记录数量
     */
    public long findCount(String propertyName, Object value);

    /**
     * 
     * @param propMap
     *            属性名与属性值组成的Map
     * @return 返回Map中属性名与属性值对应的相等的记录的数量
     */
    public long findCount(Map<String, Object> propMap);

    /**
     * 获取序列
     * 
     * @param sequenceName
     * @return
     */
    public String getSequence(String sequenceName);

    public Query createQuery(String hql, Object... values);

    public SQLQuery createSQLQuery(String hql, Object... values);
}
