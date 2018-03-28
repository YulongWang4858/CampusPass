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
    private String item_id;

    public String getItem_id()
    {
        return item_id;
    }

    public void setItem_id(String item_id)
    {
        this.item_id = item_id;
    }

    public String get_item_img_uri()
    {
        return _item_img_uri;
    }

    public void set_item_img_uri(String _item_img_uri)
    {
        this._item_img_uri = _item_img_uri;
    }

    public void set_item_id(String _item_id)
    {
        this._item_id = _item_id;
    }

    private String _item_img_uri;
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
                           Category.BuyingItemCondition _item_condition, double _item_price, int _item_stock_left, int _item_num_imgs, String item_id)
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
        this.item_id = item_id;
    }

    public BuyingItemModel(String uri, String _item_title, String _item_short_description, String category, String condition, String _item_price,
                           String _item_stock_left, String _owner_id, String item_id)
    {
        this._item_title = _item_title;
        this._item_img_uri = uri;
        this._item_condition = parseConditionString(condition);
        this._item_tag = parseCategoryString(category);
        this._item_id = _owner_id;
        this._item_stock_left = Integer.valueOf(_item_stock_left);
        this._item_short_description = _item_short_description;
        this._item_price = Double.valueOf(_item_price);
        this.item_id = item_id;
    }
    //endregion Constructor

    //region Methods

    private Category.BuyingItemCondition parseConditionString(String condition)
    {
        switch (condition)
        {
            case "NEW":
                return Category.BuyingItemCondition.NEW;
            case "DISPOSE":
                return Category.BuyingItemCondition.DISPOSE;
            default:
                return Category.BuyingItemCondition.SECOND_HAND;
        }
    }

    private Category.BuyingItemTag parseCategoryString(String category)
    {
        switch (category)
        {
            case "ELECTRONICS":
                return Category.BuyingItemTag.ELECTRONICS;
            case "BOOKS":
                return Category.BuyingItemTag.BOOKS;
            case "FOOD":
                return Category.BuyingItemTag.FOOD;
            case "CLOTHING":
                return Category.BuyingItemTag.CLOTHING;
            default:
                return Category.BuyingItemTag.HOME_EQUIPEMENTS;
        }
    }

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
