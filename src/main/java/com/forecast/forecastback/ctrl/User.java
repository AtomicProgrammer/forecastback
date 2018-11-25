package com.forecast.forecastback.ctrl;

import com.forecast.forecastback.ctrl.base.AbstractCtrl;
import com.forecast.forecastback.model.UserInfoEntity;
import com.forecast.forecastback.service.IUserInfoService;
import com.forecast.forecastback.service.base.IService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("user")
public class User extends AbstractCtrl<UserInfoEntity, Long> {

  @Autowired
  IUserInfoService userInfoService;

  @RequestMapping(value = "111", method = RequestMethod.GET)
  public String findO() {
    UserInfoEntity userEntity = new UserInfoEntity();
    List<UserInfoEntity> list = userInfoService.findAll();


    return "aaaaaa";
  }

  @Override
  protected IService<UserInfoEntity, Long> getService() {
    return userInfoService;
  }
}
