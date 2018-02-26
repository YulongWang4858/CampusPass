package com.example.wangyulong.campuspass.ViewModel;

/**
 * Created by wangyulong on 25/02/18.
 */

public class NotificationViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static NotificationViewModel _instance = null;
    //endregion Fields and Consts

    //region Properties
    public static NotificationViewModel notificationViewModel()
    {
        if (_instance == null)
        {
            _instance = new NotificationViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Construcotrs
    private NotificationViewModel()
    {
        /*Implement if necessary*/
    }
    //endregion Constructors

    //region Methods

    //endregion Methods
}
