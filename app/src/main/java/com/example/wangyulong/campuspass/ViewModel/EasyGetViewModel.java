package com.example.wangyulong.campuspass.ViewModel;

import com.example.wangyulong.campuspass.Activity.EasyGetActivity;

import java.util.EmptyStackException;

/**
 * Created by wangyulong on 04/03/18.
 */

public class EasyGetViewModel extends BasicViewModel
{
    //region Fields and Const
    private static EasyGetViewModel _instance = null;
    //endregion Fields and Const


    //region Properties
    public static EasyGetViewModel easyGetViewModel()
    {
        if (_instance == null)
        {
            _instance = new EasyGetViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private EasyGetViewModel()
    {
        /*Implement if necessary*/
    }
    //endregion Constructor

    //region Methods

    //endregion Methods
}
