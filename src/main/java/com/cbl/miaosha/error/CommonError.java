/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CommonErroe
 * Author:   cuibaoluo
 * Date:     2019/1/21 3:56 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.error;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/21
 * @since 1.0.0
 */
public interface CommonError {
    public int getErrorCode();
    public String getErrMsg();
    public CommonError setErrMSg(String errMSg);
}
