package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.support.design.widget.Snackbar;

import com.example.wangyulong.campuspass.Constant.SnackBarMsg;
import com.example.wangyulong.campuspass.Helper.ConnectionHelper;
import com.example.wangyulong.campuspass.Helper.DisplayMsgHelper;
import com.example.wangyulong.campuspass.Message.SnackBarMessageHandler;

/**
 * Created by wangyulong on 20/02/18.
 */

public class LoginViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static LoginViewModel _instance = null;
    public DisplayMsgHelper displayMsgHelper = DisplayMsgHelper.getInstance();
    public ConnectionHelper connectionHelper = ConnectionHelper.connectionHelper();
    public ObservableField<String> user_pass = new ObservableField<String>("");
    public ObservableField<String> user_matric = new ObservableField<String>("");
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
    //endregion Properties

    //region Override
    private LoginViewModel()
    {
        //Implement if necessary
    }
    //endregion Override

    //region Methods
    public boolean userLogin()
    {
        //connect and verify info
        if (user_matric.get().length() != 0 && user_pass.get().length() != 0)
        {
            if (connectionHelper.ConnectUser(user_matric.get(), user_pass.get())) //Connection and Info correct
            {
                this.showOnSnackBar(SnackBarMsg.LOGIN_SUCCESS);

                return true;
            } else
            {
                this.showOnSnackBar(SnackBarMsg.LOGIN_FAILED_WRONG_INFO);
            }
        } else
        {
            this.showOnSnackBar(SnackBarMsg.LOGIN_FAILED_EMPTY_INFO);
        }

        return false;
    }
    //endregion Methods
}
