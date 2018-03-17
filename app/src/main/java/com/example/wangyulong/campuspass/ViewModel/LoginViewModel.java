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
    public ObservableField<String> user_pass = _current_user.user_password;
    public ObservableField<String> user_matric = _current_user.user_matric;
    public ObservableField<String> user_email = _current_user.user_email;
    public ObservableField<String> user_course_info = _current_user.user_course_info;
    public ObservableField<String> user_contact_info = _current_user.user_contact_info;
    public ObservableField<String> user_name = _current_user.user_name;
    public ObservableField<String> user_pickup_address = _current_user.user_pickup_address;
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

    public void set_user_connection_status(boolean status)
    {
        this.connectionHelper.set_connection_status(status);
    }

    public boolean get_user_connection_status()
    {
        return this.connectionHelper.get_connection_stauts();
    }
    //endregion Methods
}
