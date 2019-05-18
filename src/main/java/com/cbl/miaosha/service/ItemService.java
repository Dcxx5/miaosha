package com.cbl.miaosha.service;

import com.cbl.miaosha.error.BusinessException;
import com.cbl.miaosha.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    ItemModel createItem (ItemModel item) throws BusinessException;

    List<ItemModel> listItem();

    ItemModel getItemById(Integer id);
}
