package com.huituopin.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.common.dao.support.BeanUtils;
import com.huituopin.common.dao.support.HibernateGenericDao;
import com.huituopin.common.dao.support.QueryResult;
import com.huituopin.common.dao.support.ReflectUtils;

public abstract class BaseDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {
    protected Class<T> entityClass;// DAO所管理的Entity类型.

    /**
     * HibernateDaoSupport的setSession方法为final，子类不能重写 - 【注入 SessionFactory】 -
     * 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport )里
     * **/
    @Autowired
    public void setSessionFactoryO(SessionFactory sessionFactory) {

        super.setSessionFactory(sessionFactory);
    }

    @Autowired
    private HibernateGenericDao hgdao;

    public BaseDaoImpl() {
        this.entityClass = ReflectUtils.getClassGenricType(getClass());
    }

    /**
     * session.merge()方法会首先发送一句select语句，去数据库端获取Object持久化标识所对应的表记录；
     * 然后自动生成一个持久化状态的Object实体 ，与脱管状态的Object实体做比较是否有所改变；一旦发生了改变，才会发送update语句执行更新
     * 。而按执行顺序，若两句session.merge ()方法针对同一个脱管状态的Object实体，那其结果只会执行最后一个session.merge
     * ()方法所发出的update语句。即使执行了session.merge()方法，Object实体依然是脱管状态.
     * 
     * @info 如果数据库中有该记录，则更新该记录，如果不存在该记录，则进行insert操作。
     * @param o
     */
    public void merge(T o) {
        currentSession().merge(o);
    }

    /**
     * 清除所有对象缓存
     */
    public void clear() {

        getHibernateTemplate().clear();
    }

    /**
     * 消除与 Hibernate Session 的关联
     * 
     * @param entity
     */
    public void evit(T entity) {
        getHibernateTemplate().evict(entity);
    }

    /**
     * 执行一些必须的sql语句把内存中的对象同步到jdbc的链接中
     */
    public void flush() {
        getHibernateTemplate().flush();
    }

    /**
     * 根据实体类与ID获得对象
     * 
     * @param entityClass
     *            实体类
     * @param id
     *            主键ID
     */
    public T get(PK id) {
        return (T) getHibernateTemplate().get(this.entityClass, id);
    }

    /**
     * 根据Serializable类型的id获取实体对象
     * <p/>
     * 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
     * 
     * @param id
     */
    public T load(PK id) {

        return (T) getHibernateTemplate().load(this.entityClass, id);
    }

    /**
     * 删除对象.
     */
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * 根据ID删除对象.
     */
    public void deleteById(PK id) {
        delete(get(id));
    }

    /**
     * 根据属性名和属性值查询唯一对象.
     * 
     * @return 符合条件的唯一对象 or null if not found.
     */
    @SuppressWarnings("unchecked")
    public T getUniqueBy(String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
    }

    /**
     * 保存对象.<br>
     * 如果对象已在本session中持久化了,不做任何事。<br>
     * 如果另一个seesion拥有相同的持久化标识,抛出异常。<br>
     * 如果没有持久化标识属性,调用save()。<br>
     * 如果持久化标识表明是一个新的实例化对象,调用save()。<br>
     * 如果是附带版本信息的(<version>或<timestamp>)且版本属性表明为新的实例化对象就save()。<br>
     * 否则调用update()重新关联托管对象
     */
    public void save(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    /**
     * 获取全部对象,带排序字段与升降序参数.
     * 
     * @param true为升序
     *            ，false为降序
     */
    public List<T> findAll(String orderBy, boolean isAsc) {
        Assert.hasText(orderBy);
        if (isAsc)
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(this.entityClass).addOrder(Order.asc(orderBy)));
        else
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(this.entityClass).addOrder(Order.desc(orderBy)));
    }

    /**
     * 获取实体类型的全部对象
     */
    public List<T> findAll() {

        return (List<T>) (getHibernateTemplate().loadAll(this.entityClass));
    }

    /**
     * 根据hql查询,直接使用HibernateTemplate的find函数.
     * 
     * @param values
     *            可变参数
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql, Object... values) {
        return (List<T>) hgdao.find(hql, values);
    }

    /**
     * 根据属性名和属性值查询对象.
     * 
     * @return 符合条件的对象列表
     */
    public List<T> findBy(String propertyName, Object value) {
        Assert.hasText(propertyName);
        return createCriteria(Restrictions.eq(propertyName, value)).list();
    }

    /**
     * 根据属性名和属性值查询对象,带排序参数.
     */
    public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
        Assert.hasText(propertyName);
        Assert.hasText(orderBy);
        return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
    }

    /**
     * 创建Query对象.
     * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
     * 留意可以连续设置,如下：
     * 
     * <pre>
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
     * </pre>
     * 
     * 调用方式如下：
     * 
     * <pre>
     * 
     *        dao.createQuery(hql) 
     *        dao.createQuery(hql,arg0); 
     *        dao.createQuery(hql,arg0,arg1); 
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
     * </pre>
     * 
     * @param values
     *            可变参数.
     */
    public Query createQuery(String hql, Object... values) {
        return hgdao.createQuery(hql, values);
    }

    public SQLQuery createSQLQuery(String hql, Object... values) {
        return hgdao.createSQLQuery(hql, values);
    }

    /**
     * 生产排序SQL
     * 
     * @param orderby
     *            排序属性
     */
    private static String buildOrderBy(LinkedHashMap<String, String> orderby) {
        StringBuilder sb = new StringBuilder();
        if (orderby != null && orderby.size() > 0) {
            sb.append(" order by ");
            for (String key : orderby.keySet()) {
                // sb.append("lower(o.").append(key).append(") ")
                sb.append(" o.").append(key).append(" ").append(orderby.get(key)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 生产排序SQL(原生)
     * 
     * @param orderby
     *            排序属性
     */
    private static String buildSqlOrderBy(LinkedHashMap<String, String> orderby) {
        StringBuilder sb = new StringBuilder();
        if (orderby != null && orderby.size() > 0) {
            sb.append(" order by ");
            for (String key : orderby.keySet()) {
                sb.append(key).append(" ").append(orderby.get(key)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 设置参数
     * 
     * @param params
     *            参数值数组
     */
    private static void setParameter(Query query, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
    }

    /*
     * 单个实体分页查询
     * 
     * @param startIndex 分页的起始位置从0开始
     * 
     * @param maxResult 每页显示的固定条数
     * 
     * @param whereJPQL 查询条件
     * 
     * @param params 参数值
     * 
     * @orderBy 排序
     * 
     * @return QueryResult
     */
    @SuppressWarnings("unchecked")
    // @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public QueryResult<T> getScrollDate(int startIndex, int maxResult, String whereJPQL, Object[] params,
            LinkedHashMap<String, String> orderby) throws Exception {

        QueryResult<T> qr = new QueryResult<T>();
        String entityName = getEntityName(this.entityClass);
        String where = (whereJPQL != null && !"".equals(whereJPQL.trim())) ? "where " + whereJPQL : "";
        Query query = currentSession().createQuery(
                "select o from " + entityName + " o " + where + buildOrderBy(orderby));

        setParameter(query, params);
        if (startIndex != -1 && maxResult != -1)
            query.setFirstResult(startIndex)//
                    .setMaxResults(maxResult);//
        qr.setResultlist(query.list());

        query = currentSession().createQuery("select count(*) from " + entityName + " o " + where);

        setParameter(query, params);
        qr.setTotalrecord(new Long(query.uniqueResult().toString()));
        return qr;
    }

    /*
     * 原生SQL分页查询或实体SQL分页查询
     * 
     * @param startIndex 分页的起始位置从0开始
     * 
     * @param maxResult 每页显示的固定条数
     * 
     * @param whereJPQL 查询条件
     * 
     * @param params 参数值
     * 
     * @orderBy 排序
     * 
     * @return QueryResult
     */
    @SuppressWarnings("unchecked")
    // @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public QueryResult<T> getScrollDateBySQL(int startIndex, int maxResult, String whereJPQL, Object[] params,
            LinkedHashMap<String, String> orderby) throws Exception {

        QueryResult<T> qr = new QueryResult<T>();
        String entityName = getEntityName(this.entityClass);
        String where = (whereJPQL != null && !"".equals(whereJPQL.trim())) ? "where " + whereJPQL : "";
        SQLQuery query = currentSession().createSQLQuery(
                "select * from " + entityName + " o " + where + buildOrderBy(orderby));

        setParameter(query, params);
        if (startIndex != -1 && maxResult != -1)
            query.setFirstResult(startIndex)//
                    .setMaxResults(maxResult);//
        qr.setResultlist(query.addEntity(this.entityClass).list());

        query = currentSession().createSQLQuery("select count(*) from " + entityName + " o " + where);

        setParameter(query, params);
        qr.setTotalrecord(new Long(query.uniqueResult().toString()));
        return qr;
    }

    /*
     * 原生SQL分页查询或实体SQL分页查询
     * 
     * @param startIndex 分页的起始位置
     * 
     * @param maxResult 每页显示的记录数
     * 
     * @param searchField 所查询的字段
     * 
     * @param fromSQL 查询的表
     * 
     * @param whereSQL 查询条件
     * 
     * @param params 参数值
     * 
     * @param orderby 排序字段
     * 
     * @return QueryResult
     */
    @SuppressWarnings("unchecked")
    // @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    public QueryResult<Object[]> getScrollDateByNativeSQL(int startIndex, int maxResult, String searchField,
            String fromSQL, String whereSQL, Object[] params, LinkedHashMap<String, String> orderby) throws Exception {

        QueryResult<Object[]> qr = new QueryResult<Object[]>();

        SQLQuery query = null;
        String where = (whereSQL != null && !"".equals(whereSQL.trim())) ? " where " + whereSQL : "";
        String nativeSQl = "select " + searchField + " from " + fromSQL + where + buildSqlOrderBy(orderby);

        query = createSQLQuery(nativeSQl, params);
        if (startIndex != -1 && maxResult != -1)
            query.setFirstResult(startIndex).setMaxResults(maxResult);
        qr.setResultlist(query.list());

        String countQueryString = "select count(*) from " + fromSQL + where;
        query = createSQLQuery(countQueryString, params);
        qr.setTotalrecord(new Long(query.uniqueResult().toString()));
        return qr;
    }

    /*
     * 原生SQL分页查询或实体SQL分页查询
     * 
     * @param startIndex 分页的起始位置
     * 
     * @param maxResult 每页显示的记录数
     * 
     * @param searchField 所查询的字段
     * 
     * @param fromSQL 查询的表
     * 
     * @param whereSQL 查询条件
     * 
     * @param params 参数值
     * 
     * @param orderby 排序字段
     * 
     * @return QueryResult
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public QueryResult<Object[]> getScrollDateByXmlSQL(int startIndex, int maxResult, String SQL, Object[] params)
            throws Exception {

        QueryResult<Object[]> qr = new QueryResult<Object[]>();

        SQLQuery query = null;
        // String where = (whereSQL != null && !"".equals(whereSQL.trim())) ?
        // " where " + whereSQL : "";
        // String nativeSQl = SQL + where + buildSqlOrderBy(orderby);

        query = createSQLQuery(SQL, params);
        if (startIndex != -1 && maxResult != -1)
            query.setFirstResult(startIndex).setMaxResults(maxResult);
        qr.setResultlist(query.list());

        String countQueryString = "select count(*) from (" + SQL + " )";
        query = createSQLQuery(countQueryString, params);
        qr.setTotalrecord(new Long(query.uniqueResult().toString()));
        return qr;
    }

    /*
     * 实体HQL分页查询
     * 
     * @param startIndex 分页的起始位置
     * 
     * @param maxResult 每页显示的记录数
     * 
     * @param searchField 所查询的实体属性
     * 
     * @param fromSQL 查询的实体名称
     * 
     * @param whereSQL 查询条件
     * 
     * @param params 参数值
     * 
     * @param orderby 排序字段
     * 
     * @return QueryResult
     */
    // @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
    @SuppressWarnings("unchecked")
    public QueryResult<Object[]> getScrollDateByHQL(int startIndex, int maxResult, String searchField, String fromSQL,
            String whereSQL, Object[] params, LinkedHashMap<String, String> orderby) throws Exception {

        QueryResult<Object[]> qr = new QueryResult<Object[]>();

        Query query = null;
        String where = (whereSQL != null && !"".equals(whereSQL.trim())) ? " where " + whereSQL : "";
        String nativeSQl = "select " + searchField + " from " + fromSQL + where + buildSqlOrderBy(orderby);

        query = createQuery(nativeSQl, params);
        if (startIndex != -1 && maxResult != -1)
            query.setFirstResult(startIndex).setMaxResults(maxResult);
        qr.setResultlist(query.list());

        String countString = "select count(*) from " + fromSQL + where;
        query = createQuery(countString, params);
        qr.setTotalrecord(new Long(query.uniqueResult().toString()));
        return qr;
    }

    @SuppressWarnings("unchecked")
    public QueryResult<T> getEntityByHQL(int startIndex, int maxResult, String searchField, String fromSQL,
            String whereSQL, Object[] params, LinkedHashMap<String, String> orderby) throws Exception {
        QueryResult<T> qr = new QueryResult<T>();
        Query query = null;
        String where = (whereSQL != null && !"".equals(whereSQL.trim())) ? " where " + whereSQL : "";
        String nativeSQl = "select " + searchField + " from " + fromSQL + where + buildSqlOrderBy(orderby);
        query = createQuery(nativeSQl, params);
        if (startIndex != -1 && maxResult != -1)
            query.setFirstResult(startIndex).setMaxResults(maxResult);
        qr.setResultlist(query.list());
        // String countString = "select count(*) from ( select " + searchField +
        // " from " + fromSQL +where +")";
        String countString = "";
        if (searchField.indexOf("distinct") != -1) {
            // String tmp = searchField.trim()+".*";
            // countString = "select count(*) from(select " + tmp + " from " +
            // fromSQL +where +" ) ";
            countString = "select " + searchField + " from " + fromSQL + where + "  ";
            query = createQuery(countString, params);
            qr.setTotalrecord(query.list().size());
        } else {
            countString = "select count(*) from " + fromSQL + where;
            query = createQuery(countString, params);
            qr.setTotalrecord(new Long(query.uniqueResult().toString()));
        }
        // countString =
        // "select  distinct p  from  Projects p, Sampleitem s  where  p.projectcode=s.projectcode  and p.status='SampeTest' and s.batchno is null";
        return qr;
    }

    @SuppressWarnings("unchecked")
    public QueryResult<T> getEntityByHQL(String searchField, String fromSQL, String whereSQL, Object[] params,
            LinkedHashMap<String, String> orderby) throws Exception {
        QueryResult<T> qr = new QueryResult<T>();
        Query query = null;
        String where = (whereSQL != null && !"".equals(whereSQL.trim())) ? " where " + whereSQL : "";
        String nativeSQl = "select " + searchField + " from " + fromSQL + where + buildSqlOrderBy(orderby);
        query = createQuery(nativeSQl, params);
        qr.setResultlist(query.list());
        qr.setTotalrecord(query.list().size());
        return qr;
    }

    /*
     * 得到具体实体名
     * 
     * @param entityClass 具体类
     */
    private static <E> String getEntityName(Class<E> entityClass) {
        String entityname = entityClass.getSimpleName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name()))
            entityname = entity.name();
        return entityname;
    }

    /**
     * 创建Criteria对象.
     * 
     * @return
     * @author tangjingyuan
     * @create 2015-04-14
     * @see hibernate 4中不再有getSession()方法，而用currentSession()替换
     */
    public Criteria createCriteria() {
        return this.currentSession().createCriteria(this.entityClass);
    }

    /**
     * 创建Criteria对象.
     * 
     * @param criterions
     *            可变的Restrictions条件列表
     */
    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = currentSession().createCriteria(this.entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 创建Criteria对象，带排序字段与升降序字段.
     */
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {

        Assert.hasText(orderBy);
        Criteria criteria = createCriteria(criterions);
        if (isAsc)
            criteria.addOrder(Order.asc(orderBy));
        else
            criteria.addOrder(Order.desc(orderBy));
        return criteria;
    }

    /**
     * @param hql
     *            查询sql
     * @param start
     *            分页从哪一条数据开始
     * @param pageSize
     *            每一个页面的大小
     * @param values
     *            查询条件
     * @return page对象
     */
    protected QueryResult<?> dataQuery(String hql, int start, int pageSize, Object... values) {
        return hgdao.dataQuery(hql, start, pageSize, values);
    }

    /**
     * 执行本地sql语句获得标量数值列表
     */
    @SuppressWarnings("unchecked")
    protected List<T> executeNativeSql(String sql) {
        return (List<T>) hgdao.executeNativeSql(sql);
    }

    /**
     * 判断对象某些属性的值在数据库中是否唯一.
     * 
     * @tangjingyuan
     * @date2014-8-18
     * @param uniquePropertyNames
     *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
     */
    public boolean isUnique(T entity, String... nameList) {
        Assert.notNull(nameList);
        Criteria criteria = createCriteria().setProjection(Projections.rowCount());
        try {
            // 循环加入唯一列
            for (String name : nameList) {
                criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(entity, name)));
            }
            // 以下代码为了如果是update的情况,排除entity自身.
            String idName = getIdName(this.entityClass);
            // 取得entity的主键值
            PK id = getId(this.entityClass, entity);
            // 如果id!=null,说明对象已存在,该操作为update,加入排除自身的判断
            if (id != null)
                criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
        } catch (Exception e) {
            ReflectionUtils.handleReflectionException(e);
        }
        int count = (Integer) criteria.uniqueResult();
        return count == 0;
    }

    /**
     * 分页查询函数，使用hql.
     * 
     * @param pageNo
     *            页号,从1开始.
     */
    protected QueryResult pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        return hgdao.pagedQuery(hql, pageNo, pageSize, values);
    }

    /**
     * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>.
     * 
     * @param pageNo
     *            页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    public QueryResult pagedQuery(Criteria criteria, int pageNo, int pageSize) {
        Assert.notNull(criteria);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        CriteriaImpl impl = (CriteriaImpl) criteria;
        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = impl.getProjection();
        List<CriteriaImpl.OrderEntry> orderEntries;
        try {
            orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries");
            BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");
        }
        // 执行查询
        int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        // 将之前的Projection和OrderBy条件重新设回去
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        try {
            BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");
        }
        // 返回分页对象
        if (totalCount < 1)
            return new QueryResult();
        int startIndex = QueryResult.getStartOfPage(pageNo, pageSize);
        List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new QueryResult(startIndex, totalCount, pageSize, list);
    }

    /**
     * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>.
     * 
     * @param pageNo
     *            页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    public QueryResult pagedQuery(int pageNo, int pageSize, Criterion... criterions) {

        Criteria criteria = createCriteria(criterions);
        return pagedQuery(criteria, pageNo, pageSize);
    }

    /**
     * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>.
     * 
     * @param pageNo
     *            页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    public QueryResult pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc, Criterion... criterions) {
        Criteria criteria = createCriteria(orderBy, isAsc, criterions);
        return pagedQuery(criteria, pageNo, pageSize);
    }

    /**
     * 在不同的session中关联修改过的托管对象
     */
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    /**
     * 取得对象的主键值,辅助函数.
     */
    @SuppressWarnings("unchecked")
    public PK getId(Class<T> entityClass, T entity) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Assert.notNull(entity);
        Assert.notNull(entityClass);
        return (PK) PropertyUtils.getProperty(entity, getIdName(entityClass));
    }

    /**
     * 取得对象的主键名,辅助函数.
     */
    public String getIdName(Class<T> clazz) {
        Assert.notNull(clazz);
        ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
        Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory.");
        String idName = meta.getIdentifierPropertyName();
        Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define.");
        return idName;
    }

    /**
     * 
     * @param propertyName
     *            属性名
     * @param value
     *            属性值
     * @return 对应属性的记录数量
     */
    public long findCount(String propertyName, Object value) {
        Criteria criteria = currentSession().createCriteria(this.entityClass);
        criteria.add(Restrictions.eq(propertyName, value));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        if (count == null) {
            return 0;
        }
        return count;
    }

    /**
     * 
     * @param propMap
     *            属性名与属性值组成的Map
     * @return 返回Map中属性名与属性值对应的相等的记录的数量
     */
    public long findCount(Map<String, Object> propMap) {
        Criteria criteria = currentSession().createCriteria(this.entityClass);
        if (propMap.size() > 0) {
            for (String key : propMap.keySet()) {
                criteria.add(Restrictions.eq(key, propMap.get(key)));
            }
        }
        criteria.setProjection(Projections.rowCount());
        Integer count = (Integer) criteria.uniqueResult();
        if (count == null) {
            return 0;
        }
        return count;
    }

    protected Query createFilter(Collection<?> collection, String condition) {
        return this.hgdao.getSessionFactory().getCurrentSession().createFilter(collection, condition);
    }

    /**
     * 获得序列值
     * 
     * @param sequenceName
     *            :序列名称 如角色表的序列是:"SEQ_ROLE"
     * 
     */
    public String getSequence(String sequenceName) {
        String str = "";
        try {
            Query query = currentSession().createSQLQuery(
                    "SELECT T.NEXTVALUE FROM SEQUENCE T WHERE T.SEQUENCETYPE='" + sequenceName + "'");
            str = query.list().get(0).toString();
            currentSession().createSQLQuery(
                    "UPDATE  SEQUENCE T SET T.NEXTVALUE=T.NEXTVALUE+T.INDENTITYVALUE WHERE T.SEQUENCETYPE='"
                            + sequenceName + "'").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    // 环境统计模块使用
    @SuppressWarnings("unchecked")
    public List querySQL(final String sql, final Map valueMap, final Boolean flagMap) {
        return (List<Map<String, String>>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql);
                Iterator it = null;
                if (valueMap != null) {
                    it = valueMap.keySet().iterator();
                }
                String key = "";
                Object obj = null;
                while (it != null && it.hasNext()) {
                    key = (String) it.next();
                    obj = valueMap.get(key);
                    if (obj instanceof java.util.Collection)
                        query.setParameterList(key, (java.util.Collection) obj);

                    else if (obj instanceof Object[])
                        query.setParameterList(key, (Object[]) obj);

                    else
                        query.setParameter(key, obj);
                }
                if (flagMap) {
                    query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                }
                return query.list();
                // return new Integer(query.executeUpdate());
            }
        });
    }
}
