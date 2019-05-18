/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CommonRetuenType
 * Author:   cuibaoluo
 * Date:     2019/1/21 3:42 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.response;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/21
 * @since 1.0.0
 */
public class CommonRetuenType {
    private String status;
    private Object data;

    public static CommonRetuenType create(Object result){
        return CommonRetuenType.create(result,"success");
    }
    public static CommonRetuenType create(Object result,String status){
        CommonRetuenType type=new CommonRetuenType();
        type.setData(result);
        type.setStatus(status);
        return type;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
