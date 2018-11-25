package com.forecast.forecastback.serviceImpl;

import com.forecast.forecastback.model.UserInfoEntity;
import com.forecast.forecastback.service.IUserInfoService;
import com.forecast.forecastback.serviceImpl.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService extends AbstractService<UserInfoEntity, Long> implements IUserInfoService {

}
