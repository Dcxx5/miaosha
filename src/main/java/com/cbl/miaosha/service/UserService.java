package com.cbl.miaosha.service;

import com.cbl.miaosha.error.BusinessException;
import com.cbl.miaosha.service.model.USerModel;

public interface UserService {
    USerModel getUserById(Integer id);

    void register(USerModel uSerModel) throws BusinessException;

    USerModel validagteLogin(String telphone,String encrppassword) throws BusinessException;
}
