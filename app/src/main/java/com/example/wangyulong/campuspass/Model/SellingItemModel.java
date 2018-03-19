package com.example.wangyulong.campuspass.Model;

import android.databinding.ObservableField;
import android.net.Uri;

import com.example.wangyulong.campuspass.Constant.Category;

/**
 * Created by wangyulong on 09/03/18.
 */

public class SellingItemModel
{
    //region Fields and Const
    public ObservableField<String> price = new ObservableField<>("");
    public ObservableField<String> stock = new ObservableField<>("");
    public ObservableField<String> item_title = new ObservableField<>("");
    public ObservableField<String> item_short_descr = new ObservableField<>("");
    public ObservableField<Category.BuyingItemCondition> item_condition = new ObservableField<>(Category.BuyingItemCondition.NEW);
    public ObservableField<Category.BuyingItemTag> item_tag = new ObservableField<>(Category.BuyingItemTag.BOOKS);
    public ObservableField<String> item_img_location = new ObservableField<>("");
    public ObservableField<Uri> item_img_download_uri = new ObservableField<>(Uri.EMPTY);
    //endregion Fields and Const

    //region Constructor
    public SellingItemModel()
    {

    }
    //endregion Constructor
}
