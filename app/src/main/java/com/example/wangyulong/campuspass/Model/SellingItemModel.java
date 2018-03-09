package com.example.wangyulong.campuspass.Model;

import android.databinding.ObservableField;
import com.example.wangyulong.campuspass.Constant.Category;

/**
 * Created by wangyulong on 09/03/18.
 */

public class SellingItemModel
{
    //region Fields and Const
    public ObservableField<Double> price = new ObservableField<>(0.00);
    public ObservableField<Integer> stock = new ObservableField<Integer>(0);
    public ObservableField<String> item_title = new ObservableField<>("");
    public ObservableField<String> item_short_descr = new ObservableField<>("");
    public ObservableField<Category.BuyingItemCondition> item_condition = new ObservableField<>(Category.BuyingItemCondition.NEW);
    public ObservableField<Category.BuyingItemTag> item_tag = new ObservableField<>(Category.BuyingItemTag.BOOKS);
    public ObservableField<String> item_img_location = new ObservableField<>("");
    //endregion Fields and Const

    //region Constructor
    public SellingItemModel()
    {

    }
    //endregion Constructor
}
