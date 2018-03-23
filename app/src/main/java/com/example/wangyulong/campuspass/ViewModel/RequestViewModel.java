package com.example.wangyulong.campuspass.ViewModel;

/**
 * Created by wangyulong on 22/3/18.
 */

public class RequestViewModel extends BasicViewModel
{
    //region Fields and Const
    private static RequestViewModel _instance = null;
    //endregion Fields and Const

    //region Properties
    public static RequestViewModel requestViewModel()
    {
        if (_instance == null)
        {
            _instance = new RequestViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private RequestViewModel()
    {

    }
    //endregion Constructor

    //region Methods
    
    //endregion Methods
}
