package com.forecast.forecastback.model.base;

import java.util.Map;

public interface IExtends {
    Map<String, Object> getExt();

    void setExt(Map<String, Object> ext);
}
