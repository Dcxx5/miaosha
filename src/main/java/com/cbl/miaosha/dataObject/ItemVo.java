/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ItemVo
 * Author:   cuibaoluo
 * Date:     2019/1/22 3:28 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.dataObject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/22
 * @since 1.0.0
 */
public class ItemVo {

    private Integer id;
    private String name;
    private String price;
    private Integer stock;
    private String description;
    private Integer sales;
    private String imageurl;
}
