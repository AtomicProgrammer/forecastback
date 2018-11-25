package com.forecast.forecastback.model.base;

public interface ISoftDelete {
    Boolean isDeleted();

    void setDeleted(Boolean deleted);
}
