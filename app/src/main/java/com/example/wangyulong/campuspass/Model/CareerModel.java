package com.example.wangyulong.campuspass.Model;

/**
 * Created by wangyulong on 8/4/18.
 */

public class CareerModel
{
    //region Fields and Const
    public String career_title;
    public String career_descr;
    public String career_icon_photo_uri;
    public String career_participants;
    //endregion Fields and Const

    //region Properties
    public String getCareer_participants()
    {
        return career_participants;
    }

    public void setCareer_participants(String career_participants)
    {
        this.career_participants = career_participants;
    }

    public String getCareer_title()
    {
        return career_title;
    }

    public void setCareer_title(String career_title)
    {
        this.career_title = career_title;
    }

    public String getCareer_descr()
    {
        return career_descr;
    }

    public void setCareer_descr(String career_descr)
    {
        this.career_descr = career_descr;
    }

    public String getCareer_icon_photo_uri()
    {
        return career_icon_photo_uri;
    }

    public void setCareer_icon_photo_uri(String career_icon_photo_uri)
    {
        this.career_icon_photo_uri = career_icon_photo_uri;
    }
    //endregion Properties

    //region Constructor
    public CareerModel()
    {
        //init
    }
    //endregion Constructor
}