package com.example.wangyulong.campuspass.Model;

import android.databinding.ObservableField;

import com.example.wangyulong.campuspass.Constant.Category;

import java.util.UUID;

/**
 * Created by wangyulong on 20/02/18.
 */

public class UserModel
{
    //region Fields and Consts
    private String _user_name;
    private String _user_matric;
    private String _user_email;
    private String _password;
    private Category.UserTYpe _user_type;

    public ObservableField<String> user_name = new ObservableField<>("");
    public ObservableField<String> user_matric = new ObservableField<>("");
    public ObservableField<String> user_course_info = new ObservableField<>("");
    public ObservableField<String> user_contact_info = new ObservableField<>("");
    public ObservableField<String> user_email = new ObservableField<>("");
    public ObservableField<String> user_password = new ObservableField<>("");
    public ObservableField<String> user_pickup_address = new ObservableField<>("");
    public ObservableField<String> user_career_info = new ObservableField<>("");
    public ObservableField<UUID> user_id = new ObservableField<>(UUID.randomUUID());
    //endregion Fields and Consts

    //region Constructor

    //Default
    public UserModel()
    {
        //Init
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
