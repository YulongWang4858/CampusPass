package com.example.wangyulong.campuspass.Model;

/**
 * Created by wangyulong on 20/4/18.
 */

public class ChatBoxModel
{
    //region Fields and Const
    public String chat_box_owner_id;
    public String chat_box_target_id;
    public String chat_box_id;
    public String chat_box_target_photo_uri;
    public String chat_box_owner_name;
    //endregion Fields and Const

    //region Properties

    public String getChat_box_owner_id()
    {
        return chat_box_owner_id;
    }

    public void setChat_box_owner_id(String chat_box_owner_id)
    {
        this.chat_box_owner_id = chat_box_owner_id;
    }

    public String getChat_box_target_id()
    {
        return chat_box_target_id;
    }

    public void setChat_box_target_id(String chat_box_target_id)
    {
        this.chat_box_target_id = chat_box_target_id;
    }

    public String getChat_box_id()
    {
        return chat_box_id;
    }

    public void setChat_box_id(String chat_box_id)
    {
        this.chat_box_id = chat_box_id;
    }

    public String getChat_box_target_photo_uri()
    {
        return chat_box_target_photo_uri;
    }

    public void setChat_box_target_photo_uri(String chat_box_target_photo_uri)
    {
        this.chat_box_target_photo_uri = chat_box_target_photo_uri;
    }

    public String getChat_box_owner_name()
    {
        return chat_box_owner_name;
    }

    public void setChat_box_owner_name(String chat_box_owner_name)
    {
        this.chat_box_owner_name = chat_box_owner_name;
    }
    //endregion Properties

    //region Constructors
    public ChatBoxModel()
    {

    }
    //endregion Constructors
}
