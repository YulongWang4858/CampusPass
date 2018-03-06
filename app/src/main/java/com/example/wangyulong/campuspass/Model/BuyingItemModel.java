package com.example.wangyulong.campuspass.Model;

import com.example.wangyulong.campuspass.Constant.Category;

import java.util.UUID;

/**
 * Created by wangyulong on 05/03/18.
 */

public class BuyingItemModel
{
    //region Fields and Const
    private int _item_image_id;
    private String _item_title;
    private String _item_short_description;
    private double _item_price;
    private boolean _is_in_discount;
    private Category.BuyingItemTag _item_tag;
    private Category.BuyingItemCondition _item_condition;
    private int _item_stock_left;
    private int _item_num_imgs;
    private String _item_id;
    //endregion Fields and Const

    //region Constructor
    public BuyingItemModel()
    {
        //init
//        _item_title = new String("No title");
//        _item_short_description = new String("No description");
    }

    public BuyingItemModel(int _item_image_id, String _item_title, String _item_short_description, Category.BuyingItemTag _item_tag,
                           Category.BuyingItemCondition _item_condition, double _item_price, int _item_stock_left, int _item_num_imgs)
    {
        this._item_image_id = _item_image_id;
        this._item_title = new String(_item_title);
        this._item_short_description = new String(_item_short_description);
        this._item_tag = _item_tag;
        this._item_condition = _item_condition;
        this._item_price = _item_price;
        this._item_stock_left = _item_stock_left;
        this._item_num_imgs = _item_num_imgs;

        this._item_id = UUID.randomUUID().toString(); // unique item id
    }
    //endregion Constructor

    //region Methods

    public int get_item_image_id()
    {
        return _item_image_id;
    }

    public String get_item_title()
    {
        return _item_title;
    }

    public String get_item_short_description()
    {
        return _item_short_description;
    }

    public double get_item_price()
    {
        return _item_price;
    }

    public boolean is_is_in_discount()
    {
        return _is_in_discount;
    }

    public Category.BuyingItemTag get_item_tag()
    {
        return _item_tag;
    }

    public Category.BuyingItemCondition get_item_condition()
    {
        return _item_condition;
    }

    public int get_item_stock_left()
    {
        return _item_stock_left;
    }

    public int get_item_num_imgs()
    {
        return _item_num_imgs;
    }

    public String get_item_id()
    {
        return _item_id;
    }

    //endregion Methods
}
