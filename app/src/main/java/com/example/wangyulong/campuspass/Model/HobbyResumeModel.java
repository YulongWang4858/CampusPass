package com.example.wangyulong.campuspass.Model;

import com.example.wangyulong.campuspass.ViewModel.HobbyBriefViewModel;

/**
 * Created by wangyulong on 7/4/18.
 */

public class HobbyResumeModel
{
    //region Fields and Const
    public String hobby_resume_title;
    public String hobby_resume_descr;
    public String hobby_resume_price;
    public String hobby_resume_entry_id;
    public String hobby_resume_owner_id;
    public String hobby_resume_photo_uri;
    //endregion Fields and Const

    //region Properties
    public String getHobby_resume_title()
    {
        return hobby_resume_title;
    }

    public void setHobby_resume_title(String hobby_resume_title)
    {
        this.hobby_resume_title = hobby_resume_title;
    }

    public String getHobby_resume_descr()
    {
        return hobby_resume_descr;
    }

    public void setHobby_resume_descr(String hobby_resume_descr)
    {
        this.hobby_resume_descr = hobby_resume_descr;
    }

    public String getHobby_resume_price()
    {
        return hobby_resume_price;
    }

    public void setHobby_resume_price(String hobby_resume_price)
    {
        this.hobby_resume_price = hobby_resume_price;
    }

    public String getHobby_resume_entry_id()
    {
        return hobby_resume_entry_id;
    }

    public void setHobby_resume_entry_id(String hobby_resume_entry_id)
    {
        this.hobby_resume_entry_id = hobby_resume_entry_id;
    }

    public String getHobby_resume_owner_id()
    {
        return hobby_resume_owner_id;
    }

    public void setHobby_resume_owner_id(String hobby_resume_owner_id)
    {
        this.hobby_resume_owner_id = hobby_resume_owner_id;
    }

    public String getHobby_resume_photo_uri()
    {
        return hobby_resume_photo_uri;
    }

    public void setHobby_resume_photo_uri(String hobby_resume_photo_uri)
    {
        this.hobby_resume_photo_uri = hobby_resume_photo_uri;
    }
    //endregion Properties

    //region Constructor
    public HobbyResumeModel()
    {
        //init
    }

    public HobbyResumeModel(String hobby_resume_title, String hobby_resume_descr, String hobby_resume_price, String hobby_resume_entry_id, String hobby_resume_owner_id, String hobby_resume_photo_uri)
    {
        this.hobby_resume_title = hobby_resume_title;
        this.hobby_resume_descr = hobby_resume_descr;
        this.hobby_resume_price = hobby_resume_price;
        this.hobby_resume_entry_id = hobby_resume_entry_id;
        this.hobby_resume_owner_id = hobby_resume_owner_id;
        this.hobby_resume_photo_uri = hobby_resume_photo_uri;
    }

    //endregion Constructor
}
