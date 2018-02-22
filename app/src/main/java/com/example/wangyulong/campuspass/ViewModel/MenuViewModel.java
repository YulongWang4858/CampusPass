package com.example.wangyulong.campuspass.ViewModel;

/**
 * Created by wangyulong on 22/02/18.
 */

public class MenuViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static MenuViewModel _instance = null;
    //endregion Fields and Consts

    //region Properties
    public static MenuViewModel menuViewModel()
    {
        if (_instance == null)
        {
            _instance = new MenuViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private MenuViewModel()
    {
        //Implement if necessary
    }
    //endregion Constructor
}
