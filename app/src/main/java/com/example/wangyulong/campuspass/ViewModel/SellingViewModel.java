package com.example.wangyulong.campuspass.ViewModel;

import android.view.inputmethod.BaseInputConnection;

import com.example.wangyulong.campuspass.Model.SellingItemModel;

/**
 * Created by wangyulong on 09/03/18.
 */

public class SellingViewModel extends BasicViewModel
{

    //region Fields and Const
    private static SellingViewModel _instance;
    public SellingItemModel itemModel;
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

    //endregion Methods
}
