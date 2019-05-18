/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: validatorImpl
 * Author:   cuibaoluo
 * Date:     2019/1/22 11:54 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/22
 * @since 1.0.0
 */
@Component
public class validatorImpls implements InitializingBean {

    private Validator validator;

    public validatorResult validatorResult(Object bean){
        validatorResult result=new validatorResult();
        Set<ConstraintViolation<Object>> constraintViolations=  validator.validate(bean);
        if(constraintViolations.size()>0){
            result.setHasErrors(true);
            constraintViolations.forEach(constraintViolation->{
                String errMsg=constraintViolation.getMessage();
                String propertyName=constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }
    @Override
    public void afterPropertiesSet() throws Exception {

    this.validator=Validation.buildDefaultValidatorFactory().getValidator();
    }
}
