package com.example.wangyulong.campuspass.Model;

/**
 * Created by wangyulong on 2/4/18.
 */

public class HobbyModel
{
    //region Feilds and Const
    public String hobby_name;
    public String hobby_icon_uri;
    public String hobby_category;
    public int participants;
    public String hobby_id;
    public int icon_pic;
    //endregion Fields and Const

    //region Constructor
    public HobbyModel()
    {
        //init
    }

    public HobbyModel(String hobby_name, int icon_pic, int participants)
    {
        this.hobby_name = hobby_name;
        this.icon_pic = icon_pic;
        this.participants = participants;
    }
    //endregion Constructor

    //region Properties
    public String getHobby_name()
    {
        return hobby_name;
    }

    public void setHobby_name(String hobby_name)
    {
        this.hobby_name = hobby_name;
    }

    public String getHobby_icon_uri()
    {
        return hobby_icon_uri;
    }

    public void setHobby_icon_uri(String hobby_icon_uri)
    {
        this.hobby_icon_uri = hobby_icon_uri;
    }

    public String getHobby_category()
    {
        return hobby_category;
    }

    public void setHobby_category(String hobby_category)
    {
        this.hobby_category = hobby_category;
    }

    public int getParticipants()
    {
        return participants;
    }

    public void setParticipants(int participants)
    {
        this.participants = participants;
    }

    public String getHobby_id()
    {
        return hobby_id;
    }

    public void setHobby_id(String hobby_id)
    {
        this.hobby_id = hobby_id;
    }

    public int getIcon_pic()
    {
        return icon_pic;
    }

    public void setIcon_pic(int icon_pic)
    {
        this.icon_pic = icon_pic;
    }

    //endregion Properties

}
