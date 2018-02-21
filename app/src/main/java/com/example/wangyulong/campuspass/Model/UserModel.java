package com.example.wangyulong.campuspass.Model;

import com.example.wangyulong.campuspass.Constant.Category;

/**
 * Created by wangyulong on 20/02/18.
 */

public class UserModel
{
    //region Fields and Consts
    private String _user_name;
    private String _user_matric;
    private String _password;
    private Category.UserTYpe _user_type;
    //endregion Fields and Consts

    //region Constructor

    //Default
    public UserModel()
    {
        _user_name = "Not Yet Set";
        _user_matric = "Not Yet Set";
        _password = "";
        _user_type = Category.UserTYpe.STUDENT;
    }

    //Override
    public UserModel(String matric, String password)
    {
        _user_name = "Wang Yulong";
        _user_matric = matric;
        _password = password;
        _user_type = Category.UserTYpe.STUDENT;
    }
    //endregion Constructor
}
