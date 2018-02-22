package com.example.wangyulong.campuspass.ViewModel;

import android.databinding.ObservableField;

import com.example.wangyulong.campuspass.Model.UserModel;

/**
 * Created by wangyulong on 22/02/18.
 */

public class RegisterViewModel extends BasicViewModel
{
    //region Fields and Consts
    private static RegisterViewModel _instance = null;
    private UserModel _user;

    public ObservableField<String> student_name = new ObservableField<String>();
    public ObservableField<String> email = new ObservableField<String>();
    public ObservableField<String> phone = new ObservableField<String>();
    public ObservableField<String> student_info = new ObservableField<String>();
    public ObservableField<String> career_info = new ObservableField<String>();
    public ObservableField<String> address = new ObservableField<String>();

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
        _user = new UserModel();
    }
    //endregion Constructor

    //region Methods
    private boolean CreateUser(String name, String email, String hpNum, String studentInfo)
    {
        return true;
    }
    //endregion Methods
}
