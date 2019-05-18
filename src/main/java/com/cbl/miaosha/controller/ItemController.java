/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ItemController
 * Author:   cuibaoluo
 * Date:     2019/1/22 3:27 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.controller;

import com.cbl.miaosha.dataObject.ItemVo;
import com.cbl.miaosha.response.CommonRetuenType;
import com.cbl.miaosha.service.ItemService;
import com.cbl.miaosha.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/22
 * @since 1.0.0
 */
@Controller("item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;
    @RequestMapping(value = "/create",method={RequestMethod.POST},consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonRetuenType createItem(@RequestParam(name="title")String title,
                                       @RequestParam(name="description")String description,
                                       @RequestParam(name="price")String price,
                                       @RequestParam(name="stock")Integer stock,
                                       @RequestParam(name="imageurl")String imageurl) throws Exception{
        ItemModel itemModel=new ItemModel();
        itemModel.setPrice(price);
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setStock(stock);
        itemModel.setImageurl(imageurl);
        ItemModel itemModel1=itemService.createItem(itemModel);
        ItemVo itemVo=convertFormModel(itemModel1);
        return CommonRetuenType.create(itemVo);
    }
    private ItemVo convertFormModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemVo itemVo=new ItemVo();
        BeanUtils.copyProperties(itemModel,itemVo);
        return itemVo;
    }
}
