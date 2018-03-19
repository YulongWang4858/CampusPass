package com.example.wangyulong.campuspass.Model;

import android.util.Log;

/**
 * Created by wangyulong on 19/3/18.
 */

public class DatabaseSellingModel
{
    public String getItem_condition_tag()
    {
        return item_condition_tag;
    }

    public void setItem_condition_tag(String item_condition_tag)
    {
        this.item_condition_tag = item_condition_tag;
    }

    public String getItem_title()
    {
        return item_title;
    }

    public void setItem_title(String item_title)
    {
        this.item_title = item_title;
    }

    public String getItem_category_tag()
    {
        return item_category_tag;
    }

    public void setItem_category_tag(String item_category_tag)
    {
        this.item_category_tag = item_category_tag;
    }

    public String getItem_short_decr()
    {
        return item_short_descr;
    }

    public void setItem_short_decr(String item_short_decr)
    {
        this.item_short_descr = item_short_decr;
    }

    public String getItem_stock_left()
    {
        return item_stock_left;
    }

    public void setItem_stock_left(String item_stock_left)
    {
        this.item_stock_left = item_stock_left;
    }

    public String getItem_img_uri()
    {
        return item_img_uri;
    }

    public void setItem_img_uri(String item_img_uri)
    {
        this.item_img_uri = item_img_uri;
    }

    public String getItem_owner()
    {
        return item_owner;
    }

    public void setItem_owner(String item_owner)
    {
        this.item_owner = item_owner;
    }

    public String getItem_price()
    {
        return item_price;
    }

    public void setItem_price(String item_price)
    {
        this.item_price = item_price;
    }

    public String item_condition_tag;
    public String item_title;
    public String item_category_tag;
    public String item_short_descr;
    public String item_stock_left;
    public String item_img_uri;
    public String item_owner;
    public String item_price;

    public DatabaseSellingModel()
    {

    }
}
