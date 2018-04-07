package com.example.wangyulong.campuspass.Model;

/**
 * Created by wangyulong on 5/4/18.
 */

public class DetailHobbyModel
{
    //region Fields and Const
    public String detail_hobby_name;
    public int detail_participants;
    public String detail_hobby_descr;
    public String detail_hobby_icon_uri;
    //endregion Fields and Const

    //region Constructor
    public DetailHobbyModel()
    {
        //init
    }

    public DetailHobbyModel(String detail_hobby_name, String detail_hobby_descr, int detail_participants, String detail_hobby_icon_uri)
    {
        this.detail_hobby_name = detail_hobby_name;
        this.detail_hobby_descr = detail_hobby_descr;
        this.detail_hobby_icon_uri = detail_hobby_icon_uri;
        this.detail_participants = detail_participants;
    }
    //endregion Constructor

    //region Properties
    public String getDetail_hobby_name()
    {
        return detail_hobby_name;
    }

    public void setDetail_hobby_name(String detail_hobby_name)
    {
        this.detail_hobby_name = detail_hobby_name;
    }

    public int getDetail_participants()
    {
        return detail_participants;
    }

    public void setDetail_participants(int detail_participants)
    {
        this.detail_participants = detail_participants;
    }

    public String getDetail_hobby_descr()
    {
        return detail_hobby_descr;
    }

    public void setDetail_hobby_descr(String detail_hobby_descr)
    {
        this.detail_hobby_descr = detail_hobby_descr;
    }

    public String getDetail_hobby_icon_uri()
    {
        return detail_hobby_icon_uri;
    }

    public void setDetail_hobby_icon_uri(String detail_hobby_icon_uri)
    {
        this.detail_hobby_icon_uri = detail_hobby_icon_uri;
    }
    //endregion Properties
}
