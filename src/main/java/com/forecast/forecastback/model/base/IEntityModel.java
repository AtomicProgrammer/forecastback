package com.forecast.forecastback.model.base;


import java.io.Serializable;

/**
 * Created by liubinduo on 2017/6/28.
 */
public interface IEntityModel extends Serializable {

    Long getPid();

    void setPid(Long id);


    String getName();

    void setName(String name);


}
