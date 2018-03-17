package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.view.inputmethod.BaseInputConnection;

import com.example.wangyulong.campuspass.Constant.Category;
import com.example.wangyulong.campuspass.Model.SellingItemModel;

/**
 * Created by wangyulong on 09/03/18.
 */

public class SellingViewModel extends BasicViewModel
{

    //region Fields and Const
    private static SellingViewModel _instance;
    public SellingItemModel itemModel;
    public ObservableField<Boolean> isImageSelected = new ObservableField<>(false);
    //endregion Fields and Const

    //region Properties
    public static SellingViewModel sellingViewModel()
    {
        if (_instance == null)
        {
            _instance = new SellingViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private SellingViewModel()
    {
        this.itemModel = new SellingItemModel();
    }
    //endregion Constructor

    //region Methods
    public void set_item_condition(Category.BuyingItemCondition condition)
    {
        this.itemModel.item_condition.set(condition);
    }

    public void set_item_tag(Category.BuyingItemTag tag)
    {
        this.itemModel.item_tag.set(tag);
    }

    public void is_selling_item_upload_complete()
    {
        this.isImageSelected.set(false);
    }
    //endregion Methods
}
