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
    public String user_name;
    public String user_email;
    public String user_contact;
    public String user_student_info;
    public String user_career_info;
    public String user_pickup_address;
    public String user_password;
    public String user_profile_pic_uri;
    public String user_id;
    //endregion Fields and Consts

    //region Properties

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

    public String getUser_email()
    {
        return user_email;
    }

    public void setUser_email(String user_email)
    {
        this.user_email = user_email;
    }

    public String getUser_contact()
    {
        return user_contact;
    }

    public void setUser_contact(String user_contact)
    {
        this.user_contact = user_contact;
    }

    public String getUser_student_info()
    {
        return user_student_info;
    }

    public void setUser_student_info(String user_student_info)
    {
        this.user_student_info = user_student_info;
    }

    public String getUser_career_info()
    {
        return user_career_info;
    }

    public void setUser_career_info(String user_career_info)
    {
        this.user_career_info = user_career_info;
    }

    public String getUser_pickup_address()
    {
        return user_pickup_address;
    }

    public void setUser_pickup_address(String user_pickup_address)
    {
        this.user_pickup_address = user_pickup_address;
    }

    public String getUser_password()
    {
        return user_password;
    }

    public void setUser_password(String user_password)
    {
        this.user_password = user_password;
    }

    public String getUser_profile_pic_uri()
    {
        return user_profile_pic_uri;
    }

    public void setUser_profile_pic_uri(String user_profile_pic_uri)
    {
        this.user_profile_pic_uri = user_profile_pic_uri;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    //endregion Properties

    //region Constructor
    public UserModel()
    {

    }
    //endregion Constructor
}
