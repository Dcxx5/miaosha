/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: USerModel
 * Author:   cuibaoluo
 * Date:     2019/1/21 3:06 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/21
 * @since 1.0.0
 */
public class USerModel {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotNull(message = "性别不能为空")
    private Integer gender;
    @NotNull(message = "年龄不能为空")
    @Min(value = 0,message = "年龄必须大于0")
    @Max(value = 150,message = "年龄不能大于150")
    private Integer age;
    @NotBlank(message = "手机号不能为空")
    private String telphone;
    private String registerMode;
    private String thirdPatyId;
    @NotBlank(message = "密码不能为空")
    private String encrptPassword;

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdPatyId() {
        return thirdPatyId;
    }

    public void setThirdPatyId(String thirdPatyId) {
        this.thirdPatyId = thirdPatyId;
    }
}
