/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ItemServiceImpl
 * Author:   cuibaoluo
 * Date:     2019/1/22 2:55 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cbl.miaosha.service;

import com.cbl.miaosha.dao.ItemDOMapper;
import com.cbl.miaosha.dao.ItemStockDOMapper;
import com.cbl.miaosha.dataObject.ItemDO;
import com.cbl.miaosha.dataObject.ItemStockDO;
import com.cbl.miaosha.error.BusinessException;
import com.cbl.miaosha.error.ErrBusinessError;
import com.cbl.miaosha.service.model.ItemModel;
import com.cbl.miaosha.validator.validatorImpls;
import com.cbl.miaosha.validator.validatorResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PipedReader;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cuibaoluo
 * @create 2019/1/22
 * @since 1.0.0
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDOMapper itemDOMapper;
    @Autowired
    private ItemStockDOMapper itemStockDOMapper;
    @Autowired
    private validatorImpls validator;
    @Override
    @Transactional
    public ItemModel createItem(ItemModel item) throws BusinessException {
        validatorResult result=validator.validatorResult(item);
        if(result.isHasErrors()){
            throw new BusinessException(ErrBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMag());
        }
        ItemDO itemDO=this.convertItemFromModel(item);
        itemDOMapper.insertSelective(itemDO);
        item.setId(itemDO.getId());
        ItemStockDO itemStockDO=this.convertItemstockFromModel(item);
        itemStockDOMapper.insertSelective(itemStockDO);
        return this.getItemById(item.getId());
    }
    private ItemDO convertItemFromModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemDO itemDO=new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        return itemDO;
    }
    private ItemStockDO convertItemstockFromModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemStockDO itemstockDO=new ItemStockDO();
        itemstockDO.setItemId(itemModel.getId());
        itemstockDO.setStock(itemModel.getStock());
        return itemstockDO;
    }
    @Override
    public List<ItemModel> listItem() {
        return null;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO=itemDOMapper.selectByPrimaryKey(id);
        if(itemDO==null){
            return null;
        }
        ItemStockDO itemStockDO=itemStockDOMapper.selectByItemId(itemDO.getId());

        ItemModel itemModel=convertModelFromObject(itemDO,itemStockDO);
        return itemModel;
    }
    private ItemModel convertModelFromObject(ItemDO itemDO,ItemStockDO itemStockDO){
        ItemModel itemModel=new ItemModel();
        BeanUtils.copyProperties(itemDO,itemStockDO);
        itemModel.setPrice(itemDO.getPrice());
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }
}
