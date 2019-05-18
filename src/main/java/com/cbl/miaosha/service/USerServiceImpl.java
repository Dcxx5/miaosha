/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: USerServiceIpl
 * Author:   cuibaoluo
 * Date:     2019/1/21 3:02 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.service;

import com.cbl.miaosha.dao.UserDOMapper;
import com.cbl.miaosha.dao.UserPasswordDOMapper;
import com.cbl.miaosha.dataObject.UserDO;
import com.cbl.miaosha.dataObject.UserPasswordDO;
import com.cbl.miaosha.error.BusinessException;
import com.cbl.miaosha.error.ErrBusinessError;
import com.cbl.miaosha.service.model.USerModel;
import com.cbl.miaosha.validator.validatorImpls;
import com.cbl.miaosha.validator.validatorResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/21
 * @since 1.0.0
 */
@Service
public class USerServiceImpl implements UserService{

    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private validatorImpls validatorResult;
    @Override
    public USerModel getUserById(Integer id) {
        UserDO userDO=userDOMapper.selectByPrimaryKey(id);
        if(userDO == null){
            return null;
        }
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        return couvertFromDataObejct(userDO,userPasswordDO);
    }

    @Override
    @Transactional
    public void register(USerModel uSerModel) throws BusinessException {
        if(uSerModel == null){
            throw new BusinessException(ErrBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        /*if(StringUtils.isEmpty(uSerModel.getName())
                || StringUtils.isEmpty(uSerModel.getTelphone())){
            throw new BusinessException(ErrBusinessError.PARAMETER_VALIDATION_ERROR);
        }*/

        validatorResult result=validatorResult.validatorResult(uSerModel);
        if(result.isHasErrors()){
            throw new BusinessException(ErrBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMag());
        }
        UserDO userDO=couvertfrommodel(uSerModel);
        try {
            userDOMapper.insertSelective(userDO);

        }catch (DuplicateKeyException ex){
            throw new BusinessException(ErrBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已重复注册");
        }
        uSerModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO=couvertfrompasswordmodel(uSerModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

    }

    @Override
    public USerModel validagteLogin(String telphone, String encrppassword) throws BusinessException {
        UserDO userDO=userDOMapper.selectBytelphone(telphone);
        if(userDO==null){
            throw new BusinessException(ErrBusinessError.USERLOGIN_NOT_EXIST);
        }
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        USerModel uSerModel=couvertFromDataObejct(userDO,userPasswordDO);

        if(!StringUtils.equals(encrppassword,uSerModel.getEncrptPassword())){
            throw new BusinessException(ErrBusinessError.USERLOGIN_NOT_EXIST);
        }
        return uSerModel;

    }

    private UserPasswordDO couvertfrompasswordmodel(USerModel uSerModel){
        if(uSerModel ==null){
            return null;
        }
        UserPasswordDO userPasswordDO=new UserPasswordDO();
        userPasswordDO.setEncrptPassword(uSerModel.getEncrptPassword());
        userPasswordDO.setUserId(uSerModel.getId());
        return userPasswordDO;
    }
    private UserDO couvertfrommodel(USerModel uSerModel){
        if(uSerModel ==null){
            return null;
        }
        UserDO userDO=new UserDO();
        BeanUtils.copyProperties(uSerModel,userDO);
        return userDO;
    }

    private USerModel couvertFromDataObejct(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO == null){
            return null;
        }
        USerModel uSerModel =new USerModel();
        BeanUtils.copyProperties(userDO,uSerModel);
        if(userPasswordDO != null){
            uSerModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return uSerModel;
    }
}
