package com.example.wangyulong.campuspass.ViewModel;

import com.example.wangyulong.campuspass.Constant.DisplayMsg;
import com.example.wangyulong.campuspass.Helper.ConnectionHelper;
import com.example.wangyulong.campuspass.Helper.DisplayMsgHelper;

/**
 * Created by wangyulong on 20/02/18.
 */

public class LoginViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static LoginViewModel _instance = null;
    public DisplayMsgHelper displayMsgHelper = DisplayMsgHelper.getInstance();
    public ConnectionHelper connectionHelper = ConnectionHelper.connectionHelper();

    public String _user_pass;
    public String _user_matric;

    //endregion Fields and Consts

    //region Properties
    public static LoginViewModel loginViewModel()
    {
        if (_instance == null)
        {
            _instance = new LoginViewModel();
        }

        return _instance;
    }
//
//    public String get_user_pass()
//    {
//        return _user_pass;
//    }
//
//    public void set_user_pass(String _user_pass)
//    {
//        this._user_pass = _user_pass;
//    }
//
//    public String get_user_matric()
//    {
//        return _user_matric;
//    }
//
//    public void set_user_matric(String _user_matric)
//    {
//        this._user_matric = _user_matric;
//    }
    //endregion Properties

    //region Override
    private LoginViewModel()
    {
        //Implement if necessary
        _user_matric = null;
        _user_pass = null;
    }
    //endregion Override

    //region Button Events
    public void onLoginButtonClicked()
    {
        if (_user_matric != null && _user_pass != null)
        {
            connectionHelper.ConnectUser(_user_matric, _user_pass);
        } else
        {
            //TODO: Flag Failure
        }
    }

    public void onRegisterButtonClicked()
    {

    }

    //endregion Button Events

    //region Methods

    //endregion Methods
}
