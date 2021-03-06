package com.forecast.forecastback.serviceImpl.base;

import com.forecast.forecastback.model.base.IEntityModel;
import com.forecast.forecastback.service.base.IService;
import io.ebean.EbeanServer;
import io.ebean.LikeType;
import io.ebean.Query;
import io.ebean.bean.EntityBean;
import io.ebeaninternal.server.expression.DefaultExampleExpression;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liubinduo on 2017/6/28.
 */

public abstract class AbstractService<T extends IEntityModel, ID extends Serializable>
    implements IService<T, ID> {


  private Class<T> entityClass;


  @Autowired
  protected EbeanServer server;


  public AbstractService() {
    Type genType = getClass().getGenericSuperclass();
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    entityClass = (Class) params[0];
  }


  @Override
  public boolean exists(ID id) {

    Validate.notNull(id, "The id must be not null !");

    return getQuery().setId(id).findCount() > 0;
  }

  @Override
  public boolean exists(T example) {

    Validate.notNull(example, "The example entity must be not null !");

    DefaultExampleExpression defaultExampleExpression = new DefaultExampleExpression(
        (EntityBean) example, false, LikeType.EQUAL_TO);
    return getQuery().where().add(defaultExampleExpression).findCount() > 0;
  }

  @Override
  public boolean exists(String propertyName, Object value) {

    Validate.notBlank(propertyName, "The property name must be not blank !");
    Validate.notNull(value, "The property value must be not null !");

    return getQuery().where().eq(propertyName, value).findCount() > 0;
  }


  @Override
  public Optional<T> findOne(ID id) {

    Validate.notNull(id, "The id entity must be not null !");

    return getQuery().setId(id).findOneOrEmpty();
  }

  @Override
  public Optional<T> findOne(String propertyName, Object value) {

    Validate.notEmpty(propertyName, "The name of property must be not empty !");
    Validate.notNull(value, "The value of property must be not null !");

    return getQuery().where().eq(propertyName, value).findOneOrEmpty();
  }

  @Override
  public T findOneById(ID id) {
    return getQuery().setId(id).findOne();
  }

  @Override
  public List<T> findAll(ID... id) {
    return getQuery().where().idIn(id).findList();
  }

  @Override
  public List<T> findAllIn(String propertyName, Collection<?> values) {
    return getQuery().where().in(propertyName, values).findList();
  }


  @Override
  public List<T> findAll(Collection<?> idValues) {
    return getQuery().where().idIn(idValues).findList();
  }

  @Override
  public List<T> findAll() {
    return getQuery().findList();
  }

  //@Override
  //public List<T> findAll(T example) {
  //    DefaultExampleExpression defaultExampleExpression = new DefaultExampleExpression((EntityBean) example, false, LikeType.EQUAL_TO);
  //    return getQuery().where().add(defaultExampleExpression).findList();
  //}
  //
  //@Override
  //public List<T> findAll(String propertyName, Object value) {
  //    return getQuery().where().eq(propertyName, value).order().desc("createTime").findList();
  //}
  //
  //@Override
  //public Page<T> pageQuery(int pageNo, int pageSize) {
  //
  //    Validate.isTrue(pageNo >= 0, "The pageNo must be greater than or equal to zero !");
  //    Validate.isTrue(pageSize > 0, "The pageSize must be greater than zero !");
  //
  //    Query<T> query = getQuery();
  //    int count = query.where().findCount();
  //    if (count > 0) {
  //        PagedList<T> pagedList = query.where().order().desc("pid")
  //                .setFirstRow(pageNo * pageSize)
  //                .setMaxRows(pageSize)
  //                .findPagedList();
  //
  //        return convertPage(pageNo, pageSize, count, pagedList);
  //    }
  //
  //    return convertPage(pageNo, pageSize, 0, null);
  //}
  //
  //
  //@Override
  //public Page<T> pageQuery(T example, int pageNo, int pageSize) {
  //
  //    Validate.isTrue(pageNo >= 0, "The pageNo must be greater than or equal to zero !");
  //    Validate.isTrue(pageSize > 0, "The pageSize must be greater than zero !");
  //    Validate.notNull(example, "The example entity must be not null !");
  //
  //    final ExpressionList<T> where = getQuery().where();
  //
  //    ExampleExpression.andExpression(where, example);
  //
  //    int count = where.findCount();
  //
  //    if (count > 0) {
  //
  //        PagedList<T> pagedList = where
  //                .order().desc("pid")
  //                .setFirstRow(pageNo * pageSize)
  //                .setMaxRows(pageSize)
  //                .findPagedList();
  //
  //        return convertPage(pageNo, pageSize, count, pagedList);
  //
  //    }
  //    return convertPage(pageNo, pageSize, 0, null);
  //}
  //
  //
  //@Override
  //public Page<T> pageQuery(String propertyName, Object value, int pageNo, int pageSize) {
  //
  //    Validate.notBlank(propertyName, "The property name must be not blank !");
  //    Validate.isTrue(pageNo >= 0, "The pageNo must be greater than or equal to zero !");
  //    Validate.isTrue(pageSize > 0, "The pageSize must be greater than zero !");
  //
  //    ExpressionList<T> expressionList = getQuery().where().eq(propertyName, value).eq("deleted", false);
  //
  //    int count = expressionList.findCount();
  //
  //    if (count > 0) {
  //        PagedList<T> pagedList = expressionList.setFirstRow(pageNo * pageSize)
  //                .setMaxRows(pageSize).findPagedList();
  //        return convertPage(pageNo, pageSize, count, pagedList);
  //    }
  //
  //    return convertPage(pageNo, pageSize, 0, null);
  //}
  //
  //
  //@Override
  //public Page<T> pageSearch(T example, int pageNo, int pageSize) {
  //    Validate.isTrue(pageNo >= 0, "The pageNo must be greater than or equal to zero !");
  //    Validate.isTrue(pageSize > 0, "The pageSize must be greater than zero !");
  //    Validate.notNull(example, "The example entity must be not null !");
  //
  //    final ExpressionList<T> where = getQuery().where();
  //
  //    ExampleExpression.orExpression(where, example);
  //
  //    int count = where.findCount();
  //
  //    if (count > 0) {
  //
  //        PagedList<T> pagedList = where
  //                .order().desc("pid")
  //                .setFirstRow(pageNo * pageSize)
  //                .setMaxRows(pageSize)
  //                .findPagedList();
  //
  //        return convertPage(pageNo, pageSize, count, pagedList);
  //
  //    }
  //    return convertPage(pageNo, pageSize, 0, null);
  //}
  //
  ///**
  // * 高级分页查询
  // *
  // * @param queryBean 查询参数实体
  // * @return Page<T>
  // */
  //@Override
  //public Page<T> pageSearch(QueryBean queryBean) {
  //
  //    Validate.notNull(queryBean, "The queryBean entity must be not null !");
  //    Validate
  //        .isTrue(queryBean.getPageNo() >= 0, "The pageNo must be greater than or equal to zero !");
  //    Validate.isTrue(queryBean.getPageSize() > 0, "The pageSize must be greater than zero !");
  //
  //
  //    List<Group> groups = queryBean.getGroups();
  //
  //    ExpressionList<T> where = getGroupWhere(queryBean.getOption(), groups);
  //
  //    if (StringUtils.isNotEmpty(queryBean.getOrderBy())) {
  //
  //        if (queryBean.isDesc()) {
  //            where.orderBy(queryBean.getOrderBy() + " desc");
  //        } else {
  //            where.orderBy(queryBean.getOrderBy());
  //        }
  //    } else {
  //
  //        where.order().desc("pid");
  //    }
  //
  //    int count = where.findCount();
  //
  //    if (count > 0) {
  //
  //        PagedList<T> pagedList = where
  //                .setFirstRow(queryBean.getPageNo() * queryBean.getPageSize())
  //                .setMaxRows(queryBean.getPageSize())
  //                .findPagedList();
  //
  //        return convertPage(queryBean.getPageNo(), queryBean.getPageSize(), count, pagedList);
  //
  //    }
  //    return convertPage(queryBean.getPageNo(), queryBean.getPageSize(), 0, null);
  //
  //}
  //
  //@Override
  //public List<T> listSearch(QueryBean queryBean) {
  //
  //    Validate.notNull(queryBean, "The queryBean entity must be not null !");
  //
  //    List<Group> groups = queryBean.getGroups();
  //
  //    ExpressionList<T> where = getGroupWhere(queryBean.getOption(), groups);
  //
  //    if (StringUtils.isNotEmpty(queryBean.getOrderBy())) {
  //
  //        if (queryBean.isDesc()) {
  //            where.orderBy().desc(queryBean.getOrderBy());
  //        } else {
  //            where.orderBy(queryBean.getOrderBy());
  //        }
  //    }
  //
  //    return where.findList();
  //}
  //
  //
  ///**
  // * 组处理
  // *
  // * @param option 操作类型
  // * @param groups 条件组
  // * @return ExpressionList<T>
  // */
  //private ExpressionList<T> getGroupWhere(String option, List<Group> groups) {
  //
  //    final ExpressionList<T> where = getQuery().where();
  //
  //    Junction<T> junction;
  //
  //    if (EbeanOptions.OR.equalsIgnoreCase(option)) {
  //        junction = where.or();
  //    } else {
  //        junction = where.and();
  //    }
  //
  //    for (Group group : groups) {
  //
  //        ExpressionList<T> conditionWhere = getConditionWhere(group.getConditions());
  //
  //        if (conditionWhere == null) {
  //
  //            continue;
  //        }
  //
  //
  //        if (EbeanOptions.OR.equalsIgnoreCase(group.getOption())) {
  //
  //            junction.or().addAll(conditionWhere).endOr();
  //
  //        } else {
  //            junction.and().addAll(conditionWhere).endAnd();
  //        }
  //    }
  //    if (EbeanOptions.OR.equalsIgnoreCase(option)) {
  //        junction.endOr();
  //    } else {
  //        junction.endAnd();
  //    }
  //
  //
  //    return where;
  //
  //}
  //
  ///**
  // * 基础WHERE处理
  // *
  // * @param conditions 条件集合
  // * @return ExpressionList<T>
  // */
  //private ExpressionList<T> getConditionWhere(List<Condition> conditions) {
  //
  //     ExpressionList<T> where = null;
  //
  //    for (Condition condition : conditions) {
  //
  //        // 字段名为空不处理
  //        String column = condition.getColumn();
  //
  //        if (StringUtils.isEmpty(column)) {
  //
  //            continue;
  //        }
  //
  //        // 没有操作符不处理
  //        if (StringUtils.isEmpty(condition.getOption())) {
  //
  //            continue;
  //        }
  //
  //        Object value = condition.getValue();
  //
  //        if (value instanceof String && !condition.getOption().contains("null")) {
  //
  //            String strValue = (String) value;
  //
  //            strValue = strValue.replaceAll("%", "");
  //
  //            if (StringUtils.isEmpty(column) || StringUtils.isEmpty(strValue)) {
  //
  //                continue;
  //            }
  //
  //        }
  //
  //        // 数组类型，如果值为空，则不处理
  //        if (value instanceof List && value == null) {
  //
  //            continue;
  //
  //        }
  //
  //        if (EbeanOptions.BETWEEN.equalsIgnoreCase(condition.getOption()) && (condition.getStart() == null || condition.getEnd() == null)) {
  //
  //            continue;
  //        }
  //
  //        Map<String, String> path = processJsonPath(column);
  //
  //        if (value == null && !EbeanOptions.BETWEEN.equalsIgnoreCase(condition.getOption())) {
  //
  //            continue;
  //        }
  //
  //        if (where == null) {
  //
  //            where = getQuery().where();
  //        }
  //
  //        switch (StringUtils.lowerCase(condition.getOption())) {
  //
  //            case EbeanOptions.EQ:
  //                where.eq(column, value);
  //                break;
  //
  //            case EbeanOptions.LIKE:
  //                where.like(column, (String) value);
  //                break;
  //
  //            case EbeanOptions.IN:
  //                where.in(column, value);
  //                break;
  //
  //            case EbeanOptions.NOT_IN:
  //                where.notIn(column, value);
  //                break;
  //
  //            case EbeanOptions.IS_NULL:
  //                where.isNull(column);
  //                break;
  //
  //            case EbeanOptions.IS_NOT_NULL:
  //                where.isNotNull(column);
  //                break;
  //
  //            case EbeanOptions.LE:
  //                where.le(column, value);
  //                break;
  //
  //            case EbeanOptions.LT:
  //                where.le(column, value);
  //                break;
  //
  //            case EbeanOptions.GE:
  //                where.ge(column, value);
  //                break;
  //
  //            case EbeanOptions.GT:
  //                where.gt(column, value);
  //                break;
  //
  //            case EbeanOptions.BETWEEN:
  //                where.between(column, condition.getStart(), condition.getEnd());
  //                break;
  //
  //            case EbeanOptions.ARRAY_IN:
  //                where.arrayContains(column, ((List<Object>) value).toArray());
  //                break;
  //
  //            case EbeanOptions.ARRAY_NOT_IN:
  //                where.arrayNotContains(column, ((List<Object>) value).toArray());
  //                break;
  //
  //            case EbeanOptions.ARRAY_IS_NULL:
  //                where.arrayIsEmpty(column);
  //                break;
  //
  //            case EbeanOptions.ARRAY_IS_NOT_NULL:
  //                where.arrayIsNotEmpty(column);
  //                break;
  //
  //            case EbeanOptions.JSON_B_EQ:
  //
  //                where.jsonEqualTo(path.get("column"), path.get("path"), condition.getValue());
  //                break;
  //            case EbeanOptions.JSON_B_NOT_EQ:
  //
  //
  //                where.jsonNotEqualTo(path.get("column"), path.get("path"), condition.getValue());
  //                break;
  //            case EbeanOptions.JSON_B_GE:
  //
  //                where.jsonGreaterOrEqual(path.get("column"), path.get("path"), condition.getValue());
  //                break;
  //            case EbeanOptions.JSON_B_GT:
  //
  //
  //                where.jsonGreaterThan(path.get("column"), path.get("path"), condition.getValue());
  //            case EbeanOptions.JSON_B_LE:
  //
  //
  //                where.jsonLessOrEqualTo(path.get("column"), path.get("path"), condition.getValue());
  //            case EbeanOptions.JSON_B_LT:
  //
  //                where.jsonLessThan(path.get("column"), path.get("path"), condition.getValue());
  //                break;
  //            case EbeanOptions.JSON_B_BEWTEEN:
  //
  //                where.jsonBetween(path.get("column"), path.get("path"), condition.getStart(), condition.getEnd());
  //                break;
  //            default:
  //                where.eq(condition.getColumn(), condition.getValue());
  //                break;
  //
  //        }
  //
  //    }
  //
  //    return where;
  //}
  //
  //private Map<String, String> processJsonPath(String column) {
  //
  //    int firstPointIndex = column.indexOf(".");
  //
  //    if (firstPointIndex == -1) {
  //
  //        return null;
  //    }
  //
  //    String jsonColumn = column.substring(0, firstPointIndex);
  //
  //    String path = column.substring(firstPointIndex + 1);
  //
  //    if (StringUtils.isEmpty(jsonColumn) || StringUtils.isEmpty(path)) {
  //
  //        return null;
  //    }
  //
  //    Map<String, String> result = new HashMap<>(2);
  //
  //    result.put("column", jsonColumn);
  //    result.put("path", path);
  //
  //    return result;
  //}
  //
  //@Override
  //public T save(T entity) {
  //    server.save(entity);
  //    return entity;
  //}
  //
  //@Override
  //public Iterable<T> save(Iterable<T> iterable) {
  //
  //    Validate.notNull(iterable, "The iterable must be not null !");
  //
  //    ArrayList<T> beans = new ArrayList<>();
  //    CollectionUtils.addAll(beans, iterable.iterator());
  //    server.saveAll(beans);
  //    return beans;
  //}
  //
  //@Override
  //public List<T> save(List<T> entities) {
  //
  //    Validate.notEmpty(entities, "The entities must be not empty !");
  //
  //    server.saveAll(entities);
  //    return entities;
  //}
  //
  //@Override
  //public T update(T entity) {
  //
  //    Validate.notNull(entity, "The entity must be not null !");
  //
  //    server.update(entity);
  //    return entity;
  //}
  //
  //@Override
  //public List<T> update(List<T> entities) {
  //
  //    Validate.notEmpty(entities, "The entities must be not empty !");
  //    server.updateAll(entities);
  //    return entities;
  //}
  //
  //@Override
  //public boolean delete(T entity) {
  //
  //    Validate.notNull(entity, "The entity must be not null !");
  //
  //    return server.delete(entity);
  //}
  //
  //@Override
  //public int delete(ID... id) {
  //
  //    Validate.notNull(id, "The entity must be not null !");
  //
  //    return server.update(entityClass).set("deleted", true).where().in("pid", id).update();
  //
  //}
  //
  //@Override
  //public int delete(List<T> entities) {
  //
  //    Validate.notEmpty(entities, "The entities must be not empty !");
  //
  //    return server.deleteAll(entities);
  //}
  //
  //@Override
  //public int remove(String propertyName, Object propertyValue) {
  //
  //    Validate.notEmpty(propertyName, "The propertyName must be not empty !");
  //
  //    Validate.notNull(propertyValue, "The propertyValue must be not empty !");
  //
  //    return getQuery().where().eq(propertyName, propertyValue).delete();
  //}
  //
  //
  //@Override
  //public int removeIn(String propertyName, Collection<?> idValues) {
  //    return getQuery().where().in(propertyName, idValues).delete();
  //}
  //
  //@Override
  //public int remove(List<T> entities) {
  //
  //    Validate.notEmpty(entities, "The entities must be not empty !");
  //
  //    return server.deleteAllPermanent(entities);
  //}
  //
  //
  //@Override
  //public boolean remove(T entity) {
  //
  //    Validate.notNull(entity, "The entity must be not null !");
  //
  //    return server.deletePermanent(entity);
  //
  //}
  //
  //@Override
  //public int remove(ID id) {
  //
  //    Validate.notNull(id, "The id must be not null !");
  //
  //    return server.deletePermanent(this.entityClass, id);
  //}
  //
  //@Override
  //public int remove(ID... id) {
  //
  //    Validate.notNull(id, "The id must be not null !");
  //
  //    return server.deleteAllPermanent(this.entityClass, Arrays.asList(id));
  //
  //}
  //
  protected Query<T> getQuery() {
    return server.find(entityClass);
  }
  //
  //protected UpdateQuery<T> getUpdate() {
  //    return server.update(this.entityClass);
  //}
  //
  //
  //protected PageImpl<T> convertPage(int pageNo, int pageSize, int totalCount, PagedList<T> pagedList) {
  //    if (pagedList != null) {
  //        return new PageImpl<>(pagedList.getList(), new PageRequest(pageNo, pageSize), totalCount);
  //    }
  //    return new PageImpl<>(Lists.newArrayList(), new PageRequest(pageNo, pageSize), 0);
  //}

}



