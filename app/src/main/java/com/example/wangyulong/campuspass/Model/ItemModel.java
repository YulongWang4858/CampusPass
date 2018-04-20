package com.example.wangyulong.campuspass.Model;

/**
 * Created by wangyulong on 17/4/18.
 */

public class ItemModel
{
    //region Fields and Const
    public String item_name;
    public String item_descr;
    public String item_photo_uri;
    public String item_num_photos;
    public String item_category;
    public String item_condition;
    public String item_stock_left;
    public String item_price;
    public String item_id;
    public String item_owner_id;
    //endregion Fields and Const

    //region Properties


    public String getItem_id()
    {
        return item_id;
    }

    public void setItem_id(String item_id)
    {
        this.item_id = item_id;
    }

    public String getItem_owner_id()
    {
        return item_owner_id;
    }

    public void setItem_owner_id(String item_owner_id)
    {
        this.item_owner_id = item_owner_id;
    }

    public String getItem_name()
    {
        return item_name;
    }

    public void setItem_name(String item_name)
    {
        this.item_name = item_name;
    }

    public String getItem_descr()
    {
        return item_descr;
    }

    public void setItem_descr(String item_descr)
    {
        this.item_descr = item_descr;
    }

    public String getItem_photo_uri()
    {
        return item_photo_uri;
    }

    public void setItem_photo_uri(String item_photo_uri)
    {
        this.item_photo_uri = item_photo_uri;
    }

    public String getItem_num_photos()
    {
        return item_num_photos;
    }

    public void setItem_num_photos(String item_num_photos)
    {
        this.item_num_photos = item_num_photos;
    }

    public String getItem_category()
    {
        return item_category;
    }

    public void setItem_category(String item_category)
    {
        this.item_category = item_category;
    }

    public String getItem_condition()
    {
        return item_condition;
    }

    public void setItem_condition(String item_condition)
    {
        this.item_condition = item_condition;
    }

    public String getItem_stock_left()
    {
        return item_stock_left;
    }

    public void setItem_stock_left(String item_stock_left)
    {
        this.item_stock_left = item_stock_left;
    }

    public String getItem_price()
    {
        return item_price;
    }

    public void setItem_price(String item_price)
    {
        this.item_price = item_price;
    }

    //endregion Properties

    //region Constructor
    public ItemModel()
    {

    }
    //endregion Constructor
}
