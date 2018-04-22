package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;
import android.support.design.widget.Snackbar;

import com.example.wangyulong.campuspass.Constant.SnackBarMsg;
import com.example.wangyulong.campuspass.Helper.ConnectionHelper;
import com.example.wangyulong.campuspass.Helper.DisplayMsgHelper;
import com.example.wangyulong.campuspass.Message.SnackBarMessageHandler;
import com.example.wangyulong.campuspass.Model.UserModel;

/**
 * Created by wangyulong on 20/02/18.
 */

public class LoginViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static LoginViewModel _instance = null;
    private UserModel _current_user = new UserModel();
    public DisplayMsgHelper displayMsgHelper = DisplayMsgHelper.getInstance();
    public ConnectionHelper connectionHelper = ConnectionHelper.connectionHelper();
    public ObservableField<String> user_pass = new ObservableField<>(new String(""));
    public ObservableField<String> user_matric = new ObservableField<>(new String(""));
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

        //TODO: Remove after testing
        this.user_pass.set("2enXm?83");
        this.user_matric.set("1004858369@qq.com");
    }
    //endregion Override

    //region Methods
    public void set_user_connection_status(boolean status)
    {
        this.connectionHelper.set_connection_status(status);
    }
    //endregion Methods
}
