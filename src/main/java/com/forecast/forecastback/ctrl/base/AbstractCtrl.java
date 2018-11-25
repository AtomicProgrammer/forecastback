package com.forecast.forecastback.ctrl.base;

import com.forecast.forecastback.model.base.BaseEntity;
import com.forecast.forecastback.service.base.IService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class AbstractCtrl<Entity extends BaseEntity, ID extends Serializable>{

  //
  //public @ResponseBody
  //IRestResponse create(@RequestBody RequestValue<Entity> requestValue,
  //    HttpServletRequest request) {
  //
  //  Entity entity = requestValue.getBody();
  //
  //  if (entity == null) {
  //    return RestResponse.builder().error(HeadCode.BAD_REQUEST);
  //  }
  //
  //  ICreateTrigger.Code code = ICreateTrigger.Code.CREATE;
  //  Map<String, String[]> parameterMap = request.getParameterMap();
  //
  //  ITrigger trigger = getTrigger();
  //
  //  if (trigger != null && trigger instanceof ICreateTrigger) {
  //    trigger.setAttributeStorage(new HashMap<>());
  //    code = ((ICreateTrigger) trigger).beforeCreate(entity, parameterMap);
  //  }
  //
  //  if (code == ICreateTrigger.Code.CREATE) {
  //    entity.setDeleted(false);
  //    entity = getService().save(entity);
  //  }
  //
  //  IRestResponse restResponse = RestResponse.builder().data(entity);
  //
  //  if (trigger != null && trigger instanceof ICreateTrigger) {
  //    restResponse = ((ICreateTrigger) trigger).afterCreate(restResponse, entity, parameterMap);
  //  }
  //
  //  return restResponse;
  //}
  //
  //
  //public @ResponseBody
  //IRestResponse delete(@PathVariable("pid") ID[] ids,
  //    HttpServletRequest request) {
  //  IService<Entity, ID> service = getService();
  //
  //  Map<String, String[]> parameterMap = request.getParameterMap();
  //  IDeleteTrigger.Code code = IDeleteTrigger.Code.DELETE;
  //
  //  ITrigger trigger = getTrigger();
  //
  //  if (trigger != null && trigger instanceof IDeleteTrigger) {
  //    trigger.setAttributeStorage(new HashMap<>());
  //    code = ((IDeleteTrigger) trigger).beforeDelete(ids, parameterMap);
  //  }
  //
  //  if (ids != null && code == IDeleteTrigger.Code.DELETE) {
  //    service.delete(ids);
  //  }
  //
  //  IRestResponse restResponse = RestResponse.builder();
  //
  //  if (trigger != null && trigger instanceof IDeleteTrigger) {
  //    restResponse = ((IDeleteTrigger) trigger).afterDelete(restResponse, ids, parameterMap);
  //  }
  //
  //  return restResponse;
  //
  //}
  //
  //
  //public @ResponseBody
  //IRestResponse update(@RequestBody RequestValue<Entity> requestValue,
  //    HttpServletRequest request) {
  //
  //  Entity entity = requestValue.getBody();
  //
  //  if (entity == null) {
  //    return RestResponse.builder().error(HeadCode.BAD_REQUEST);
  //  }
  //
  //  IUpdateTrigger.Code code = IUpdateTrigger.Code.UPDATE;
  //  Map<String, String[]> parameterMap = request.getParameterMap();
  //
  //  ITrigger trigger = getTrigger();
  //
  //  if (trigger != null && trigger instanceof IUpdateTrigger) {
  //    trigger.setAttributeStorage(new HashMap<>());
  //    code = ((IUpdateTrigger) trigger).beforeUpdate(entity, parameterMap);
  //  }
  //
  //  if (code == IUpdateTrigger.Code.UPDATE) {
  //    entity = getService().update(entity);
  //  }
  //
  //  IRestResponse restResponse = RestResponse.builder().data(entity);
  //  if (trigger != null && trigger instanceof IUpdateTrigger) {
  //    restResponse = ((IUpdateTrigger) trigger).afterUpdate(restResponse, entity, parameterMap);
  //  }
  //  return restResponse;
  //
  //}
  //
  //
  //public @ResponseBody
  //IRestResponse findOne(@PathVariable("pid") ID pid) {
  //
  //  if (pid == null) {
  //    return RestResponse.builder().error(HeadCode.NOT_FIND);
  //  }
  //
  //  Optional<Entity> entityOptional = getService().findOne(pid);
  //
  //  if (entityOptional.isPresent()) {
  //    return RestResponse.builder().data(entityOptional.get());
  //  }
  //
  //  return RestResponse.builder().error(HeadCode.NOT_FIND);
  //
  //}
  //
  //
  //public @ResponseBody
  //IRestResponse findList(
  //    @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
  //    @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {
  //
  //  Page<Entity> entities = getService().pageQuery(pageNo, pageSize);
  //  return RestResponse.builder().data(entities);
  //}
  //
  //public @ResponseBody
  //IRestResponse search(@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
  //    @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize,
  //    @RequestBody RequestValue<Entity> requestValue) {
  //  Entity entity = requestValue.getBody();
  //  Page<Entity> entities = getService().pageSearch(entity, pageNo, pageSize);
  //  return RestResponse.builder().data(entities);
  //}
  //
  //public @ResponseBody
  //IRestResponse search(@RequestBody RequestValue<QueryBean> requestValue) {
  //
  //  Page<Entity> entities = getService().pageSearch(requestValue.getBody());
  //
  //  return RestResponse.builder().data(entities);
  //}
  //
  //public @ResponseBody
  //IRestResponse listSearch(@RequestBody RequestValue<QueryBean> requestValue) {
  //
  //  List<Entity> entities = getService().listSearch(requestValue.getBody());
  //
  //  return RestResponse.builder().data(entities);
  //}
  //
  //protected abstract ITrigger getTrigger();

  protected abstract IService<Entity, ID> getService();
}
