package com.huituopin.common.dao.support;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 继承自spring的HibernateDaoSupport<br>
 * 提供了和具体实体类无关的数据库操作,如分页函数和若干便捷查询方法
 * 
 * @see HibernateDaoSupport
 */
@Repository
public class HibernateGenericDao extends HibernateDaoSupport {
    /**
     * @author tangjingyuan
     * @see HibernateDaoSupport的setSessionFactory方法为final，子类不能重写 - 【注入
     *      SessionFactory】 - 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport )里
     * **/
    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 分页查询函数，使用hql.
     * 
     * @param pageNo
     *            页号,从1开始.
     */
    public QueryResult pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values);
        long totalCount = (Long) countlist.get(0);
        if (totalCount < 1)
            return new QueryResult();
        // 实际查询返回分页对象
        int startIndex = QueryResult.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, values);
        List list = null;
        if (pageNo < 0 || pageSize < 0) {
            startIndex = 1;
            list = query.list();
            pageSize = list.size();
        } else {
            list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        }
        return new QueryResult(startIndex, totalCount, pageSize, list);
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
    public QueryResult<?> dataQuery(String hql, int start, int pageSize, Object... values) {
        Assert.hasText(hql);
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values);
        long totalCount = (Long) countlist.get(0);
        if (totalCount < 1)
            return new QueryResult();
        // 实际查询返回分页对象
        int startIndex = start;
        Query query = createQuery(hql, values);
        List list = null;
        if (start < 0 || pageSize < 0) {
            startIndex = 1;
            list = query.list();
            pageSize = list.size();
        } else {
            list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        }
        return new QueryResult(startIndex, totalCount, pageSize, list);
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
        Assert.hasText(hql);
        Query query = currentSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }

    /**
     * SQL方式查询
     * 
     * @param sql
     *            符合SQL语法的查询语句
     * @param values
     *            数量可变的条件值，按顺序绑定
     */
    public SQLQuery createSQLQuery(final String sql, final Object... values) {
        SQLQuery query = currentSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    /**
     * 根据hql查询,直接使用HibernateTemplate的find函数.
     * 
     * @param values
     *            可变参数
     */
    @SuppressWarnings("unchecked")
    public List<?> find(String hql, Object... values) {
        Assert.hasText(hql);
        return getHibernateTemplate().find(hql, values);
    }

    /**
     * 执行本地sql语句获得标量数值列表
     */
    public List<?> executeNativeSql(String sql) {
        return currentSession().createSQLQuery(sql).list();
    }

    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     */
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     */
    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order//s*by[//w|//W|//s|//S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
