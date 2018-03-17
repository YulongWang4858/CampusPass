package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;

import com.example.wangyulong.campuspass.Model.UserModel;

import java.util.UUID;

/**
 * Created by wangyulong on 22/02/18.
 */

public class RegisterViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static RegisterViewModel _instance = null;
    private UserModel _new_user = new UserModel();

    public ObservableField<String> user_name = _new_user.user_name;
    public ObservableField<String> user_email = _new_user.user_email;
    public ObservableField<String> user_contact_info = _new_user.user_contact_info;
    public ObservableField<String> user_course_info = _new_user.user_course_info;
    public ObservableField<String> user_pickup_address = _new_user.user_pickup_address;
    public ObservableField<String> user_password = _new_user.user_password;
    public ObservableField<String> user_career_info = _new_user.user_career_info;
    public ObservableField<UUID> user_unique_id = _new_user.user_id;
    //endregion Fields and Consts

    //region Properties
    public static RegisterViewModel registerViewModel()
    {
        if (_instance == null)
        {
            _instance = new RegisterViewModel();
        }

        return _instance;
    }
    //endregion Properties

    //region Constructor
    private RegisterViewModel()
    {

    }
    //endregion Constructor

    //region Methods
    private boolean CreateUser(String name, String email, String hpNum, String studentInfo)
    {
        return true;
    }

    public boolean VerifyUserInfoFormat()
    {
//        if (email.get() != null && student_name.get() != null)
//        {
//            return true;
//        }

        return true;
    }

    public void init_new_user()
    {
        this._new_user = new UserModel();
    }
    //endregion Methods
}
