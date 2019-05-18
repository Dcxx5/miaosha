/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BaseController
 * Author:   cuibaoluo
 * Date:     2019/1/21 4:33 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.controller;

import com.cbl.miaosha.error.BusinessException;
import com.cbl.miaosha.error.ErrBusinessError;
import com.cbl.miaosha.response.CommonRetuenType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/21
 * @since 1.0.0
 */
public class BaseController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object  handleException(HttpServletRequest request, Exception e){
        Map<String,Object> responseData=new HashMap<>();
        if(e instanceof BusinessException){
            BusinessException businessException=(BusinessException)e;
            responseData.put("errCode",businessException.getErrorCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }else{
            responseData.put("errCode",ErrBusinessError.UKNOWNERROR.getErrorCode());
            responseData.put("errMsg",ErrBusinessError.UKNOWNERROR.getErrMsg());
        }
        return CommonRetuenType.create(responseData,"fail");
    }
}
