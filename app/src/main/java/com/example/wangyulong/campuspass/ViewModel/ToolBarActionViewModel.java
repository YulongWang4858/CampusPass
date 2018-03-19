package com.example.wangyulong.campuspass.ViewModel;

/**
 * Created by wangyulong on 18/3/18.
 */

public class ToolBarActionViewModel extends BasicViewModel
{
    //region Fields and Const
    private static ToolBarActionViewModel _instance = null;
    //endregion Fields and Const

    //region Properties
    public static ToolBarActionViewModel toolBarActionViewModel()
    {
        if (_instance == null)
        {
            _instance = new ToolBarActionViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private ToolBarActionViewModel()
    {

    }
    //endregion Constructor

    //region Methods

    //endregion Methods
}
