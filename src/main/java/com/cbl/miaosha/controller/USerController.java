/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: USerController
 * Author:   cuibaoluo
 * Date:     2019/1/21 3:00 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.cbl.miaosha.dataObject.UserDO;
import com.cbl.miaosha.error.BusinessException;
import com.cbl.miaosha.error.ErrBusinessError;
import com.cbl.miaosha.response.CommonRetuenType;
import com.cbl.miaosha.service.UserService;
import com.cbl.miaosha.service.model.USerModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/21
 * @since 1.0.0
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class USerController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login",method={RequestMethod.POST},consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonRetuenType login(@RequestParam(name="telphone")String telphone,@RequestParam(name="password")String password) throws Exception {

        if (org.apache.commons.lang3.StringUtils.isEmpty(telphone) || org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(ErrBusinessError.PARAMETER_VALIDATION_ERROR);
    }
        USerModel uSerModel=userService.validagteLogin(telphone,this.EnocodeByMd5(password));
        this.httpServletRequest.getSession().setAttribute("ISLOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGINUSER",uSerModel);
        return CommonRetuenType.create(null);
    }

    //注册
    @RequestMapping(value = "/register",method={RequestMethod.POST},consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonRetuenType register(@RequestParam(name="telphone")String telphone,
                    @RequestParam(name="otpcode")String otpcode,
                    @RequestParam(name="name")String name,
                    @RequestParam(name="gender")Integer gender,
                    @RequestParam(name="age")Integer age,
                    @RequestParam(name="password")String password) throws Exception{
       String insessionotpcode=(String)this.httpServletRequest.getSession().getAttribute(telphone);
        if(!org.apache.commons.lang3.StringUtils.equals(otpcode,insessionotpcode)){
            throw new BusinessException(ErrBusinessError.PARAMETER_VALIDATION_ERROR,"验证码不正确");
        }
       USerModel uSerModel=new USerModel();
       uSerModel.setName(name);
        uSerModel.setGender(gender);
        uSerModel.setAge(age);
        uSerModel.setTelphone(telphone);
        uSerModel.setRegisterMode("byphone");
        uSerModel.setEncrptPassword(this.EnocodeByMd5(password));
        userService.register(uSerModel);
        return CommonRetuenType.create(null);
    }
public String EnocodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest messageDigest=MessageDigest.getInstance("MD5");
    BASE64Encoder base64Encoder=new BASE64Encoder();
    String newstr=base64Encoder.encode(messageDigest.digest(str.getBytes("UTF-8")));
    return newstr;
}


    //短信接口
    @RequestMapping(value = "/getotp",method={RequestMethod.POST},consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonRetuenType getotp(@RequestParam(name="telphone")String telphone){
        Random random=new Random();
        int redomint=random.nextInt(99999);
        redomint+=10000;
        String otpcode=String.valueOf(redomint);
        httpServletRequest.getSession().setAttribute(telphone,otpcode);
        System.out.println("telphone"+telphone+"otpcode"+otpcode);
        return CommonRetuenType.create(null);
    }
    @RequestMapping("/get")
    @ResponseBody
    public CommonRetuenType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        USerModel uSerModel=userService.getUserById(id);
        if(uSerModel==null){
            throw new BusinessException(ErrBusinessError.USER_NOT_EXIST);
        }
        UserDO userDO=convertFromModel(uSerModel);
        return CommonRetuenType.create(userDO);
    }
    private UserDO convertFromModel(USerModel uSerModel){
        if(uSerModel == null){
            return null;
        }
        UserDO userDO=new UserDO();
        BeanUtils.copyProperties(uSerModel,userDO);
        return userDO;
    }

}
