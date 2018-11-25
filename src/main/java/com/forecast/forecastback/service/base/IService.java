package com.forecast.forecastback.service.base;

import com.forecast.forecastback.model.base.IEntityModel;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IService<T extends IEntityModel, ID extends Serializable> {

  boolean exists(ID id);

  boolean exists(T example);

  boolean exists(String propertyName, Object value);

  Optional<T> findOne(ID id);

  Optional<T> findOne(String propertyName, Object value);

  T findOneById(ID id);

  List<T> findAll(ID... id);

  List<T> findAllIn(String propertyName, Collection<?> values);


  List<T> findAll(Collection<?> idValues);

  List<T> findAll();
  //
  //
  //List<T> findAll(T example);
  //
  //List<T> findAll(String propertyName, Object value);
  //
  //Page<T> pageQuery(int pageNo, int pageSize);
  //
  //Page<T> pageQuery(T example, int pageNo, int pageSize);
  //
  //Page<T> pageQuery(String propertyName, Object value, int pageNo, int pageSize);
  //
  //Page<T> pageSearch(T example, int pageNo, int pageSize);
  //
  //Page<T> pageSearch(QueryBean queryBean);
  //
  //List<T> listSearch(QueryBean queryBean);
  //
  //T save(T entity);
  //
  //Iterable<T> save(Iterable<T> iterable);
  //
  //List<T> save(List<T> entities);
  //
  //T update(T entity);
  //
  //List<T> update(List<T> entities);
  //
  //boolean delete(T entity);
  //
  //int delete(ID... id);
  //
  //int delete(List<T> entities);
  //
  //int remove(String propertyName, Object propertyValue);
  //
  //int removeIn(String propertyName, Collection<?> idValues);
  //
  //int remove(List<T> entities);
  //
  //boolean remove(T entity);
  //
  //int remove(ID id);
  //
  //int remove(ID... id);
}